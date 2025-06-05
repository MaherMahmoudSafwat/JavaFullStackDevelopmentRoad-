package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main class demonstrating Spring's Dependency Injection (DI) in action
 */
public class Main {
    public static void main(String[] args) {
        // ===================================================================
        // 1. SPRING CONTAINER INITIALIZATION
        // ===================================================================
        /*
         * Creates Spring's ApplicationContext (the container) by loading:
         * - The XML configuration file ("Spring.XML")
         * - All bean definitions inside it
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.XML");

        // ===================================================================
        // 2. GETTING BEANS FROM THE CONTAINER
        // ===================================================================
        /*
         * Gets the Alien bean with:
         * - Explicit casting (older style)
         * - Bean name "alien1" must match XML configuration
         */
        Alien obj1 = (Alien) context.getBean("alien1");

        // ===================================================================
        // 3. WORKING WITH THE BEAN
        // ===================================================================
        /*
         * Example 1: Accessing bean properties
         * - Shows the age value that was either:
         *   - Set via XML property injection (<property name="age" value="21"/>)
         *   - Or set via constructor injection
         */
        System.out.println("Alien's age: " + obj1.getAge());

        /*
         * Example 2: Using bean functionality
         * - Calls the code() method which uses the injected Computer dependency
         * - The actual implementation (Laptop/Desktop) depends on XML config
         */
        obj1.code();

        // ===================================================================
        // 4. ALTERNATIVE WAYS TO GET BEANS (COMMENTED EXAMPLES)
        // ===================================================================
        /*
        // Example 3: Getting another reference to the same bean (if scope="singleton")
        Alien obj2 = (Alien) context.getBean("alien1");
        System.out.println("Second reference age: " + obj2.getAge());
        */

        /*
        // Example 4: Manual property setting (alternative to XML configuration)
        // obj1.setAge(21);  // Uncomment to override XML configuration
        */

        // ===================================================================
        // 5. GETTING DEPENDENCY BEANS DIRECTLY
        // ===================================================================
        /*
         * Example 5: Getting a Computer implementation directly
         * - Method 1: By bean name ("com2" from XML)
         */
        Desktop obj = (Desktop) context.getBean("com2");

        /*
         * Example 6: Getting by class type (Spring finds the matching bean)
         * - Works because we marked one as primary="true" in XML
         * - Throws exception if multiple candidates exist without primary
         */
        Desktop Obj1 = context.getBean(Desktop.class);
    }
}


