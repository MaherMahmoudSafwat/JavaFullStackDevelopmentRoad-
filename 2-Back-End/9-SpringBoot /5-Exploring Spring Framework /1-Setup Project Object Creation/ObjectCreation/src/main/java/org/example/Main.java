package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // ===================================================================
        // 1. Creating the Spring IoC Container (ApplicationContext)
        // ===================================================================
        // - 'ClassPathXmlApplicationContext' loads the Spring configuration from XML file
        // - This creates and manages all beans defined in "Spring.XML"
        // - The container will:
        //   a) Read the XML configuration
        //   b) Create all bean instances
        //   c) Wire dependencies between beans
        /*
        IoC Container (ApplicationContext):
        The "brain" of Spring that manages all your beans
        Reads configuration (XML in this case) to know what beans to create
        Handles dependency injection automatically
        */
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.XML");

        // ===================================================================
        // 2. Getting Beans from the Container
        // ===================================================================
        // - 'getBean()' retrieves a Spring-managed object from the container
        // - We specify the bean name ("Alien") that matches the XML definition
        // - Casting is needed because getBean() returns Object by default
        Alien obj = (Alien) context.getBean("Alien");

        // ===================================================================
        // 3. Working with the Bean
        // ===================================================================
        // - We can modify and use the bean like any normal Java object
        // - Changes to this instance will be visible to other parts of the app
        //   that get the same bean (depends on bean scope - singleton/prototype)
        obj.Age = 21;
        System.out.println("First Alien instance age: " + obj.Age);
        obj.Code();

        // ===================================================================
        // 4. Getting Another Bean Instance
        // ===================================================================
        // - This gets what might be the SAME or DIFFERENT instance depending on:
        //   a) Singleton scope (default): Same instance
        //   b) Prototype scope: New instance each time
        Alien obj1 = (Alien) context.getBean("Alien");

        // - If scope is singleton (default), obj1 will show age=21 because it's
        //   the same instance we modified earlier
        // - If scope is prototype, age will be whatever default value is set in XML
        System.out.println("Second Alien instance age: " + obj1.Age);
        obj1.Code();

        // ===================================================================
        // Important Notes:
        // 1. The ApplicationContext automatically closes when the JVM shuts down
        // 2. For manual cleanup, we could call:
        //    ((ClassPathXmlApplicationContext) context).close();
        // ===================================================================
    }
}