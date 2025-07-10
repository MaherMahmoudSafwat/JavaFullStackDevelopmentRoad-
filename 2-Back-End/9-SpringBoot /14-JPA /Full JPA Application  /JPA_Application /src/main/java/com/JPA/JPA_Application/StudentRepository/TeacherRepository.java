package com.JPA.JPA_Application.StudentRepository;

import com.JPA.JPA_Application.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA Repository interface for Teacher entity.
 *
 * This minimal interface demonstrates the power of Spring Data JPA:
 * - Inherits 18+ ready-to-use CRUD operations from JpaRepository
 * - Requires no implementation - Spring provides proxy implementation automatically
 * - Follows Spring Data's repository pattern for database access
 *
 * Basic JPA Repository Structure:
 * JpaRepository<EntityClass, PrimaryKeyType>
 *
 * Inherited CRUD operations include:
 * - save(), saveAll() - for insert/update
 * - findById(), findAll() - for reading
 * - delete(), deleteAll() - for removal
 * - count(), existsById() - for utility
 *
 * Even with empty body, this interface provides:
 * 1. Complete CRUD functionality
 * 2. Pagination and sorting support
 * 3. Batch operation capability
 * 4. Automatic transaction management
 *
 * Usage Example:
 * Teacher teacher = teacherRepository.findById(1L).orElseThrow();
 * teacherRepository.save(new Teacher(...));
 * teacherRepository.deleteById(1L);
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // No methods needed - inherits all basic CRUD operations

    // Future custom queries can be added here as needed:
    // 1. Derived queries (findBy...)
    // 2. @Query annotated JPQL/native SQL
    // 3. Custom repository extensions
}
