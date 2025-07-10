package com.JPA.JPA_Application.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Teacher entity representing an instructor in the system.
 * Demonstrates one-to-many relationship with Course entities.
 */
@Data // Lombok: Generates getters, setters, toString, equals, hashCode
@AllArgsConstructor // Lombok: Constructor with all fields
@NoArgsConstructor // Lombok: No-args constructor (JPA requirement)
@Entity // JPA: Marks as persistent entity
@Table(name = "teachers") // Explicit table name
public class Teacher {

    /**
     * Primary Key Configuration
     */
    @Id // JPA: Identifies primary key
    @SequenceGenerator( // Sequence-based ID generation
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_sequence"
    )
    private Long teacherId;

    /**
     * Basic Teacher Information
     */
    @Column(nullable = false, length = 50) // Required field
    private String firstName;

    @Column(nullable = false, length = 50) // Required field
    private String lastName;

    /**
     * ONE-TO-MANY RELATIONSHIP WITH COURSES
     * <p>
     * This is the inverse side of the relationship (Course owns the FK)
     * mappedBy indicates the field in Course that manages the relationship
     */
    @OneToMany(
            mappedBy = "teacher", // Field in Course that owns the relationship
            cascade = CascadeType.ALL, // All operations cascade to courses
            orphanRemoval = true, // Automatically remove orphaned courses
            fetch = FetchType.LAZY // Recommended for collections
    )
    private List<Course> courses = new ArrayList<>(); // Initialize collection
}
