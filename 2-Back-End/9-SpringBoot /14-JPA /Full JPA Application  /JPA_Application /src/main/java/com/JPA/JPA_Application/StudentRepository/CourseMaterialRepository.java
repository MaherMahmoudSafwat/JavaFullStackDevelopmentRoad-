package com.JPA.JPA_Application.StudentRepository;

import com.JPA.JPA_Application.Model.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Repository interface for CourseMaterial entity.
 * Extends JpaRepository to inherit CRUD operations and query methods.
 *
 * Demonstrates Spring Data JPA repository capabilities.
 */
public interface CourseMaterialRepository
        extends JpaRepository<CourseMaterial, Long> { // Entity type and ID type

    /**
     * Custom query method to find materials by URL containing a string
     * Derived query - Spring Data JPA automatically implements this
     */
    List<CourseMaterial> findByUrlContaining(String urlFragment);

    /**
     * Custom JPQL query to find materials by course title
     * @param title Course title to search for
     * @return List of matching course materials
     */
    @Query("SELECT cm FROM CourseMaterial cm JOIN cm.course c WHERE c.title = :title")
    List<CourseMaterial> findByCourseTitle(@Param("title") String title);

    /**
     * Native SQL query to count materials with URLs longer than specified length
     * @param minLength Minimum URL length
     * @return Count of materials
     */
    @Query(
            value = "SELECT COUNT(*) FROM course_material WHERE LENGTH(url) > :minLength",
            nativeQuery = true
    )
    long countByUrlLengthGreaterThan(@Param("minLength") int minLength);
}