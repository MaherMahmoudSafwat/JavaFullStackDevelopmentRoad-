package com.JPA.JPA_Application.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * CourseMaterial entity representing course materials in the system.
 * Has a one-to-one bidirectional relationship with Course.
 */
@Data // Lombok: Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Lombok: Full constructor with all fields
@NoArgsConstructor // Lombok: No-args constructor (required by JPA)
@Entity // JPA: Marks this as a persistent entity (maps to DB table)
@Component // Spring: Makes this class a managed bean
@ToString(exclude = "course") // Lombok: Excludes course from toString to prevent circular references
public class CourseMaterial {

    /**
     * Primary Key Configuration
     */
    @Id // JPA: Identifies the primary key
    @SequenceGenerator( // Sequence-based ID generation
            name = "course_material_sequence",
            sequenceName = "course_material_sequence",
            allocationSize = 1 // Increment value
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, // Uses DB sequence
            generator = "course_material_sequence"
    )
    private Long courseMaterialId;

    /**
     * Basic Field Mapping
     */
    @Column(nullable = false) // Not null constraint
    private String url; // Material URL

    /**
     * ONE-TO-ONE RELATIONSHIP WITH COURSE
     *
     * This is the owning side of the relationship as it contains the foreign key.
     * The inverse side is in the Course entity with mappedBy="course".
     */
    @OneToOne(
            cascade = CascadeType.ALL, // All operations cascade to Course
            fetch = FetchType.LAZY, // Load course only when accessed
            optional = false // Course must exist (NOT NULL constraint)
    )
    @JoinColumn(
            name = "course_id", // Foreign key column name
            referencedColumnName = "courseId", // References Course's PK
            foreignKey = @ForeignKey(name = "FK_COURSE_MATERIAL_COURSE") // Named foreign key
    )
    private Course course;
}