package com.Project1.demo;

import com.Project1.demo.Models.Laptop;
import com.Project1.demo.Models.Student;
import com.Project1.demo.Serivce.LaptopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Main application class that serves as the entry point for the Spring Boot application.
 *
 * @SpringBootApplication is a convenience annotation that combines:
 * - @Configuration: Marks this class as a configuration class
 * - @EnableAutoConfiguration: Enables Spring Boot's auto-configuration
 * - @ComponentScan: Enables component scanning in current package and sub-packages
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// Create and run the Spring Application context
		ApplicationContext Context = SpringApplication.run(DemoApplication.class, args);

		// Get a Student bean from the Spring container
		Student St1 = Context.getBean(Student.class);
		System.out.println(St1.toString());
		St1.Code(); // Execute the Code method on Student

		// Get the LaptopService bean and a Laptop bean
		LaptopService Service = Context.getBean(LaptopService.class);
		Laptop Laptop1 = Context.getBean(Laptop.class);

		// Use the service to add the laptop
		Service.add(Laptop1);
	}
}

/*
 * Spring Stereotype Annotations Explained:
 * ========================================
 *
 * 1. @Controller:
 *    - Used in presentation layer (MVC controllers)
 *    - Handles HTTP requests and returns responses
 *    - Typically used with @RequestMapping annotations
 *    - Example:
 *      @Controller
 *      public class MyController {
 *          @GetMapping("/path")
 *          public String handleRequest() { ... }
 *      }
 *
 * 2. @Service:
 *    - Business service facade layer
 *    - Contains business logic and transaction boundaries
 *    - Acts as a middle layer between controllers and repositories
 *    - Example:
 *      @Service
 *      public class MyService {
 *          @Autowired
 *          private MyRepository repo;
 *
 *          public void businessMethod() { ... }
 *      }
 *
 * 3. @Repository:
 *    - Data access layer (DAO pattern)
 *    - Handles database operations and data persistence
 *    - Automatically translates exceptions to Spring's DataAccessException
 *    - Example:
 *      @Repository
 *      public class MyRepository {
 *          @PersistenceContext
 *          private EntityManager em;
 *
 *          public void saveData(Entity e) { ... }
 *      }
 *
 * Key Differences:
 * - @Controller: For web controllers (handles HTTP)
 * - @Service: For business logic (transactional boundary)
 * - @Repository: For data access (database operations)
 *
 * All are specializations of @Component and can be autodetected during scanning.
 * The different names help clarify the architectural layer and purpose.
 */
