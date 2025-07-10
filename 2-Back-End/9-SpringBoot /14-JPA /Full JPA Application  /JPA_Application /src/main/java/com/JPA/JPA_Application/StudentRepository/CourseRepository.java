package com.JPA.JPA_Application.StudentRepository;

import com.JPA.JPA_Application.Model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Course entity.
 * Provides CRUD operations and custom query methods for Course management.
 *
 * Extends JpaRepository which provides:
 * - Standard CRUD operations
 * - Pagination and sorting support
 * - Query method capability
 */
@Repository // Explicitly declares this as a Spring repository component
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Inherits all basic CRUD operations from JpaRepository

    // =============================================
    // 1. Derived Query Methods (Auto-implemented)
    // =============================================

    /**
     * Finds courses by exact title match
     * @param title The exact title to search for
     * @return List of matching courses
     */
    List<Course> findByTitle(String title);

    /**
     * Finds courses with titles containing the given string (case-insensitive)
     * @param titlePart Partial title to search for
     * @return List of matching courses
     */
    List<Course> findByTitleContainingIgnoreCase(String titlePart);

    /**
     * Finds courses with credit value greater than specified
     * @param credit Minimum credit value
     * @return List of matching courses
     */
    List<Course> findByCreditGreaterThan(String credit);

    // =============================================
    // 2. JPQL Custom Queries
    // =============================================

    /**
     * Custom query to find courses by teacher's last name
     * @param lastName Teacher's last name
     * @return List of matching courses
     */
    @Query("SELECT c FROM Course c JOIN c.teacher t WHERE t.lastName = :lastName")
    List<Course> findByTeacherLastName(@Param("lastName") String lastName);

    /**
     * Custom query to count courses by credit value
     * @param credit The credit value to count
     * @return Number of courses with this credit value
     */
    @Query("SELECT COUNT(c) FROM Course c WHERE c.credit = :credit")
    long countByCreditValue(@Param("credit") String credit);

    // =============================================
    // 3. Native SQL Queries
    // =============================================

    /**
     * Native query to find courses with long titles
     * @param minLength Minimum title length
     * @return List of matching courses
     */
    @Query(
            value = "SELECT * FROM courses WHERE LENGTH(title) > :minLength",
            nativeQuery = true
    )
    List<Course> findCoursesWithLongTitles(@Param("minLength") int minLength);

    // =============================================
    // 4. Pagination and Sorting
    // =============================================

    /**
     * Finds courses by title with pagination
     * @param title Title to search for
     * @param pageable Pagination information
     * @return Page of courses
     */
    Page<Course> findByTitle(String title, Pageable pageable);

    /**
     * Finds all courses sorted by the given property
     * @param sort Sorting criteria
     * @return List of sorted courses
     */
    List<Course> findAll(Sort sort);
}