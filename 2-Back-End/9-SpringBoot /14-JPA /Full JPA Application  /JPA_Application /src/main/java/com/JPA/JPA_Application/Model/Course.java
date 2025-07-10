package com.JPA.JPA_Application.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "courses") // Explicitly names the database table
public class Course {

    @Id // Marks this field as the primary key
    @SequenceGenerator( // Configures sequence-based ID generation
            name = "course_sequence", // Unique generator name
            sequenceName = "course_sequence", // Database sequence name
            allocationSize = 1 // Increment size
    )
    @GeneratedValue( // Specifies how IDs are generated
            strategy = GenerationType.SEQUENCE, // Uses database sequence
            generator = "course_sequence" // References the generator above
    )
    private Long courseId;

    // Basic field with JPA column definition
    @Column(nullable = false) // Ensures this column cannot be null in DB
    private String title;

    // Column with both null constraint and length limit
    @Column(nullable = false, length = 3) // VARCHAR(3) NOT NULL
    private String credit;

    // ONE-TO-ONE RELATIONSHIP WITH COURSE MATERIAL
    @OneToOne(
            mappedBy = "course", // Indicates CourseMaterial owns the relationship
            cascade = CascadeType.ALL, // All operations cascade to CourseMaterial
            orphanRemoval = true // Automatically removes orphaned CourseMaterial
    )
    private CourseMaterial courseMaterial;

    // MANY-TO-ONE RELATIONSHIP WITH TEACHER
    @ManyToOne(
            fetch = FetchType.LAZY // Loads teacher only when accessed
    )
    @JoinColumn(
            name = "teacher_id" // Foreign key column name in courses table
    )
    private Teacher teacher;

    // MANY-TO-MANY RELATIONSHIP WITH STUDENT
    @ManyToMany(cascade = CascadeType.ALL) // All operations cascade to students
    @JoinTable( // Configures the join table
            name = "student_course_map", // Join table name
            joinColumns = @JoinColumn( // Column for this entity (Course)
                    name = "course_id", // Column name in join table
                    referencedColumnName = "courseId" // References courseId in Course
            ),
            inverseJoinColumns = @JoinColumn( // Column for other entity (Student)
                    name = "student_id", // Column name in join table
                    referencedColumnName = "studentId" // References studentId in Student
            )
    )
    private List<Student> students; // Changed to lowercase for convention

    // HELPER METHOD FOR BIDIRECTIONAL RELATIONSHIP
    /**
     * Properly sets both sides of the one-to-one relationship
     * @param material The CourseMaterial to associate with this Course
     */
    public void setCourseMaterial(CourseMaterial material) {
        this.courseMaterial = material;
        if (material != null) {
            material.setCourse(this); // Maintain bidirectional relationship
        }
    }
}