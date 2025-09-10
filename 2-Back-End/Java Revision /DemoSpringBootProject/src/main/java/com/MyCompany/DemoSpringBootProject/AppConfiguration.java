package com.MyCompany.DemoSpringBootProject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Profile;

/**
 * SPRING CONFIGURATION CLASS - Bean Factory
 *
 * This class demonstrates how to manually create and configure beans
 * instead of relying on component scanning (@Component).
 *
 * WHY USE @Configuration CLASSES:
 * 1. Full control over bean creation
 * 2. Can configure third-party library beans (that you can't add @Component to)
 * 3. Complex initialization logic
 * 4. Conditional bean creation
 * 5. Profile-specific beans
 */

// @Configuration tells Spring this class contains bean definitions
// Spring will process this class and register all @Bean methods as beans in IOC container
@Configuration
public class AppConfiguration {

    /*
     * BEAN CREATION METHODS:
     * Each @Bean method creates a bean that Spring manages in IOC container
     */

    // Basic bean creation - Spring will call this method and register returned object as bean
    @Bean
    public MyFirstClass MyFirstClass() {
        // Creating bean with specific constructor argument
        return new MyFirstClass("This is my first class from @Bean method");
    }

    /*
     * NAMED BEAN with QUALIFIER:
     * When multiple beans of same type exist, we need ways to distinguish them
     */
    @Bean("XYZ")  // Custom bean name instead of method name
    @Qualifier("XYZ")  // Qualifier to identify this specific bean
    public MyFirstClass MySecondClass() {
        return new MyFirstClass("This is my second class with @Qualifier");
    }

    /*
     * PRIMARY BEAN:
     * When multiple beans of same type exist, @Primary marks the default choice
     * This bean will be injected when no specific qualifier is mentioned
     */
    @Bean
    @Primary  // This bean will be chosen by default when injecting MyFirstClass
    public MyFirstClass MyThirdClass() {
        return new MyFirstClass("This is my PRIMARY third class");
    }

    /*
     * PROFILE-SPECIFIC BEANS:
     * Beans that are created only when specific profiles are active
     */

    // This bean will only be created when "development" profile is active
    @Bean
    @Profile("development")  // Only active in development environment
    public MyFirstClass devSpecificBean() {
        return new MyFirstClass("Development Environment Bean");
    }

    // This bean will only be created when "production" profile is active
    @Bean
    @Profile("production")  // Only active in production environment
    public MyFirstClass prodSpecificBean() {
        return new MyFirstClass("Production Environment Bean");
    }

    // Multiple profiles - active when ANY of these profiles is active
    @Bean
    @Profile({"staging", "testing"})  // Active in staging OR testing
    public MyFirstClass testingBean() {
        return new MyFirstClass("Testing/Staging Environment Bean");
    }

    /*
     * SCOPED BEANS:
     * Control the lifecycle and creation frequency of beans
     */

    @Bean
    @Scope("singleton")  // Default scope - one instance per Spring container
    public MyFirstClass singletonBean() {
        return new MyFirstClass("Singleton Scoped Bean");
    }

    @Bean
    @Scope("prototype")  // New instance every time bean is requested
    public MyFirstClass prototypeBean() {
        return new MyFirstClass("Prototype Scoped Bean");
    }

    /*
     * WEB-SPECIFIC SCOPES (only available in web applications):
     * @Scope("request") - New instance per HTTP request
     * @Scope("session") - New instance per HTTP session
     * @Scope("application") - One instance per ServletContext
     */
}

/*
 * IMPORTANT CONCEPTS EXPLAINED:
 *
 * IOC (INVERSION OF CONTROL):
 * - Traditional: Your code creates and manages object dependencies
 * - IOC: Spring container creates and manages objects for you
 * - You just declare what you need, Spring provides it
 *
 * DEPENDENCY INJECTION (DI):
 * - The process of providing dependencies to objects
 * - Spring automatically wires dependencies between beans
 * - Three types: Constructor, Setter, Field injection
 *
 * BEAN LIFECYCLE:
 * 1. Spring scans for classes marked with @Component, @Service, etc.
 * 2. Spring processes @Configuration classes and @Bean methods
 * 3. Spring creates bean instances
 * 4. Spring injects dependencies via @Autowired
 * 5. Spring calls initialization methods (@PostConstruct)
 * 6. Bean is ready for use
 * 7. Spring calls destruction methods (@PreDestroy) on shutdown
 */