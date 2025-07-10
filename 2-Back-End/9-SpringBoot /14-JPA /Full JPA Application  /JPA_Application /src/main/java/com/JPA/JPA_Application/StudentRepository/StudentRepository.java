package com.JPA.JPA_Application.StudentRepository;

import com.JPA.JPA_Application.Model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * JPA Repository interface for Student entity.
 * Extends JpaRepository to inherit standard CRUD operations.
 *
 * JPA Key Concepts Demonstrated:
 * 1. Repository Pattern - Abstraction layer for database operations
 * 2. Derived Query Methods - Auto-implemented based on method names
 * 3. JPQL - Java Persistence Query Language (object-oriented queries)
 * 4. Native SQL - Direct database queries
 * 5. Parameter Binding - Both positional and named parameters
 * 6. Modifying Queries - For UPDATE/DELETE operations
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    // ========== DERIVED QUERIES ==========
    // Spring Data JPA automatically implements these based on method name conventions

    /**
     * Derived query: SELECT * FROM students WHERE first_name = ?
     * @param firstName Exact match for first name
     * @return List of matching Student entities
     */
    List<Student> findByFirstName(String firstName);

    /**
     * Derived query with LIKE pattern: WHERE email LIKE 'prefix%'
     * @param emailPrefix Starting characters of email
     * @return List of Students with matching email prefixes
     */
    List<Student> findByEmailIdStartingWith(String emailPrefix);

    /**
     * Derived query with comparison: WHERE student_id > ?
     * @param Number Threshold ID value
     * @return Students with IDs greater than specified value
     */
    List<Student> findByStudentIdGreaterThan(int Number);

    // ========== JPQL QUERIES ==========
    // Object-oriented queries using entity class/field names

    /**
     * JPQL projection query - returns only first names
     * @param emailId Email to search for
     * @return List of first names (not full entities)
     */
    @Query("SELECT s.firstName FROM Student s WHERE s.emailId = ?1")
    List<String> getStudentFirstNameByEmailAddress(String emailId);

    // ========== NATIVE SQL QUERIES ==========
    // Direct database queries using table/column names

    /**
     * Native SQL query using actual table/column names
     * @param EmailId Email to search for
     * @return Complete Student entities matching email
     */
    @Query(
            value = "SELECT * FROM tbl_student WHERE email_address = ?1",
            nativeQuery = true
    )
    List<Student> getAllStudentsDataByEmailAddress(String EmailId);

    // ========== NAMED PARAMETERS ==========
    // Safer alternative to positional parameters

    /**
     * JPQL with named parameter binding
     * @param EmailId Email to search for (bound to :emailId)
     * @return Matching Student entities
     */
    @Query("SELECT s FROM Student s WHERE s.emailId = :emailId")
    List<Student> getStudentsDataByEmailIdNamedParameter(@Param("emailId") String EmailId);

    // ========== MODIFYING QUERIES ==========
    // Requires special annotations for write operations

    /**
     * JPQL UPDATE query
     * @param FirstName New first name value
     * @param emailId Email to identify records to update
     * @return Number of affected rows
     */
    @Transactional  // Required for any modifying operation
    @Modifying      // Marks this as data-modifying query
    @Query("UPDATE Student s SET s.firstName = ?1 WHERE s.emailId = ?2")
    int UpdateStudentData(String FirstName, String emailId);

    // ========== ADDITIONAL QUERY EXAMPLES ==========

    /**
     * Case-insensitive search
     * @param firstName Name to search (case ignored)
     * @return Matching Students regardless of case
     */
    List<Student> findByFirstNameIgnoreCase(String firstName);

    /**
     * Contains search (LIKE %pattern%)
     * @param emailSubstring Text to search within emails
     * @return Students containing the substring
     */
    List<Student> findByEmailIdContaining(String emailSubstring);

    /**
     * IN clause with collection parameter
     * @param ids List of IDs to search for
     * @return Students with IDs in the provided list
     */
    @Query("SELECT s FROM Student s WHERE s.studentId IN :ids")
    List<Student> findStudentsByIds(@Param("ids") List<Long> ids);

    /**
     * DELETE operation example
     * @param emailId Email to identify records to delete
     * @return Number of deleted records
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM Student s WHERE s.emailId = ?1")
    int deleteByEmailId(String emailId);
}
