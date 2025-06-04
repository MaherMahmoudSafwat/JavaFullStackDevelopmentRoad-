package com.example.SpringBootFirstProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// This annotation is the heart of Spring Boot!
// It combines 3 key annotations:
// 1. @Configuration: Marks this as a configuration class
// 2. @EnableAutoConfiguration: Lets Spring Boot auto-configure your app
// 3. @ComponentScan: Tells Spring to look for components in this package
@SpringBootApplication
public class SpringBootFirstProjectApplication {

	public static void main(String[] args) {
		// Spring Boot starts here! This does 3 magic things:
		// 1. Starts the embedded server (Tomcat)
		// 2. Creates the Spring "application context" (container for all beans)
		// 3. Auto-configures everything
		ApplicationContext context = SpringApplication.run(SpringBootFirstProjectApplication.class, args);

		// Getting the Programmer bean from Spring's container
		// (Spring creates and manages this object for us)
		Programmer programmer = context.getBean(Programmer.class);
		programmer.code(); // Using the bean

		// Getting the Laptop bean directly (though usually we autowire it)
		Laptop laptop = context.getBean(Laptop.class);
		laptop.coding();
	}
}