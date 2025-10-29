package com.MyCompany.DemoSpringBootProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * SERVICE CLASS - Demonstrates CONSTRUCTOR INJECTION (BEST PRACTICE)
 *
 * This class shows the RECOMMENDED way to handle dependency injection
 * using constructor injection instead of field or setter injection.
 *
 * @Service is a specialized @Component annotation that:
 * 1. Marks this class as a service layer component
 * 2. Makes it eligible for component scanning
 * 3. Indicates this class contains business logic
 * 4. Can be enhanced by AOP (Aspect-Oriented Programming)
 */

@Service  // Specialized @Component for service layer
public class MyServiceClass {

    /*
     * CONSTRUCTOR INJECTION - BEST PRACTICE:
     *
     * WHY CONSTRUCTOR INJECTION IS RECOMMENDED:
     * 1. IMMUTABILITY: Fields can be final (thread-safe)
     * 2. TESTABILITY: Easy to test with mock objects
     * 3. FAIL-FAST: Application fails to start if dependencies are missing
     * 4. NO REFLECTION: Dependencies are clear and explicit
     * 5. CIRCULAR DEPENDENCY DETECTION: Spring detects circular dependencies early
     * 6. FRAMEWORK INDEPENDENCE: Works without Spring annotations in tests
     */

    // Making field final ensures immutability after construction
    private final MyFirstClass myFirstClass;

    /*
     * CONSTRUCTOR INJECTION:
     * Spring will automatically call this constructor and inject dependencies
     *
     * @Autowired is optional on constructors (since Spring 4.3) when there's only one constructor
     * Spring will automatically resolve which MyFirstClass bean to inject:
     * - If @Primary bean exists, it will inject that one
     * - If @Qualifier is specified, it will inject the qualified bean
     * - If only one bean of type exists, it will inject that one
     */
    @Autowired  // Optional annotation - Spring can infer this
    public MyServiceClass(MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
        System.out.println("MyServiceClass created with CONSTRUCTOR INJECTION");
        System.out.println("Injected bean says: " + myFirstClass.HelloWorld());
    }

    /*
     * ALTERNATIVE: Constructor with @Qualifier for specific bean selection
     * Uncomment this and comment above constructor to see different bean injection
     */
    /*
    @Autowired
    public MyServiceClass(@Qualifier("XYZ") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
        System.out.println("MyServiceClass created with QUALIFIED CONSTRUCTOR INJECTION");
        System.out.println("Injected qualified bean says: " + myFirstClass.HelloWorld());
    }
    */

    /*
     * BUSINESS METHODS:
     * These methods use the injected dependency to perform business logic
     */
    public void TellTheStory() {
        System.out.println("=== SERVICE LAYER STORY ===");
        System.out.println("This is a bean from MyFirstClass => " + myFirstClass.HelloWorld());
        System.out.println("Service successfully used injected dependency!");

        // Demonstrating that we can call any method on injected dependency
        System.out.println("Additional info from injected bean:");
        System.out.println("- Number: " + myFirstClass.getNumber());
        System.out.println("- Property: " + myFirstClass.getPropertyName());
    }

    /*
     * GETTER METHOD (if needed for testing or other services)
     * Since field is final, we only provide getter, not setter
     */
    public MyFirstClass getMyFirstClass() {
        return myFirstClass;
    }
}

/*
 * DEPENDENCY INJECTION COMPARISON:
 *
 * 1. CONSTRUCTOR INJECTION (RECOMMENDED):
 *    - Fields can be final (immutable)
 *    - All dependencies required at construction time
 *    - Easy to test (just call constructor with mocks)
 *    - Framework independent
 *
 * 2. SETTER INJECTION (GOOD for optional dependencies):
 *    - Dependencies can be optional
 *    - Can change dependencies after object creation
 *    - Good for optional dependencies
 *    - JavaBean compliant
 *
 * 3. FIELD INJECTION (NOT RECOMMENDED):
 *    - Very concise code
 *    - But: hard to test, uses reflection, tight coupling
 *    - Makes dependencies hidden
 *    - Cannot use final fields
 *
 * SPRING'S DEPENDENCY RESOLUTION ORDER:
 * 1. Look for @Primary annotated bean
 * 2. Look for @Qualifier match
 * 3. If only one bean of type exists, use it
 * 4. If multiple beans and no @Primary/@Qualifier, throw exception
 */
