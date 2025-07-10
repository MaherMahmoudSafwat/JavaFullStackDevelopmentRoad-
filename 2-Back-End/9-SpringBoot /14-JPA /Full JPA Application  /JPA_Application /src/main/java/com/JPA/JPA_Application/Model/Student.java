package com.JPA.JPA_Application.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Student entity representing a student in the system.
 * Demonstrates JPA entity mapping with embedded components.
 */
@Data // Lombok: Generates getters, setters, toString, equals, hashCode
@AllArgsConstructor // Lombok: Constructor with all fields
@NoArgsConstructor // Lombok: No-args constructor (JPA requirement)
@Builder // Lombok: Enables builder pattern
@Entity // JPA: Marks as persistent entity (maps to DB table)
@Table(
        name = "tbl_student", // Custom table name
        uniqueConstraints = @UniqueConstraint( // Unique constraint
                name = "StudentID_Constraint",
                columnNames = "email_address" // Unique email constraint
        ),
        indexes = { // Database indexes
                @Index(name = "idx_email", columnList = "email_address"), // Email index
                @Index(name = "idx_name", columnList = "firstName,lastName") // Name composite index
        }
)
public class Student {

    /**
     * Primary Key Configuration
     */
    @Id // JPA: Identifies primary key
    @SequenceGenerator( // Sequence generator for IDs
            name = "student_sequence", // Generator name
            sequenceName = "student_sequence", // DB sequence name
            allocationSize = 1 // Increment size
    )
    @GeneratedValue( // ID generation strategy
            strategy = GenerationType.SEQUENCE, // Uses DB sequence
            generator = "student_sequence" // References generator
    )
    private Long studentId;

    /**
     * Basic Student Information
     */
    @Column(nullable = false, length = 50) // First name cannot be null
    private String firstName; // Maps to firstName column

    @Column(nullable = false, length = 50) // Last name cannot be null
    private String lastName; // Fixed capitalization (from LastName)

    /**
     * Email Information
     */
    @Column(
            name = "email_address", // Custom column name
            nullable = false, // Required field
            unique = true, // Ensures email uniqueness
            length = 100 // Maximum length
    )
    private String emailId;

    /**
     * Embedded Guardian Information
     * All guardian fields will be included in student table
     * with column names defined in Guardian class
     */
    @Embedded
    private Guardian guardian; // Changed from Guardian1 to follow conventions

    // Builder customization example
    public static StudentBuilder builder(String email) {
        return new StudentBuilder().emailId(email);
    }
}