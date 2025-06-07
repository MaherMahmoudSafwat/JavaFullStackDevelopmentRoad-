package org.example;

import org.example.Configurations.AppConfigurations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main class demonstrating different ways to use Spring IoC container
 * with both annotation-based and Java configuration approaches.
 */
public class Main {
    public static void main(String[] args) {
        /*
         * SECTION 1: Using AppConfig (explicit bean definitions)
         * -----------------------------------------------------
         * This section demonstrates working with explicitly defined beans
         * from the AppConfig configuration class
         */
        // ApplicationContext Context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Getting bean by name "St1" (defined in AppConfig with multiple names)
        // Student St = Context.getBean("St1", Student.class);
        // System.out.println(St.toString());

        // Getting another instance of the same bean (prototype scope)
        // Student St2 = Context.getBean("St1", Student.class);
        // System.out.println(St2.toString());

        // Getting a differently configured Student bean
        // Student St3 = Context.getBean("Student1", Student.class);
        // System.out.println(St3.toString());
        // St3.ComputerCode(); // Demonstrates device dependency injection

        /*
         * SECTION 2: Using AppConfigurations (component scanning)
         * -------------------------------------------------------
         * This section demonstrates working with auto-detected components
         * via component scanning
         */
        ApplicationContext Context = new AnnotationConfigApplicationContext(AppConfigurations.class);

        // Getting Student bean (auto-detected via @Component)
        Student St4 = Context.getBean(Student.class);

        // Output default age set by @Value
        System.out.println(St4.getStudentAge()); // Prints: 21

        // Execute code on the injected device
        St4.ComputerCode(); // Behavior depends on which Device implementation is injected
    }
}





