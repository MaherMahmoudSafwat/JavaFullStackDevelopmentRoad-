package com.Project1.demo.Repository;

import com.Project1.demo.Models.Laptop;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Data Access Layer class for Laptop entities.
 *
 * Demonstrates dual annotation with @Component and @Repository,
 * though @Repository alone would be sufficient.
 */
@Component  // Marks this class as a Spring-managed component
@Repository // Specialized annotation for data access layer components
public class LaptopRepository {

    /**
     * Saves a laptop entity (simulated operation)
     * @param lap The laptop entity to be saved
     */
    public void save(Laptop lap) {
        // In a real implementation, this would interact with a database
        System.out.println("Saved in Database..");
    }
}

/*
 * Understanding @Component vs @Repository:
 * ========================================
 *
 * 1. @Component:
 *    - Generic stereotype annotation for any Spring-managed component
 *    - Base annotation for other stereotypes (@Service, @Repository, @Controller)
 *    - Used when a class doesn't fit the more specific stereotypes
 *    - Example:
 *      @Component
 *      public class GenericUtility { ... }
 *
 * 2. @Repository:
 *    - Specialized @Component for data access layer (DAO pattern)
 *    - Provides additional benefits beyond @Component:
 *      a) Automatic exception translation:
 *         - Converts database exceptions to Spring's DataAccessException hierarchy
 *      b) Makes database operations more Spring-friendly
 *      c) Clearly indicates the class's role in architecture
 *    - Example:
 *      @Repository
 *      public class UserRepository { ... }
 *
 * Key Differences:
 * - @Repository is a specialization of @Component
 * - @Repository adds persistence-layer specific behavior
 * - @Component is more generic
 * - Both make the class eligible for component scanning
 *
 * Best Practice:
 * - Use @Repository for database access classes
 * - Use @Component for generic utility classes
 * - Don't need both annotations together (redundant)
 *
 * Why both work here:
 * - Spring treats @Repository as qualified @Component
 * - Having both doesn't cause issues but is unnecessary
 * - @Repository alone would be the cleaner approach
 */