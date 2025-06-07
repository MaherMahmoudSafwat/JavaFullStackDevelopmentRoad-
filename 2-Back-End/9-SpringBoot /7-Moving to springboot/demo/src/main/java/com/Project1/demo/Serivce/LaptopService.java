package com.Project1.demo.Serivce;

import com.Project1.demo.Models.Laptop;
import com.Project1.demo.Repository.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer class for Laptop-related business logic.
 * Demonstrates the use of @Service annotation and service layer responsibilities.
 */
@Service // Marks this class as a Spring service layer component
public class LaptopService {

    @Autowired // Injects the LaptopRepository dependency
    private LaptopRepository laptopRepository;

    /**
     * Adds a laptop through the repository layer
     * @param laptop The laptop entity to be persisted
     */
    public void add(Laptop laptop) {
        laptopRepository.save(laptop); // Delegates to repository for persistence
    }

    /**
     * Business logic to determine if a laptop is suitable for programming
     * @param lap The laptop to evaluate
     * @return boolean indicating suitability (simplified to always return true here)
     */
    public boolean isGoodForProg(Laptop lap) {
        // In a real implementation, this would contain actual business logic
        // evaluating laptop specifications
        return true;
    }
}

/*
 * Understanding @Service and Service Layer Responsibilities:
 * =========================================================
 *
 * @Service Annotation:
 * - Specialization of @Component for business service layer
 * - Semantically indicates a class contains business logic
 * - Eligible for component scanning like @Component
 * - Transaction boundaries are typically defined at this layer
 *
 * Service Layer Responsibilities:
 * 1. Contains core business logic
 * 2. Coordinates transactions
 * 3. Orchestrates calls between:
 *    - Controllers (presentation layer)
 *    - Repositories (data access layer)
 * 4. Implements business rules and validation
 *
 * Key Differences from Other Stereotypes:
 *
 * 1. vs @Repository:
 *    - @Service: Business logic and transactions
 *    - @Repository: Data access and persistence
 *    Example Flow: Controller -> Service -> Repository
 *
 * 2. vs @Component:
 *    - @Service is semantically specific to business layer
 *    - @Component is generic (use when no other stereotype fits)
 *
 * 3. vs @Controller:
 *    - @Controller handles HTTP requests/responses
 *    - @Service contains business logic called by controllers
 *
 * Best Practices for Service Layer:
 * - Keep business logic in service classes
 * - Services should be stateless and thread-safe
 * - Use @Transactional at service layer for atomic operations
 * - Services should delegate persistence operations to repositories
 *
 * Typical Service Method Structure:
 * 1. Validate inputs
 * 2. Apply business rules
 * 3. Call repositories for persistence
 * 4. Handle transactions
 * 5. Return results to caller
 *
 * Suggested Improvement for this Class:
 * - Add proper business logic to isGoodForProg()
 * - Consider adding @Transactional for write operations
 * - Add proper exception handling
 * - Follow field naming conventions (laptopRepository instead of Laptop)
 */
