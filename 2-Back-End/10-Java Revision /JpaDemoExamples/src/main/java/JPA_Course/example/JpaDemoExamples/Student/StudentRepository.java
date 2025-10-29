package JPA_Course.example.JpaDemoExamples.Student;

import JPA_Course.example.JpaDemoExamples.Examples.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>
{
    // ==================== BASIC DERIVED QUERIES ====================

    /**
     * FIND BY SINGLE PROPERTY
     * Method name: findByFirstName
     * Generated SQL: SELECT * FROM students WHERE first_name = ?
     */
    List<Student> findByFirstName(String firstName);

    /**
     * FIND BY MULTIPLE PROPERTIES (AND)
     * Method name: findByFirstNameAndLastName
     * Generated SQL: SELECT * FROM students WHERE first_name = ? AND last_name = ?
     */
    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    /**
     * FIND BY MULTIPLE PROPERTIES (OR)
     * Method name: findByFirstNameOrLastName
     * Generated SQL: SELECT * FROM students WHERE first_name = ? OR last_name = ?
     */
    List<Student> findByFirstNameOrLastName(String firstName, String lastName);

    // ==================== STRING MATCHING QUERIES ====================

    /**
     * FIND BY STRING CONTAINING
     * Method name: findByFirstNameContaining
     * Generated SQL: SELECT * FROM students WHERE first_name LIKE %?%
     */
    List<Student> findByFirstNameContaining(String nameContains);

    /**
     * FIND BY STRING STARTING WITH
     * Method name: findByFirstNameStartingWith
     * Generated SQL: SELECT * FROM students WHERE first_name LIKE ?%
     */
    List<Student> findByFirstNameStartingWith(String namePrefix);

    /**
     * FIND BY STRING ENDING WITH
     * Method name: findByFirstNameEndingWith
     * Generated SQL: SELECT * FROM students WHERE first_name LIKE %?
     */
    List<Student> findByFirstNameEndingWith(String nameSuffix);

    /**
     * FIND BY STRING IGNORING CASE
     * Method name: findByFirstNameIgnoreCase
     * Generated SQL: SELECT * FROM students WHERE UPPER(first_name) = UPPER(?)
     */
    List<Student> findByFirstNameIgnoreCase(String firstName);

    /**
     * COMBINED STRING OPERATIONS
     * Method name: findByFirstNameContainingIgnoreCase
     * Generated SQL: SELECT * FROM students WHERE UPPER(first_name) LIKE UPPER(%?%)
     */
    List<Student> findByFirstNameContainingIgnoreCase(String nameContains);

    // ==================== COMPARISON QUERIES ====================

    /**
     * FIND BY PROPERTY NOT EQUAL
     * Method name: findByFirstNameNot
     * Generated SQL: SELECT * FROM students WHERE first_name != ?
     */
    List<Student> findByFirstNameNot(String firstName);

    /**
     * FIND BY IN COLLECTION
     * Method name: findByFirstNameIn
     * Generated SQL: SELECT * FROM students WHERE first_name IN (?, ?, ?)
     */
    List<Student> findByFirstNameIn(List<String> firstNames);

    /**
     * FIND BY NOT IN COLLECTION
     * Method name: findByFirstNameNotIn
     * Generated SQL: SELECT * FROM students WHERE first_name NOT IN (?, ?, ?)
     */
    List<Student> findByFirstNameNotIn(List<String> firstNames);

    // ==================== DATE/TIME QUERIES ====================

    /**
     * FIND BY DATE AFTER
     * Method name: findByCreatedAtAfter
     * Generated SQL: SELECT * FROM students WHERE created_at > ?
     */
    List<Student> findByCreatedAtAfter(LocalDateTime date);

    /**
     * FIND BY DATE BEFORE
     * Method name: findByCreatedAtBefore
     * Generated SQL: SELECT * FROM students WHERE created_at < ?
     */
    List<Student> findByCreatedAtBefore(LocalDateTime date);

    /**
     * FIND BY DATE BETWEEN
     * Method name: findByCreatedAtBetween
     * Generated SQL: SELECT * FROM students WHERE created_at BETWEEN ? AND ?
     */
    List<Student> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // ==================== NULL CHECKS ====================

    /**
     * FIND BY PROPERTY IS NULL
     * Method name: findByUpdatedAtIsNull
     * Generated SQL: SELECT * FROM students WHERE updated_at IS NULL
     */
    List<Student> findByUpdatedAtIsNull();

    /**
     * FIND BY PROPERTY IS NOT NULL
     * Method name: findByUpdatedAtIsNotNull
     * Generated SQL: SELECT * FROM students WHERE updated_at IS NOT NULL
     */
    List<Student> findByUpdatedAtIsNotNull();

    // ==================== EMBEDDED OBJECT QUERIES ====================

    /**
     * FIND BY EMBEDDED PROPERTY
     * Method name: findByHomeAddress_City
     * Generated SQL: SELECT * FROM students WHERE city = ?
     *
     * This navigates through the embedded Address object to access city field
     */
    List<Student> findByHomeAddress_City(String city);

    /**
     * FIND BY EMBEDDED PROPERTY WITH MULTIPLE CONDITIONS
     * Method name: findByHomeAddress_StateAndHomeAddress_City
     * Generated SQL: SELECT * FROM students WHERE state = ? AND city = ?
     */
    List<Student> findByHomeAddress_StateAndHomeAddress_City(String state, String city);

    /**
     * FIND BY BILLING ADDRESS
     * Method name: findByBillingAddress_ZipCode
     * Generated SQL: SELECT * FROM students WHERE billing_zip = ?
     *
     * Uses the custom column name from @AttributeOverride
     */
    List<Student> findByBillingAddress_ZipCode(String zipCode);

    // ==================== RELATIONSHIP QUERIES ====================

    /**
     * FIND BY RELATED ENTITY PROPERTY
     * Method name: findByCourses_CourseName
     * Generated SQL: SELECT s.* FROM students s JOIN students_courses sc ON s.id = sc.student_id
     *                JOIN courses c ON sc.course_id = c.id WHERE c.course_name = ?
     */
    List<Student> findByCourses_CourseName(String courseName);

    /**
     * FIND BY RELATED ENTITY PROPERTY
     * Method name: findByTeacher_TeacherName
     * Generated SQL: SELECT s.* FROM students s JOIN teachers t ON s.teacher_id = t.id
     *                WHERE t.teacher_name = ?
     */
    List<Student> findByTeacher_TeacherName(String teacherName);

    // ==================== ORDERING ====================

    /**
     * FIND ALL ORDERED BY PROPERTY
     * Method name: findAllByOrderByFirstNameAsc
     * Generated SQL: SELECT * FROM students ORDER BY first_name ASC
     */
    List<Student> findAllByOrderByFirstNameAsc();

    /**
     * FIND ALL ORDERED BY MULTIPLE PROPERTIES
     * Method name: findAllByOrderByLastNameAscFirstNameDesc
     * Generated SQL: SELECT * FROM students ORDER BY last_name ASC, first_name DESC
     */
    List<Student> findAllByOrderByLastNameAscFirstNameDesc();

    /**
     * FIND WITH CONDITION AND ORDERING
     * Method name: findByFirstNameContainingOrderByLastName
     * Generated SQL: SELECT * FROM students WHERE first_name LIKE %?% ORDER BY last_name
     */
    List<Student> findByFirstNameContainingOrderByLastName(String nameContains);

    // ==================== LIMITING RESULTS ====================

    /**
     * FIND FIRST RESULT
     * Method name: findFirstByOrderByCreatedAtDesc
     * Generated SQL: SELECT * FROM students ORDER BY created_at DESC LIMIT 1
     */
    Optional<Student> findFirstByOrderByCreatedAtDesc();

    /**
     * FIND TOP N RESULTS
     * Method name: findTop5ByOrderByCreatedAtDesc
     * Generated SQL: SELECT * FROM students ORDER BY created_at DESC LIMIT 5
     */
    List<Student> findTop5ByOrderByCreatedAtDesc();

    /**
     * FIND FIRST WITH CONDITION
     * Method name: findFirstByFirstNameOrderByCreatedAtDesc
     * Generated SQL: SELECT * FROM students WHERE first_name = ? ORDER BY created_at DESC LIMIT 1
     */
    Optional<Student> findFirstByFirstNameOrderByCreatedAtDesc(String firstName);

    // ==================== COUNT AND EXISTS QUERIES ====================

    /**
     * COUNT BY CONDITION
     * Method name: countByFirstName
     * Generated SQL: SELECT COUNT(*) FROM students WHERE first_name = ?
     */
    long countByFirstName(String firstName);

    /**
     * COUNT BY MULTIPLE CONDITIONS
     * Method name: countByFirstNameAndLastName
     * Generated SQL: SELECT COUNT(*) FROM students WHERE first_name = ? AND last_name = ?
     */
    long countByFirstNameAndLastName(String firstName, String lastName);

    /**
     * EXISTS BY CONDITION
     * Method name: existsByEmail
     * Generated SQL: SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM students WHERE email = ?
     */
    boolean existsByEmail(String email);

    /**
     * EXISTS BY RELATIONSHIP
     * Method name: existsByCourses_CourseCode
     * Generated SQL: Complex EXISTS query with joins
     */
    boolean existsByCourses_CourseCode(String courseCode);

    // ==================== DELETE QUERIES ====================

    /**
     * DELETE BY CONDITION
     * Method name: deleteByFirstName
     * Generated SQL: DELETE FROM students WHERE first_name = ?
     * Returns: Number of deleted records
     */
    long deleteByFirstName(String firstName);

    /**
     * DELETE BY DATE CONDITION
     * Method name: deleteByCreatedAtBefore
     * Generated SQL: DELETE FROM students WHERE created_at < ?
     */
    long deleteByCreatedAtBefore(LocalDateTime cutoffDate);

    // ==================== CUSTOM QUERIES ====================

    /**
     * CUSTOM JPQL QUERY
     * When method names become too complex, use @Query
     */
    @Query("SELECT s FROM Student s WHERE s.firstName LIKE %:name% OR s.lastName LIKE %:name%")
    List<Student> findByFullNameContaining(@Param("name") String name);

    /**
     * COMPLEX QUERY WITH MULTIPLE JOINS
     */
    @Query("SELECT DISTINCT s FROM Student s " +
            "JOIN s.courses c " +
            "JOIN c.sections sec " +
            "WHERE sec.sectionName = :sectionName")
    List<Student> findStudentsInSection(@Param("sectionName") String sectionName);

    /**
     * NATIVE SQL QUERY
     * Use when JPQL is not sufficient
     */
    @Query(value = "SELECT * FROM students WHERE " +
            "CONCAT(first_name, ' ', last_name) LIKE %?1%",
            nativeQuery = true)
    List<Student> findByFullNameNative(String fullName);
    // ==================== EXERCISE: UPDATE A FIELD ====================

    /**
     * EXERCISE PROBLEM: Update student status for all students enrolled before a certain date
     *
     * SOLUTION 1: Using @Query with UPDATE statement
     *
     * @Modifying - Required for INSERT, UPDATE, DELETE queries
     * Without @Modifying, Spring expects SELECT query returning entities
     * With @Modifying, Spring knows this query modifies data
     *
     * JPQL UPDATE syntax:
     * UPDATE EntityName SET field = value WHERE conditions
     *
     * Expected SQL Generated:
     * UPDATE students SET status = ? WHERE enrollment_date < ?;
     *
     * Return value: Number of affected rows (int)
     */
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.status = :newStatus WHERE s.enrollmentDate < :cutoffDate")
    int updateStatusForOldEnrollments(@Param("newStatus") StudentStatus newStatus,
                                      @Param("cutoffDate") LocalDateTime cutoffDate);
    /*
    Usage Example:
    int updatedCount = studentRepository.updateStatusForOldEnrollments(
        StudentStatus.INACTIVE,
        LocalDateTime.now().minusYears(1)
    );
    System.out.println("Updated " + updatedCount + " students to INACTIVE status");

    Expected Console Output:
    "Updated 15 students to INACTIVE status"
    */

    /**
     * SOLUTION 2: Update specific student's email
     *
     * @Modifying annotation breakdown:
     * - clearAutomatically = true: Clear EntityManager after update
     * - flushAutomatically = true: Flush pending changes before update
     *
     * Why clear EntityManager?
     * After direct SQL UPDATE, entities in memory might be stale
     * Clearing forces fresh loading from database
     *
     * Expected SQL Generated:
     * UPDATE students SET email = ? WHERE student_id = ?;
     */
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("UPDATE Student s SET s.email = :newEmail WHERE s.studentId = :studentId")
    int updateStudentEmail(@Param("studentId") Integer studentId, @Param("newEmail") String newEmail);

    /**
     * SOLUTION 3: Bulk update with multiple conditions
     *
     * Expected SQL Generated:
     * UPDATE students SET status = ?, enrollment_date = ?
     * WHERE status = ? AND enrollment_date < ?;
     */
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.status = :newStatus, s.enrollmentDate = :newDate " +
            "WHERE s.status = :oldStatus AND s.enrollmentDate < :cutoffDate")
    int bulkUpdateStudentInfo(@Param("newStatus") StudentStatus newStatus,
                              @Param("newDate") LocalDateTime newDate,
                              @Param("oldStatus") StudentStatus oldStatus,
                              @Param("cutoffDate") LocalDateTime cutoffDate);

    // ==================== UPDATE DATA WITH @MODIFYING ====================

    /**
     * @Modifying ANNOTATION DEEP DIVE
     *
     * @Modifying is required for any non-SELECT queries:
     * - INSERT statements
     * - UPDATE statements
     * - DELETE statements
     *
     * Without @Modifying:
     * - Spring expects query to return entities
     * - Tries to map result to entity objects
     * - Fails with UPDATE/DELETE/INSERT queries
     *
     * With @Modifying:
     * - Spring knows query modifies data
     * - Returns number of affected rows
     * - Manages transaction properly
     */

    /**
     * DELETE with @Modifying
     *
     * Expected SQL Generated:
     * DELETE FROM students WHERE status = ?;
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.status = :status")
    int deleteStudentsByStatus(@Param("status") StudentStatus status);

    /**
     * UPDATE with calculations
     *
     * This example shows updating based on calculated values
     *
     * Expected SQL Generated:
     * UPDATE students SET enrollment_date = ?
     * WHERE enrollment_date IS NULL;
     */
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.enrollmentDate = :defaultDate WHERE s.enrollmentDate IS NULL")
    int setDefaultEnrollmentDate(@Param("defaultDate") LocalDateTime defaultDate);

    /**
     * UPDATE with subquery
     *
     * Complex example showing UPDATE with subquery
     * Sets status to GRADUATED for students in completed courses
     *
     * Expected SQL Generated:
     * UPDATE students SET status = 'GRADUATED'
     * WHERE student_id IN (
     *   SELECT DISTINCT sc.student_id FROM students_courses sc
     *   JOIN courses c ON sc.course_id = c.course_id
     *   WHERE c.course_name LIKE '%Completed%'
     * );
     */
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.status = 'GRADUATED' " +
            "WHERE s IN (SELECT DISTINCT st FROM Student st JOIN st.courses c WHERE c.courseName LIKE '%Completed%')")
    int graduateStudentsInCompletedCourses();

    // ==================== SELECT DATA WITH NAMED QUERIES ====================

    /**
     * USING NAMED QUERIES defined in @NamedQuery annotations
     *
     * Named queries are defined at entity level with @NamedQuery
     * Called here using the query name
     *
     * Advantages of Named Queries:
     * - Validated at startup (syntax errors caught early)
     * - Better performance (pre-compiled)
     * - Centralized query management
     * - Reusable across multiple repositories
     */

    /**
     * Execute Named Query: Student.findByNamePattern
     *
     * This calls the named query defined in Student entity
     * Pattern matching for first name or last name
     */
    @Query(name = "Student.findByNamePattern")
    List<Student> findByNamePattern(@Param("pattern") String pattern);
    /*
    Usage Example:
    List<Student> johnsAndJanes = studentRepository.findByNamePattern("%John%");
    // Finds students with "John" anywhere in first or last name

    Expected SQL:
    SELECT * FROM students
    WHERE LOWER(first_name) LIKE LOWER('%John%') OR LOWER(last_name) LIKE LOWER('%John%');
    */

    /**
     * Execute Named Query: Student.findByEnrollmentDateRange
     */
    @Query(name = "Student.findByEnrollmentDateRange")
    List<Student> findByEnrollmentDateRange(@Param("startDate") LocalDateTime startDate,
                                            @Param("endDate") LocalDateTime endDate);

    /**
     * Execute Named Query: Student.countByStatus
     *
     * Returns Long (count), not List<Student>
     */
    @Query(name = "Student.countByStatus")
    Long countByStatus(@Param("status") StudentStatus status);

    /**
     * Execute Named Query: Student.findByTeacherNamePattern
     */
    @Query(name = "Student.findByTeacherNamePattern")
    List<Student> findByTeacherNamePattern(@Param("teacherName") String teacherName);

    /**
     * Execute Named Query: Student.findByCoursesAndTeacher
     */
    @Query(name = "Student.findByCoursesAndTeacher")
    List<Student> findByCoursesAndTeacher(@Param("courseName") String courseName,
                                          @Param("teacherName") String teacherName);

    // ==================== UPDATE DATA WITH NAMED QUERIES ====================

    /**
     * Named queries can also be UPDATE/DELETE statements
     * These are defined in separate @NamedQuery annotations
     */

    /**
     * Execute Named Update Query
     *
     * This would reference a named query like:
     * @NamedQuery(
     *     name = "Student.updateStatusByEnrollmentDate",
     *     query = "UPDATE Student s SET s.status = :status WHERE s.enrollmentDate < :date"
     * )
     */
    @Modifying
    @Transactional
    @Query(name = "Student.updateStatusByEnrollmentDate")
    int updateStatusByEnrollmentDateNamed(@Param("status") StudentStatus status,
                                          @Param("date") LocalDateTime date);

    // ==================== NATIVE NAMED QUERIES ====================

    /**
     * Named Native SQL Queries
     *
     * Sometimes JPQL is not sufficient, need raw SQL
     * Can also be defined as named queries with nativeQuery = true
     */
    @Query(value = "SELECT * FROM students WHERE " +
            "CONCAT(first_name, ' ', last_name) = ?1",
            nativeQuery = true)
    List<Student> findByExactFullName(String fullName);

    /**
     * Complex Native Query with statistics
     *
     * Example showing advanced SQL with aggregations
     */
    @Query(value = "SELECT s.*, COUNT(sc.course_id) as course_count " +
            "FROM students s " +
            "LEFT JOIN students_courses sc ON s.student_id = sc.student_id " +
            "GROUP BY s.student_id " +
            "HAVING COUNT(sc.course_id) > ?1",
            nativeQuery = true)
    List<Object[]> findStudentsWithMinimumCourses(int minCourseCount);
}
