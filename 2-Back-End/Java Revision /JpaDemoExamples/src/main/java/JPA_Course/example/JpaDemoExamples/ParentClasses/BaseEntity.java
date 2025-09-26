package JPA_Course.example.JpaDemoExamples.ParentClasses;// ==================== 1. INHERITANCE EXPLAINED IN SIMPLE TERMS ====================

/*
🎯 WHAT IS INHERITANCE?

Think of inheritance like a FAMILY TREE:
- You inherit traits from your parents (eye color, height, etc.)
- In programming, child classes inherit fields and methods from parent classes

REAL WORLD EXAMPLE:
- Animal (parent) has: name, age, eat(), sleep()
- Dog (child) inherits: name, age, eat(), sleep() + adds: bark(), wagTail()
- Cat (child) inherits: name, age, eat(), sleep() + adds: meow(), purr()

Both Dog and Cat ARE-A Animal, but they have their own special behaviors too.
*/

// ==================== BASE ENTITY CLASS (PARENT OF ALL ENTITIES) ====================

import jakarta.persistence.*;

import java.time.LocalDateTime;
// ==================== WHY CREATING RELATIONSHIPS BETWEEN ENTITIES? ====================

/*
🎯 WHY DO WE NEED RELATIONSHIPS?

REAL WORLD ANALOGY:
- A Student takes many Courses
- A Course has many Students
- A Course contains many Sections
- A Section has many Lectures
- A Lecture uses many Resources (videos, documents, images)

WITHOUT RELATIONSHIPS:
- You'd have to manually manage connections
- Data would be duplicated everywhere
- Inconsistencies would occur
- No referential integrity

WITH JPA RELATIONSHIPS:
- Database enforces data consistency
- Automatic loading of related objects
- Cascade operations (save parent, save children automatically)
- Navigation between objects is seamless

EXAMPLE WITHOUT RELATIONSHIPS (BAD):
Student student = new Student("John", "Doe", "john@email.com");
String courseName = "Java Programming";  // Just a string - no connection to actual course
// How do you know this course exists? How do you get course details?

EXAMPLE WITH RELATIONSHIPS (GOOD):
Student student = new Student("John", "Doe", "john@email.com");
Course course = courseRepository.findById(1);
student.getCourses().add(course);  // Real object reference with all course data
course.getStudents().add(student); // Bidirectional - course knows about student too
*/

// ==================== BASE ENTITY (PARENT FOR ALL) ====================

package JPA_Course.example.JpaDemoExamples.Base;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * @MappedSuperclass - THIS IS NOT A TABLE, BUT A TEMPLATE FOR OTHER ENTITIES
 *
 * WHY USE BaseEntity?
 * - Every entity needs: id, createdAt, updatedAt
 * - Instead of copying this code in every class, we inherit from BaseEntity
 * - All child entities automatically get these fields in their database tables
 *
 * Expected Output: NO table created for BaseEntity itself
 * But every child entity table will include these columns:
 * - id INTEGER PRIMARY KEY AUTO_INCREMENT
 * - created_at TIMESTAMP NOT NULL
 * - updated_at TIMESTAMP
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {

    /**
     * PRIMARY KEY for all entities
     *
     * @Id - Marks this as the primary key
     * @GeneratedValue - Database automatically assigns values (1, 2, 3, ...)
     * IDENTITY strategy - Uses database's AUTO_INCREMENT feature
     * <p>
     * Expected Database Output in ALL child tables:
     * id INTEGER AUTO_INCREMENT PRIMARY KEY
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * AUDIT FIELD: When was this record created?
     *
     * @Column configurations:
     * - name = "created_at" - Column name in database
     * - nullable = false - Cannot be NULL (required field)
     * - updatable = false - Cannot be changed after creation
     * <p>
     * Expected Database Output in ALL child tables:
     * created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    protected LocalDateTime createdAt;

    /**
     * AUDIT FIELD: When was this record last modified?
     * Can be NULL initially, gets set on first update
     * <p>
     * Expected Database Output in ALL child tables:
     * updated_at TIMESTAMP NULL
     */
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    /**
     * JPA LIFECYCLE CALLBACK: Executed BEFORE saving NEW entity
     *
     * @PrePersist - Called automatically by JPA before INSERT
     * <p>
     * When you do: studentRepository.save(newStudent)
     * JPA automatically calls this method BEFORE the SQL INSERT
     * <p>
     * Expected Console Output:
     * "Setting created_at timestamp: 2024-01-15T10:30:45.123"
     * <p>
     * Expected SQL Result:
     * INSERT INTO students (created_at, first_name, last_name, email)
     * VALUES ('2024-01-15 10:30:45.123', 'John', 'Doe', 'john@email.com');
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        System.out.println("🕒 Setting created_at timestamp: " + createdAt);
    }

    /**
     * JPA LIFECYCLE CALLBACK: Executed BEFORE updating EXISTING entity
     *
     * @PreUpdate - Called automatically by JPA before UPDATE
     * <p>
     * When you modify an existing entity and save:
     * student.setFirstName("NewName");
     * studentRepository.save(student); // Triggers @PreUpdate
     * <p>
     * Expected Console Output:
     * "Setting updated_at timestamp: 2024-01-15T11:45:20.456"
     * <p>
     * Expected SQL Result:
     * UPDATE students SET first_name = 'NewName', updated_at = '2024-01-15 11:45:20.456'
     * WHERE id = 1;
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        System.out.println("🕒 Setting updated_at timestamp: " + updatedAt);
    }

    // Getters and setters (accessible by child classes)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
