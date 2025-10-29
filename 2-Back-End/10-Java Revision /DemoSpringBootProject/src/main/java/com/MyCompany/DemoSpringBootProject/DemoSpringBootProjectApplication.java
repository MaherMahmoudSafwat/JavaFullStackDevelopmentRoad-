package com.MyCompany.DemoSpringBootProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MAIN APPLICATION CLASS - Entry Point of Spring Boot Application
 *
 * This is the heart of our Spring Boot application where everything starts.
 * Think of this as the "main method" that launches our entire Spring ecosystem.
 */

// @SpringBootApplication is a POWERFUL composite annotation that combines 3 important annotations:
// 1. @EnableAutoConfiguration - Tells Spring Boot to automatically configure your application
// 2. @ComponentScan - Tells Spring to scan for components in current package and sub-packages
// 3. @Configuration - Marks this class as a configuration class (can define beans)
@SpringBootApplication
public class DemoSpringBootProjectApplication {

	public static void main(String[] args) {

		/*
		 * IOC CONTAINER CREATION AND STARTUP:
		 * SpringApplication.run() creates the Spring IOC Container (ApplicationContext)
		 * This container will manage all our beans, handle dependency injection,
		 * and control the entire lifecycle of our application components.
		 */
		var ctx = SpringApplication.run(DemoSpringBootProjectApplication.class, args);

		/*
		 * DEPENDENCY INJECTION IN ACTION:
		 * Here we're getting beans from the IOC container.
		 * Spring has already created these objects and wired their dependencies.
		 */

		// Getting MyServiceClass bean from IOC container
		// Spring automatically resolved its dependencies (MyFirstClass) via Constructor Injection
		MyServiceClass serviceBean = ctx.getBean(MyServiceClass.class);
		serviceBean.TellTheStory();

		// Getting MyFirstClass bean from IOC container
		// Spring chose the @Primary bean when multiple beans of same type exist
		MyFirstClass firstClassBean = ctx.getBean(MyFirstClass.class);

		// Demonstrating property injection and environment access
		System.out.println("Java Version: " + firstClassBean.GetEnvironment());
		System.out.println("Operating System: " + firstClassBean.GetOs());
		System.out.println("Custom Property: " + firstClassBean.GetProperty());
		System.out.println("Hardcoded Number: " + firstClassBean.getNumber());
		System.out.println("Property from file: " + firstClassBean.getPropertyName());
	}
}