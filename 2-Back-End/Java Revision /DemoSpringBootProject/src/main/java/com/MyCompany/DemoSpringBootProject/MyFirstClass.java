package com.MyCompany.DemoSpringBootProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * SPRING COMPONENT CLASS - Demonstrates Various Dependency Injection Types
 *
 * This class shows different ways to inject dependencies and values in Spring:
 * 1. Field Injection (NOT RECOMMENDED - explained below)
 * 2. Setter Injection (RECOMMENDED for optional dependencies)
 * 3. Constructor Injection (MOST RECOMMENDED - used in MyServiceClass)
 * 4. Value Injection from properties files
 */

// @Component makes this class a Spring-managed bean
// Spring will create an instance and manage its lifecycle in the IOC container
@Component

// @PropertySource tells Spring to load additional property files
// "classpath:" means look in src/main/resources folder
@PropertySource("classpath:Customers.properties")
@Profile("Customers")
public class MyFirstClass {

    /*
     * FIELD INJECTION EXAMPLES (NOT RECOMMENDED):
     * These fields are injected directly by Spring using reflection
     */

    // Regular field - not injected, set by constructor
    private String name;

    // FIELD INJECTION of Environment bean
    // Spring will inject Environment bean directly into this field
    // WHY FIELD INJECTION IS NOT RECOMMENDED:
    // 1. Makes testing difficult (can't mock dependencies easily)
    // 2. Creates tight coupling with Spring framework
    // 3. Violates encapsulation (private fields accessed via reflection)
    // 4. Makes circular dependencies harder to detect
    // 5. Cannot make fields final (immutability issues)
    @Autowired
    private Environment environment;

    // VALUE INJECTION from properties or hardcoded values
    // @Value can inject:
    // - Hardcoded values: @Value("123")
    // - Properties: @Value("${property.name}")
    // - SpEL expressions: @Value("#{systemProperties['java.version']}")
    @Value("123")
    private int number;

    // Injecting value from properties file using ${} placeholder
    @Value("${my.Prop1}")
    private String propertyName;

    /*
     * CONSTRUCTORS:
     * Spring needs at least one constructor to create the bean
     */

    // Default constructor for Spring (required when you have other constructors)
    public MyFirstClass() {
        this.name = "Default Name from No-Args Constructor";
    }

    // Parameterized constructor - can be used by @Bean methods in configuration
    public MyFirstClass(String name) {
        this.name = name;
    }

    /*
     * BUSINESS METHODS:
     * These methods use the injected Environment to access system properties
     */

    public String GetEnvironment() {
        // Using injected Environment to get Java version
        return environment.getProperty("java.version");
    }

    public String GetOs() {
        // Using injected Environment to get OS name
        return environment.getProperty("os.name");
    }

    public String GetProperty() {
        // Using injected Environment to get custom property from properties file
        return environment.getProperty("my.customer.property");
    }

    public String HelloWorld() {
        return name;
    }

    /*
     * SETTER INJECTION EXAMPLES (RECOMMENDED for optional dependencies):
     * These demonstrate setter-based dependency injection
     * WHY SETTER INJECTION IS BETTER THAN FIELD INJECTION:
     * 1. Can be tested easily (call setter with mock objects)
     * 2. Can be made optional (don't call @Autowired if dependency is optional)
     * 3. Clearer what dependencies the class needs
     * 4. Follows JavaBean conventions
     */

    // SETTER INJECTION - Alternative way to inject Environment
    // This method will be called by Spring after object creation
    @Autowired
    public void setEnvironmentViaSetter(Environment environment) {
        this.environment = environment;
        System.out.println("Environment injected via SETTER INJECTION");
    }

    /*
     * GETTERS AND SETTERS for @Value injected fields:
     */

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

/*
 * IMPORTANT ANNOTATIONS EXPLANATION:
 *
 * @Component: Marks class as Spring-managed component
 * @Service: Specialized @Component for service layer
 * @Repository: Specialized @Component for data access layer
 * @Controller: Specialized @Component for web controllers
 * @Configuration: Class that defines bean configurations
 *
 * @Autowired: Tells Spring to inject dependency automatically
 * @Value: Injects values from properties files or hardcoded values
 * @PropertySource: Loads additional property files
 * @Qualifier: Specifies which bean to inject when multiple candidates exist
 * @Primary: Marks bean as primary choice when multiple candidates exist
 */