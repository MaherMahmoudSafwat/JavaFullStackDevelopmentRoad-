// ==================== ENHANCED STUDENT ENTITY WITH NAMED QUERIES ====================

package JPA_Course.example.JpaDemoExamples.Student;

import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * ENHANCED STUDENT ENTITY with Named Queries
 *
 * @NamedQuery - Defines reusable JPQL queries at entity level
 * These queries are parsed and validated when the EntityManager is created
 * Better performance than dynamic queries (parsed once, used many times)
 *
 * Expected Database Table Structure:
 * CREATE TABLE students (
 *     student_id INTEGER AUTO_INCREMENT PRIMARY KEY,
 *     first_name VARCHAR(57) NOT NULL,
 *     last_name VARCHAR(255) NOT NULL,
 *     email VARCHAR(255) NOT NULL UNIQUE,
 *     enrollment_date TIMESTAMP,
 *     status VARCHAR(20),
 *     teacher_id INTEGER UNIQUE,
 *     FOREIGN KEY (teacher_id) REFERENCES teachers(id)
 * );
 */
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        /**
         * NAMED QUERY 1: Find students by name pattern
         *
         * This query will be registered with name "Student.findByNamePattern"
         * Can be called from any repository or EntityManager
         *
         * JPQL Breakdown:
         * - SELECT s FROM Student s: Select Student entities (alias 's')
         * - WHERE LOWER(s.firstName) LIKE LOWER(:pattern): Case-insensitive pattern matching
         * - OR LOWER(s.lastName) LIKE LOWER(:pattern): Check both first and last names
         *
         * Parameter :pattern will be replaced with actual search term
         *
         * Expected SQL Generated:
         * SELECT student_id, first_name, last_name, email, enrollment_date, status, teacher_id
         * FROM students
         * WHERE LOWER(first_name) LIKE LOWER(?) OR LOWER(last_name) LIKE LOWER(?);
         */
        @NamedQuery(
                name = "Student.findByNamePattern",
                query = "SELECT s FROM Student s WHERE LOWER(s.firstName) LIKE LOWER(:pattern) OR LOWER(s.lastName) LIKE LOWER(:pattern)"
        ),

        /**
         * NAMED QUERY 2: Find students by enrollment date range
         *
         * JPQL Breakdown:
         * - WHERE s.enrollmentDate BETWEEN :startDate AND :endDate: Date range filter
         * - ORDER BY s.enrollmentDate DESC: Sort by newest first
         *
         * Expected SQL Generated:
         * SELECT * FROM students
         * WHERE enrollment_date BETWEEN ? AND ?
         * ORDER BY enrollment_date DESC;
         */
        @NamedQuery(
                name = "Student.findByEnrollmentDateRange",
                query = "SELECT s FROM Student s WHERE s.enrollmentDate BETWEEN :startDate AND :endDate ORDER BY s.enrollmentDate DESC"
        ),

        /**
         * NAMED QUERY 3: Count students by status
         *
         * This is a COUNT query - returns Long, not Student entities
         *
         * Expected SQL Generated:
         * SELECT COUNT(*) FROM students WHERE status = ?;
         */
        @NamedQuery(
                name = "Student.countByStatus",
                query = "SELECT COUNT(s) FROM Student s WHERE s.status = :status"
        ),

        /**
         * NAMED QUERY 4: Find students with specific teacher
         *
         * JPQL with JOIN:
         * - JOIN s.teacher t: Join Student with Teacher entity
         * - WHERE t.teacherName LIKE :teacherName: Filter by teacher name pattern
         *
         * Expected SQL Generated:
         * SELECT s.* FROM students s
         * JOIN teachers t ON s.teacher_id = t.id
         * WHERE t.teacher_name LIKE ?;
         */
        @NamedQuery(
                name = "Student.findByTeacherNamePattern",
                query = "SELECT s FROM Student s JOIN s.teacher t WHERE t.teacherName LIKE :teacherName"
        ),

        /**
         * NAMED QUERY 5: Complex query with multiple JOINs
         *
         * This demonstrates complex relationships:
         * - JOIN s.courses c: Student -> Courses (many-to-many)
         * - JOIN c.teachers ct: Courses -> Teachers (one-to-many)
         * - Multiple conditions with AND/OR
         *
         * Expected SQL Generated:
         * SELECT DISTINCT s.* FROM students s
         * JOIN students_courses sc ON s.student_id = sc.student_id
         * JOIN courses c ON sc.course_id = c.course_id
         * JOIN teachers ct ON c.course_id = ct.course_id
         * WHERE c.course_name = ? AND ct.teacher_name LIKE ?;
         */
        @NamedQuery(
                name = "Student.findByCoursesAndTeacher",
                query = "SELECT DISTINCT s FROM Student s " +
                        "JOIN s.courses c " +
                        "JOIN c.teachers ct " +
                        "WHERE c.courseName = :courseName AND ct.teacherName LIKE :teacherName"
        )
})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 57, message = "First name must be between 2 and 57 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Please provide a valid email address")
    private String email;

    /**
     * NEW FIELDS for advanced queries
     */
    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

    /**
     * RELATIONSHIPS
     */
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"students", "teachers"})
    private List<Courses> courses = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", unique = true)
    @JsonIgnoreProperties("student")
    private Teacher teacher;

    // Helper methods
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void addCourse(Courses course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Courses course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }

    @PrePersist
    public void prePersist() {
        if (enrollmentDate == null) {
            enrollmentDate = LocalDateTime.now();
        }
        if (status == null) {
            status = StudentStatus.ACTIVE;
        }
    }
}

/**
 * STUDENT STATUS ENUM
 */
public enum StudentStatus {
    ACTIVE,     // Currently enrolled and attending
    INACTIVE,   // Temporarily not attending
    GRADUATED,  // Completed program
    WITHDRAWN   // Left the program
}

// ==================== ENHANCED STUDENT REPOSITORY WITH ADVANCED FEATURES ====================

package JPA_Course.example.JpaDemoExamples.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ENHANCED STUDENT REPOSITORY
 *
 * Extends JpaSpecificationExecutor to enable Specification queries
 * This allows building dynamic queries programmatically
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

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

// ==================== JPA SPECIFICATIONS OVERVIEW ====================

/*
WHAT ARE JPA SPECIFICATIONS?

Specifications allow you to build queries DYNAMICALLY at runtime.
Instead of writing fixed queries, you build query conditions programmatically.

Think of Specifications like building SQL WHERE clauses with Java code:
- Add conditions based on user input
- Combine conditions with AND/OR
- Reuse conditions across different queries

REAL WORLD EXAMPLE:
User search form with optional fields:
- Name (optional)
- Email domain (optional)
- Enrollment date range (optional)
- Status (optional)

Without Specifications: Need separate query method for each combination
With Specifications: Build query dynamically based on what user provided

TECHNICAL DETAILS:
Specifications use JPA Criteria API under the hood
Criteria API allows building type-safe queries programmatically
*/

package JPA_Course.example.JpaDemoExamples.Student;

import jakarta.persistence.criteria.*;
        import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * STUDENT SPECIFICATIONS
 *
 * Collection of reusable query conditions for Student entity
 * Each method returns a Specification that can be combined with others
 */
@Component
public class StudentSpecifications {

    /**
     * SPECIFICATION BUILDING EXPLAINED
     *
     * Specification<Student> interface has one method:
     * Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb)
     *
     * Parameters:
     * - Root<Student> root: Represents the Student entity in FROM clause
     * - CriteriaQuery<?> query: The overall query being built
     * - CriteriaBuilder cb: Factory for creating query parts (WHERE, AND, OR, etc.)
     *
     * Return:
     * - Predicate: A condition that can be used in WHERE clause
     */

    /**
     * SPECIFICATION 1: Filter by first name
     *
     * Lambda breakdown:
     * (root, query, cb) -> cb.equal(root.get("firstName"), firstName)
     *
     * - root.get("firstName"): Access firstName field of Student entity
     * - cb.equal(field, value): Creates "field = value" condition
     * - Return null if firstName is null (no condition added)
     *
     * Generated SQL WHERE clause:
     * WHERE first_name = ?
     */
    public static Specification<Student> hasFirstName(String firstName) {
        return (root, query, cb) -> {
            if (firstName == null || firstName.trim().isEmpty()) {
                return null; // No condition if firstName is empty
            }
            return cb.equal(root.get("firstName"), firstName);
        };
    }

    /**
     * SPECIFICATION 2: Filter by last name pattern
     *
     * cb.like() creates LIKE condition with wildcards
     * cb.lower() converts to lowercase for case-insensitive search
     *
     * Generated SQL WHERE clause:
     * WHERE LOWER(last_name) LIKE LOWER('%pattern%')
     */
    public static Specification<Student> hasLastNameContaining(String pattern) {
        return (root, query, cb) -> {
            if (pattern == null || pattern.trim().isEmpty()) {
                return null;
            }
            return cb.like(
                    cb.lower(root.get("lastName")),
                    cb.lower(cb.literal("%" + pattern + "%"))
            );
        };
    }

    /**
     * SPECIFICATION 3: Filter by email domain
     *
     * Example: hasEmailDomain("gmail.com") finds all @gmail.com emails
     *
     * Generated SQL WHERE clause:
     * WHERE email LIKE '%@domain%'
     */
    public static Specification<Student> hasEmailDomain(String domain) {
        return (root, query, cb) -> {
            if (domain == null || domain.trim().isEmpty()) {
                return null;
            }
            return cb.like(root.get("email"), "%@" + domain + "%");
        };
    }

    /**
     * SPECIFICATION 4: Filter by status
     *
     * Simple enum equality check
     *
     * Generated SQL WHERE clause:
     * WHERE status = ?
     */
    public static Specification<Student> hasStatus(StudentStatus status) {
        return (root, query, cb) -> {
            if (status == null) {
                return null;
            }
            return cb.equal(root.get("status"), status);
        };
    }

    /**
     * SPECIFICATION 5: Filter by enrollment date range
     *
     * cb.between() creates BETWEEN condition
     * Handles null dates gracefully
     *
     * Generated SQL WHERE clause:
     * WHERE enrollment_date BETWEEN ? AND ?
     */
    public static Specification<Student> enrolledBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, cb) -> {
            if (startDate == null && endDate == null) {
                return null;
            }

            if (startDate != null && endDate != null) {
                return cb.between(root.get("enrollmentDate"), startDate, endDate);
            } else if (startDate != null) {
                return cb.greaterThanOrEqualTo(root.get("enrollmentDate"), startDate);
            } else {
                return cb.lessThanOrEqualTo(root.get("enrollmentDate"), endDate);
            }
        };
    }

    /**
     * SPECIFICATION 6: Filter by relationship (has teacher)
     *
     * Demonstrates JOIN in Specifications
     * root.join("teacher") creates JOIN with teacher table
     *
     * Generated SQL:
     * SELECT s.* FROM students s 
     * JOIN teachers t ON s.teacher_id = t.id 
     * WHERE t.teacher_name LIKE ?
     */
    public static Specification<Student> hasTeacherNameContaining(String teacherName) {
        return (root, query, cb) -> {
            if (teacherName == null || teacherName.trim().isEmpty()) {
                return null;
            }

            // Create JOIN with teacher entity
            Join<Student, Teacher> teacherJoin = root.join("teacher", JoinType.INNER);

            return cb.like(
                    cb.lower(teacherJoin.get("teacherName")),
                    cb.lower(cb.literal("%" + teacherName + "%"))
            );
        };
    }

    /**
     * SPECIFICATION 7: Complex relationship filter (enrolled in course)
     *
     * Demonstrates many-to-many relationship in Specifications
     * root.join("courses") navigates through many-to-many relationship
     *
     * Generated SQL:
     * SELECT DISTINCT s.* FROM students s 
     * JOIN students_courses sc ON s.student_id = sc.student_id
     * JOIN courses c ON sc.course_id = c.course_id
     * WHERE c.course_name = ?
     */
    public static Specification<Student> isEnrolledInCourse(String courseName) {
        return (root, query, cb) -> {
            if (courseName == null || courseName.trim().isEmpty()) {
                return null;
            }

            // Create JOIN with courses (many-to-many)
            Join<Student, Courses> courseJoin = root.join("courses", JoinType.INNER);

            // Ensure DISTINCT results (avoid duplicates from JOIN)
            query.distinct(true);

            return cb.equal(courseJoin.get("courseName"), courseName);
        };
    }

    /**
     * SPECIFICATION 8: Null/Not Null checks
     *
     * cb.isNull() and cb.isNotNull() for checking null values
     *
     * Generated SQL WHERE clause:
     * WHERE teacher_id IS NOT NULL
     */
    public static Specification<Student> hasTeacher() {
        return (root, query, cb) -> cb.isNotNull(root.get("teacher"));
    }

    public static Specification<Student> hasNoTeacher() {
        return (root, query, cb) -> cb.isNull(root.get("teacher"));
    }

    /**
     * SPECIFICATION 9: Count-based filter
     *
     * Complex example: Students enrolled in more than N courses
     * Uses subquery to count enrollments
     *
     * Generated SQL:
     * WHERE (SELECT COUNT(sc.course_id) FROM students_courses sc WHERE sc.student_id = s.student_id) > ?
     */
    public static Specification<Student> hasMoreThanNCourses(int courseCount) {
        return (root, query, cb) -> {
            // Create subquery to count courses
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Student> subqueryRoot = subquery.from(Student.class);
            Join<Student, Courses> subqueryCourseJoin = subqueryRoot.join("courses");

            subquery.select(cb.count(subqueryCourseJoin))
                    .where(cb.equal(subqueryRoot.get("studentId"), root.get("studentId")));

            return cb.greaterThan(subquery, (long) courseCount);
        };
    }

    /**
     * SPECIFICATION 10: Dynamic OR conditions
     *
     * Creates OR condition based on provided search terms
     * Searches across multiple fields
     *
     * Generated SQL:
     * WHERE first_name LIKE ? OR last_name LIKE ? OR email LIKE ?
     */
    public static Specification<Student> searchAcrossFields(String searchTerm) {
        return (root, query, cb) -> {
            if (searchTerm == null || searchTerm.trim().isEmpty()) {
                return null;
            }

            String likePattern = "%" + searchTerm.toLowerCase() + "%";

            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.like(cb.lower(root.get("firstName")), likePattern));
            predicates.add(cb.like(cb.lower(root.get("lastName")), likePattern));
            predicates.add(cb.like(cb.lower(root.get("email")), likePattern));

            // Combine with OR
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}

// ==================== EXECUTING SPECIFICATIONS ====================

package JPA_Course.example.JpaDemoExamples.Service;

import JPA_Course.example.JpaDemoExamples.Student.*;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * SERVICE DEMONSTRATING SPECIFICATION USAGE
 *
 * Shows how to build and execute dynamic queries using Specifications
 */
@Service
@Transactional
public class StudentSearchService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * EXAMPLE 1: Single Specification
     *
     * Find all students with specific first name
     */
    public List<Student> findStudentsByFirstName(String firstName) {
        Specification<Student> spec = StudentSpecifications.hasFirstName(firstName);

        List<Student> results = studentRepository.findAll(spec);
        /*
        Expected SQL:
        SELECT * FROM students WHERE first_name = ? AND status = ?;
        */

        System.out.println("Found " + results.size() + " active students with name: " + firstName);
        return results;
    }

    /**
     * EXAMPLE 3: Combining Specifications with OR
     *
     * Find students by first name OR last name pattern
     */
    public List<Student> findStudentsByNamePattern(String firstName, String lastNamePattern) {
        Specification<Student> spec = Specification
                .where(StudentSpecifications.hasFirstName(firstName))
                .or(StudentSpecifications.hasLastNameContaining(lastNamePattern));

        List<Student> results = studentRepository.findAll(spec);
        /*
        Expected SQL:
        SELECT * FROM students 
        WHERE first_name = ? OR LOWER(last_name) LIKE LOWER(?);
        */

        return results;
    }

    /**
     * EXAMPLE 4: Complex Specification Chain
     *
     * Build complex search with multiple conditions:
     * - Active students
     * - With specific email domain
     * - Enrolled in specific course
     * - With teacher name containing pattern
     */
    public List<Student> complexStudentSearch(String emailDomain, String courseName, String teacherPattern) {
        Specification<Student> spec = Specification
                .where(StudentSpecifications.hasStatus(StudentStatus.ACTIVE))
                .and(StudentSpecifications.hasEmailDomain(emailDomain))
                .and(StudentSpecifications.isEnrolledInCourse(courseName))
                .and(StudentSpecifications.hasTeacherNameContaining(teacherPattern));

        List<Student> results = studentRepository.findAll(spec);
        /*
        Expected SQL:
        SELECT DISTINCT s.* FROM students s 
        JOIN teachers t ON s.teacher_id = t.id
        JOIN students_courses sc ON s.student_id = sc.student_id
        JOIN courses c ON sc.course_id = c.course_id
        WHERE s.status = 'ACTIVE' 
          AND s.email LIKE '%@domain%'
          AND c.course_name = ?
          AND LOWER(t.teacher_name) LIKE LOWER(?);
        */

        System.out.println("Complex search found " + results.size() + " students");
        return results;
    }

    /**
     * EXAMPLE 5: Dynamic Specification Building
     *
     * Build specification based on what search criteria are provided
     * This is the real power of Specifications - truly dynamic queries
     */
    public List<Student> dynamicStudentSearch(StudentSearchCriteria criteria) {
        Specification<Student> spec = Specification.where(null); // Start with empty spec

        // Add conditions only if criteria are provided
        if (criteria.getFirstName() != null) {
            spec = spec.and(StudentSpecifications.hasFirstName(criteria.getFirstName()));
        }

        if (criteria.getLastNamePattern() != null) {
            spec = spec.and(StudentSpecifications.hasLastNameContaining(criteria.getLastNamePattern()));
        }

        if (criteria.getEmailDomain() != null) {
            spec = spec.and(StudentSpecifications.hasEmailDomain(criteria.getEmailDomain()));
        }

        if (criteria.getStatus() != null) {
            spec = spec.and(StudentSpecifications.hasStatus(criteria.getStatus()));
        }

        if (criteria.getStartDate() != null || criteria.getEndDate() != null) {
            spec = spec.and(StudentSpecifications.enrolledBetween(criteria.getStartDate(), criteria.getEndDate()));
        }

        if (criteria.getCourseName() != null) {
            spec = spec.and(StudentSpecifications.isEnrolledInCourse(criteria.getCourseName()));
        }

        if (criteria.getTeacherNamePattern() != null) {
            spec = spec.and(StudentSpecifications.hasTeacherNameContaining(criteria.getTeacherNamePattern()));
        }

        if (criteria.getSearchTerm() != null) {
            spec = spec.and(StudentSpecifications.searchAcrossFields(criteria.getSearchTerm()));
        }

        List<Student> results = studentRepository.findAll(spec);
        System.out.println("Dynamic search with " + criteria.getActiveFiltersCount() + " filters found " + results.size() + " students");

        return results;
    }

    /**
     * EXAMPLE 6: Specifications with Pagination and Sorting
     *
     * Combine Specifications with paging for performance
     */
    public Page<Student> searchStudentsWithPaging(StudentSearchCriteria criteria,
                                                  int page, int size, String sortBy, String sortDir) {

        // Build specification dynamically
        Specification<Student> spec = buildSpecificationFromCriteria(criteria);

        // Create sort order
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        // Create pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> results = studentRepository.findAll(spec, pageable);
        /*
        Expected SQL:
        SELECT * FROM students 
        WHERE [dynamic conditions based on criteria]
        ORDER BY [sortBy] [ASC/DESC]
        LIMIT [size] OFFSET [page * size];
        
        Also executes count query for pagination:
        SELECT COUNT(*) FROM students WHERE [same conditions];
        */

        System.out.println("Paged search returned " + results.getContent().size() + " of " + results.getTotalElements() + " students");
        return results;
    }

    /**
     * EXAMPLE 7: Specifications with Custom Repository Methods
     *
     * Combine specifications with custom repository logic
     */
    public List<Student> findStudentsWithStatistics(StudentSearchCriteria criteria) {
        // Use specification for filtering
        Specification<Student> spec = buildSpecificationFromCriteria(criteria);
        List<Student> filteredStudents = studentRepository.findAll(spec);

        // Add custom logic
        System.out.println("=== STUDENT SEARCH STATISTICS ===");
        System.out.println("Total found: " + filteredStudents.size());

        long activeCount = filteredStudents.stream()
                .filter(s -> s.getStatus() == StudentStatus.ACTIVE)
                .count();
        System.out.println("Active: " + activeCount);

        long graduatedCount = filteredStudents.stream()
                .filter(s -> s.getStatus() == StudentStatus.GRADUATED)
                .count();
        System.out.println("Graduated: " + graduatedCount);

        double averageCoursesPerStudent = filteredStudents.stream()
                .mapToInt(s -> s.getCourses().size())
                .average()
                .orElse(0.0);
        System.out.println("Average courses per student: " + String.format("%.2f", averageCoursesPerStudent));

        return filteredStudents;
    }

    /**
     * Helper method to build specification from criteria object
     */
    private Specification<Student> buildSpecificationFromCriteria(StudentSearchCriteria criteria) {
        return Specification
                .where(criteria.getFirstName() != null ? StudentSpecifications.hasFirstName(criteria.getFirstName()) : null)
                .and(criteria.getLastNamePattern() != null ? StudentSpecifications.hasLastNameContaining(criteria.getLastNamePattern()) : null)
                .and(criteria.getEmailDomain() != null ? StudentSpecifications.hasEmailDomain(criteria.getEmailDomain()) : null)
                .and(criteria.getStatus() != null ? StudentSpecifications.hasStatus(criteria.getStatus()) : null)
                .and(criteria.getStartDate() != null || criteria.getEndDate() != null ?
                        StudentSpecifications.enrolledBetween(criteria.getStartDate(), criteria.getEndDate()) : null)
                .and(criteria.getCourseName() != null ? StudentSpecifications.isEnrolledInCourse(criteria.getCourseName()) : null)
                .and(criteria.getTeacherNamePattern() != null ? StudentSpecifications.hasTeacherNameContaining(criteria.getTeacherNamePattern()) : null)
                .and(criteria.getSearchTerm() != null ? StudentSpecifications.searchAcrossFields(criteria.getSearchTerm()) : null);
    }
}

// ==================== SEARCH CRITERIA DTO ====================

package JPA_Course.example.JpaDemoExamples.Student;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * DATA TRANSFER OBJECT for search criteria
 *
 * Used to pass search parameters to specification-based queries
 * Clean way to handle optional search fields
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSearchCriteria {

    private String firstName;
    private String lastNamePattern;
    private String emailDomain;
    private StudentStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String courseName;
    private String teacherNamePattern;
    private String searchTerm; // Search across multiple fields

    /**
     * Helper method to count active filters
     */
    public int getActiveFiltersCount() {
        int count = 0;
        if (firstName != null && !firstName.trim().isEmpty()) count++;
        if (lastNamePattern != null && !lastNamePattern.trim().isEmpty()) count++;
        if (emailDomain != null && !emailDomain.trim().isEmpty()) count++;
        if (status != null) count++;
        if (startDate != null) count++;
        if (endDate != null) count++;
        if (courseName != null && !courseName.trim().isEmpty()) count++;
        if (teacherNamePattern != null && !teacherNamePattern.trim().isEmpty()) count++;
        if (searchTerm != null && !searchTerm.trim().isEmpty()) count++;
        return count;
    }

    /**
     * Check if any search criteria are provided
     */
    public boolean hasAnyCriteria() {
        return getActiveFiltersCount() > 0;
    }
}

// ==================== COMPREHENSIVE DEMO SERVICE ====================

package JPA_Course.example.JpaDemoExamples.Service;

import JPA_Course.example.JpaDemoExamples.Student.*;
        import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Courses.CourseRepository;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import JPA_Course.example.JpaDemoExamples.Teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * COMPREHENSIVE DEMONSTRATION SERVICE
 *
 * Demonstrates all advanced JPA features:
 * - @Modifying queries
 * - Named queries
 * - Specifications
 * - Complex search scenarios
 */
@Service
@Transactional
public class AdvancedJpaDemo {

    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private StudentSearchService searchService;

    /**
     * COMPLETE DEMONSTRATION
     *
     * Runs through all advanced JPA features with sample data
     */
    public void runComprehensiveDemo() {

        System.out.println("==========================================");
        System.out.println("ADVANCED JPA FEATURES DEMONSTRATION");
        System.out.println("==========================================");

        // Step 1: Set up test data
        setupTestData();

        // Step 2: Demonstrate @Modifying queries
        demonstrateModifyingQueries();

        // Step 3: Demonstrate Named Queries
        demonstrateNamedQueries();

        // Step 4: Demonstrate Specifications
        demonstrateSpecifications();

        // Step 5: Demonstrate complex search scenarios
        demonstrateComplexSearches();

        System.out.println("==========================================");
        System.out.println("DEMONSTRATION COMPLETED SUCCESSFULLY");
        System.out.println("==========================================");
    }

    /**
     * SETUP TEST DATA
     * Creates sample data for demonstrations
     */
    private void setupTestData() {
        System.out.println("\n=== SETTING UP TEST DATA ===");

        // Clear existing data
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        courseRepository.deleteAll();

        // Create courses
        Courses javaCourse = new Courses("Java Programming", "CS101", 3);
        Courses pythonCourse = new Courses("Python Programming", "CS102", 3);
        Courses dataScienceCourse = new Courses("Data Science", "DS201", 4);

        courseRepository.saveAll(Arrays.asList(javaCourse, pythonCourse, dataScienceCourse));

        // Create teachers
        Teacher drSmith = new Teacher("Dr. John Smith", "dr.smith@university.edu", "password123");
        Teacher profJones = new Teacher("Prof. Sarah Jones", "prof.jones@university.edu", "password456");
        Teacher mrBrown = new Teacher("Mr. Michael Brown", "mr.brown@university.edu", "password789");

        drSmith.setCourses(javaCourse);
        profJones.setCourses(pythonCourse);
        mrBrown.setCourses(dataScienceCourse);

        teacherRepository.saveAll(Arrays.asList(drSmith, profJones, mrBrown));

        // Create students with varied data
        LocalDateTime now = LocalDateTime.now();

        Student alice = new Student();
        alice.setFirstName("Alice");
        alice.setLastName("Johnson");
        alice.setEmail("alice.johnson@gmail.com");
        alice.setEnrollmentDate(now.minusMonths(6));
        alice.setStatus(StudentStatus.ACTIVE);
        alice.setTeacher(drSmith);

        Student bob = new Student();
        bob.setFirstName("Bob");
        bob.setLastName("Smith");
        bob.setEmail("bob.smith@yahoo.com");
        bob.setEnrollmentDate(now.minusMonths(12));
        bob.setStatus(StudentStatus.INACTIVE);
        bob.setTeacher(profJones);

        Student charlie = new Student();
        charlie.setFirstName("Charlie");
        charlie.setLastName("Brown");
        charlie.setEmail("charlie.brown@university.edu");
        charlie.setEnrollmentDate(now.minusMonths(18));
        charlie.setStatus(StudentStatus.GRADUATED);
        charlie.setTeacher(mrBrown);

        Student diana = new Student();
        diana.setFirstName("Diana");
        diana.setLastName("Wilson");
        diana.setEmail("diana.wilson@gmail.com");
        diana.setEnrollmentDate(now.minusMonths(3));
        diana.setStatus(StudentStatus.ACTIVE);
        diana.setTeacher(drSmith);

        Student edward = new Student();
        edward.setFirstName("Edward");
        edward.setLastName("Davis");
        edward.setEmail("edward.davis@hotmail.com");
        edward.setEnrollmentDate(now.minusMonths(24));
        edward.setStatus(StudentStatus.WITHDRAWN);

        studentRepository.saveAll(Arrays.asList(alice, bob, charlie, diana, edward));

        // Set up course enrollments
        alice.addCourse(javaCourse);
        alice.addCourse(pythonCourse);

        bob.addCourse(pythonCourse);
        bob.addCourse(dataScienceCourse);

        charlie.addCourse(javaCourse);
        charlie.addCourse(dataScienceCourse);

        diana.addCourse(javaCourse);

        edward.addCourse(dataScienceCourse);

        studentRepository.saveAll(Arrays.asList(alice, bob, charlie, diana, edward));
        courseRepository.saveAll(Arrays.asList(javaCourse, pythonCourse, dataScienceCourse));

        System.out.println("Created 5 students, 3 courses, 3 teachers with relationships");
    }

    /**
     * DEMONSTRATE @Modifying QUERIES
     */
    private void demonstrateModifyingQueries() {
        System.out.println("\n=== DEMONSTRATING @MODIFYING QUERIES ===");

        // Example 1: Update student status for old enrollments
        LocalDateTime cutoff = LocalDateTime.now().minusMonths(15);
        int updated = studentRepository.updateStatusForOldEnrollments(StudentStatus.INACTIVE, cutoff);
        System.out.println("Updated " + updated + " students to INACTIVE status (enrolled before " + cutoff + ")");
        /*
        Expected Output:
        "Updated 2 students to INACTIVE status (enrolled before 2023-10-15T10:30:00)"
        
        Expected SQL:
        UPDATE students SET status = 'INACTIVE' WHERE enrollment_date < '2023-10-15 10:30:00';
        */

        // Example 2: Update specific student email
        Student alice = studentRepository.findByFirstName("Alice").get(0);
        int emailUpdated = studentRepository.updateStudentEmail(alice.getStudentId(), "alice.new@gmail.com");
        System.out.println("Updated email for " + emailUpdated + " student");

        // Example 3: Set default enrollment date for students without one
        int defaultDateSet = studentRepository.setDefaultEnrollmentDate(LocalDateTime.now());
        System.out.println("Set default enrollment date for " + defaultDateSet + " students");

        // Example 4: Delete students by status
        int deleted = studentRepository.deleteStudentsByStatus(StudentStatus.WITHDRAWN);
        System.out.println("Deleted " + deleted + " withdrawn students");

        // Show current state
        long remaining = studentRepository.count();
        System.out.println("Remaining students after modifications: " + remaining);
    }

    /**
     * DEMONSTRATE NAMED QUERIES
     */
    private void demonstrateNamedQueries() {
        System.out.println("\n=== DEMONSTRATING NAMED QUERIES ===");

        // Example 1: Search by name pattern
        List<Student> johnResults = studentRepository.findByNamePattern("%John%");
        System.out.println("Students with 'John' in name: " + johnResults.size());
        johnResults.forEach(s -> System.out.println("  - " + s.getFullName()));
        /*
        Expected Output:
        "Students with 'John' in name: 1"
        "  - Alice Johnson"
        
        Expected SQL:
        SELECT * FROM students 
        WHERE LOWER(first_name) LIKE LOWER('%John%') OR LOWER(last_name) LIKE LOWER('%John%');
        */

        // Example 2: Find by enrollment date range
        LocalDateTime start = LocalDateTime.now().minusMonths(12);
        LocalDateTime end = LocalDateTime.now().minusMonths(3);
        List<Student> dateRangeResults = studentRepository.findByEnrollmentDateRange(start, end);
        System.out.println("Students enrolled between " + start + " and " + end + ": " + dateRangeResults.size());

        // Example 3: Count by status
        Long activeCount = studentRepository.countByStatus(StudentStatus.ACTIVE);
        Long inactiveCount = studentRepository.countByStatus(StudentStatus.INACTIVE);
        Long graduatedCount = studentRepository.countByStatus(StudentStatus.GRADUATED);

        System.out.println("Student counts by status:");
        System.out.println("  Active: " + activeCount);
        System.out.println("  Inactive: " + inactiveCount);
        System.out.println("  Graduated: " + graduatedCount);

        // Example 4: Find by teacher name pattern
        List<Student> drSmithStudents = studentRepository.findByTeacherNamePattern("%Dr. Smith%");
        System.out.println("Students with Dr. Smith: " + drSmithStudents.size());

        // Example 5: Complex query with multiple joins
        List<Student> complexResults = studentRepository.findByCoursesAndTeacher("Java Programming", "%Smith%");
        System.out.println("Students in Java Programming with Smith teacher: " + complexResults.size());
    }

    /**
     * DEMONSTRATE SPECIFICATIONS
     */
    private void demonstrateSpecifications() {
        System.out.println("\n=== DEMONSTRATING SPECIFICATIONS ===");

        // Example 1: Single specification
        List<Student> activeStudents = searchService.findStudentsByFirstName("Alice");
        System.out.println("Students named Alice: " + activeStudents.size());

        // Example 2: Combined specifications with AND
        List<Student> activeAliceStudents = searchService.findActiveStudentsByName("Alice");
        System.out.println("Active students named Alice: " + activeAliceStudents.size());

        // Example 3: Complex specification chain
        List<Student> complexResults = searchService.complexStudentSearch("gmail.com", "Java Programming", "Smith");
        System.out.println("Complex search results: " + complexResults.size());

        // Example 4: Dynamic specification building
        StudentSearchCriteria criteria = StudentSearchCriteria.builder()
                .status(StudentStatus.ACTIVE)
                .emailDomain("gmail.com")
                .courseName("Java Programming")
                .build();

        List<Student> dynamicResults = searchService.dynamicStudentSearch(criteria);
        System.out.println("Dynamic search with " + criteria.getActiveFiltersCount() + " filters: " + dynamicResults.size());

        // Example 5: Specifications with pagination
        Page<Student> pagedResults = searchService.searchStudentsWithPaging(criteria, 0, 10, "firstName", "asc");
        System.out.println("Paged results: " + pagedResults.getContent().size() + " of " + pagedResults.getTotalElements());
    }

    /**
     * DEMONSTRATE COMPLEX SEARCH SCENARIOS
     */
    private void demonstrateComplexSearches() {
        System.out.println("\n=== DEMONSTRATING COMPLEX SEARCH SCENARIOS ===");

        // Scenario 1: Multi-field search with statistics
        StudentSearchCriteria multiCriteria = StudentSearchCriteria.builder()
                .emailDomain("gmail.com")
                .status(StudentStatus.ACTIVE)
                .build();

        List<Student> statsResults = searchService.findStudentsWithStatistics(multiCriteria);

        // Scenario 2: Date range search
        StudentSearchCriteria dateRangeCriteria = StudentSearchCriteria.builder()
                .startDate(LocalDateTime.now().minusMonths(12))
                .endDate(LocalDateTime.now().minusMonths(1))
                .build();

        List<Student> dateRangeResults = searchService.dynamicStudentSearch(dateRangeCriteria);
        System.out.println("Students enrolled in specific date range: " + dateRangeResults.size());

        // Scenario 3: Text search across multiple fields
        StudentSearchCriteria textSearchCriteria = StudentSearchCriteria.builder()
                .searchTerm("Alice")
                .build();

        List<Student> textSearchResults = searchService.dynamicStudentSearch(textSearchCriteria);
        System.out.println("Text search for 'Alice' across all fields: " + textSearchResults.size());

        // Scenario 4: Empty criteria (should return all)
        StudentSearchCriteria emptyCriteria = new StudentSearchCriteria();
        List<Student> allResults = searchService.dynamicStudentSearch(emptyCriteria);
        System.out.println("Search with no criteria (all students): " + allResults.size());
    }
}

// ==================== COMPLETE SUMMARY ====================

/*
==========================================
COMPREHENSIVE SUMMARY OF ADVANCED JPA FEATURES
==========================================

1. @MODIFYING QUERIES:
   - Required for UPDATE, DELETE, INSERT operations
   - Returns number of affected rows
   - clearAutomatically = true clears EntityManager after update
   - flushAutomatically = true flushes pending changes before update
   - Must be combined with @Transactional

2. NAMED QUERIES (@NamedQuery):
   - Defined at entity level with @NamedQuery annotation
   - Pre-compiled and validated at startup
   - Better performance than dynamic queries
   - Reusable across different repositories
   - Support for both JPQL and native SQL

3. SPECIFICATIONS:
   - Enable dynamic query building at runtime
   - Use JPA Criteria API under the hood
   - Combine conditions with AND/OR logic
   - Support for complex relationships and joins
   - Perfect for search forms with optional criteria
   - Can be combined with pagination and sorting

4. BEST PRACTICES:

   WHEN TO USE @MODIFYING:
   - Bulk updates/deletes
   - Performance-critical operations
   - Simple field updates

   WHEN TO USE NAMED QUERIES:
   - Fixed queries used multiple times
   - Complex queries that don't change
   - Better performance requirements

   WHEN TO USE SPECIFICATIONS:
   - Dynamic search functionality
   - User-driven filtering
   - Complex conditional logic
   - When you need flexibility

5. PERFORMANCE CONSIDERATIONS:
   - Named queries: Fastest (pre-compiled)
   - Specifications: Good (type-safe, optimized)
   - Dynamic @Query: Slowest (parsed every time)

6. CODE ORGANIZATION:
   - Specifications in separate utility classes
   - Search criteria in DTOs
   - Service layer for complex business logic
   - Repository for data access only

This comprehensive system provides enterprise-level search and update capabilities
suitable for production applications with complex data access requirements.
*/
Expected SQL:
SELECT * FROM students WHERE first_name = ?;
        */

        System.out.println("Found " + results.size() + " students with first name: " + firstName);
        return results;
    }

/**
 * EXAMPLE 2: Combining Specifications with AND
 *
 * Find students by first name AND status
 * Specification.where() starts the chain
 * .and() combines with AND logic
 */
public List<Student> findActiveStudentsByName(String firstName) {
    Specification<Student> spec = Specification
            .where(StudentSpecifications.hasFirstName(firstName))
            .and(StudentSpecifications.hasStatus(StudentStatus.ACTIVE));

    List<Student> results = studentRepository.findAll(spec);
        /*
         */




