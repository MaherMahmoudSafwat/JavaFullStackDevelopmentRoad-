// ==================== EMBEDDED ID CLASS ====================

package JPA_Course.example.JpaDemoExamples.Examples;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * EMBEDDED ID - Composite primary key
 *
 * Sometimes you need a primary key made up of multiple columns
 * For example: student enrollment in a specific course section
 * Primary key: studentId + courseId + sectionId
 *
 * Requirements for Embedded ID:
 * 1. Must be @Embeddable
 * 2. Must implement Serializable
 * 3. Must have equals() and hashCode() methods (Lombok @Data provides these)
 * 4. Must have no-argument constructor
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "section_id")
    private Integer sectionId;
}

// ==================== ENTITY USING EMBEDDED ID ====================

package JPA_Course.example.JpaDemoExamples.Embedded;

import JPA_Course.example.JpaDemoExamples.Base.BaseEntity;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Section.Section;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * STUDENT ENROLLMENT - Entity using embedded ID
 *
 * This represents a student's enrollment in a specific course section
 * Primary key is composite: studentId + courseId + sectionId
 *
 * Expected Database Table:
 * CREATE TABLE student_enrollments (
 *     student_id INTEGER NOT NULL,        -- Part of composite primary key
 *     course_id INTEGER NOT NULL,         -- Part of composite primary key
 *     section_id INTEGER NOT NULL,        -- Part of composite primary key
 *     enrollment_date TIMESTAMP,
 *     grade VARCHAR(2),
 *     status VARCHAR(20),
 *     created_at TIMESTAMP NOT NULL,      -- From BaseEntity
 *     updated_at TIMESTAMP,               -- From BaseEntity
 *     PRIMARY KEY (student_id, course_id, section_id),
 *     FOREIGN KEY (student_id) REFERENCES students(id),
 *     FOREIGN KEY (course_id) REFERENCES courses(id),
 *     FOREIGN KEY (section_id) REFERENCES sections(id)
 * );
 */
@Entity
@Table(name = "student_enrollments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollment {

    /**
     * @EmbeddedId - Uses embedded composite primary key
     * The primary key is made up of studentId + courseId + sectionId
     */
    @EmbeddedId
    private StudentCourseId id;

    /**
     * Additional fields specific to the enrollment
     */
    @Column(name = "enrollment_date")
    private LocalDateTime enrollmentDate;

    @Column(name = "grade", length = 2)
    @Pattern(regexp = "^[A-F][+-]?$", message = "Grade must be A-F with optional + or -")
    private String grade;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Relationships using the embedded ID fields
     * We need to map these using the embedded ID's fields
     */
    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Courses course;

    @ManyToOne
    @JoinColumn(name = "section_id", insertable = false, updatable = false)
    private Section section;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (enrollmentDate == null) {
            enrollmentDate = LocalDateTime.now();
        }
        if (status == null) {
            status = EnrollmentStatus.ENROLLED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}

/**
 * ENROLLMENT STATUS ENUM
 */
public enum EnrollmentStatus {
    ENROLLED,
    DROPPED,
    COMPLETED,
    WITHDRAWN
}
/**
 * ENHANCED STUDENT ENTITY with embedded address
 *
 * Expected Database Table Structure:
 * CREATE TABLE students (
 *     -- From BaseEntity:
 *     id INTEGER AUTO_INCREMENT PRIMARY KEY,
 *     created_at TIMESTAMP NOT NULL,
 *     updated_at TIMESTAMP,
 *
 *     -- Student specific fields:
 *     first_name VARCHAR(57) NOT NULL,
 *     last_name VARCHAR(255) NOT NULL,
 *     email VARCHAR(255) NOT NULL UNIQUE,
 *
 *     -- From embedded Address (home address):
 *     street_address VARCHAR(255),
 *     city VARCHAR(255),
 *     state VARCHAR(255),
 *     zip_code VARCHAR(255),
 *     country VARCHAR(255),
 *
 *     -- From embedded Address (billing address) with custom column names:
 *     billing_street VARCHAR(255),
 *     billing_city VARCHAR(255),
 *     billing_state VARCHAR(255),
 *     billing_zip VARCHAR(255),
 *     billing_country VARCHAR(255),
 *
 *     -- Relationships:
 *     teacher_id INTEGER UNIQUE,
 *     FOREIGN KEY (teacher_id) REFERENCES teachers(id)
 * );
 */
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {

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
     * EMBEDDED ADDRESS - Home Address
     * @Embedded - Embeds the Address fields directly into this table
     * All Address fields become columns in students table with their original names
     */
    @Embedded
    private Address homeAddress;

    /**
     * EMBEDDED ADDRESS - Billing Address with custom column names
     * @AttributeOverrides - Customizes the column names for this embedded address
     * This allows us to have multiple addresses in the same table
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "streetAddress", column = @Column(name = "billing_street")),
            @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
            @AttributeOverride(name = "state", column = @Column(name = "billing_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "billing_zip")),
            @AttributeOverride(name = "country", column = @Column(name = "billing_country"))
    })
    private Address billingAddress;

    /**
     * Many-to-Many relationship with Courses
     */
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"students", "teachers"})
    private List<Courses> courses = new ArrayList<>();

    /**
     * One-to-One relationship with Teacher
     */
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

    /**
     * Business methods using embedded addresses
     */
    public boolean hasSameHomeAndBillingAddress() {
        if (homeAddress == null || billingAddress == null) {
            return false;
        }
        return homeAddress.equals(billingAddress);
    }

    public String getFormattedHomeAddress() {
        return homeAddress != null ? homeAddress.getFullAddress() : "No home address";
    }

    public String getFormattedBillingAddress() {
        return billingAddress != null ? billingAddress.getFullAddress() : "No billing address";
    }
}

// ==================== POLYMORPHIC QUERIES EXPLAINED ====================

/*
WHAT ARE POLYMORPHIC QUERIES?

POLYMORPHIC QUERIES allow you to query a parent entity and get back all subtypes.
Think of it like asking "Give me all Animals" and getting back Dogs, Cats, and Birds.

In our system:
- Query Resource entity
- Get back VideoResource, TextResource, and ImageResource objects
- All in the same result list
- JPA automatically creates the correct Java object type based on discriminator column

WHY ARE THEY USEFUL?
- You can work with different types uniformly
- Process mixed collections of related objects
- Apply common operations across all subtypes
- Still access type-specific methods when needed
*/

package JPA_Course.example.JpaDemoExamples.Resource;

import JPA_Course.example.JpaDemoExamples.Base.BaseEntity;
import JPA_Course.example.JpaDemoExamples.Lecture.Lecture;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RESOURCE ENTITY - Parent class for inheritance hierarchy
 *
 * Single Table Inheritance Strategy:
 * All resource types stored in one table with discriminator column
 */
@Entity
@Table(name = "resources")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "resource_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Resource extends BaseEntity {

    @Column(name = "resource_name", nullable = false)
    protected String resourceName;

    @Column(name = "resource_description", columnDefinition = "TEXT")
    protected String resourceDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    @JsonIgnoreProperties({"resources", "section"})
    protected Lecture lecture;

    /**
     * Abstract method - each subtype must implement
     * This enables polymorphic behavior
     */
    public abstract String getResourceInfo();
}

// ==================== RESOURCE SUBTYPES ====================

@Entity
@DiscriminatorValue("VIDEO")
@Data
@NoArgsConstructor
public class VideoResource extends Resource {

    @Column(name = "video_url", length = 500)
    private String videoUrl;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Override
    public String getResourceInfo() {
        return "Video: " + resourceName + " (" + getFormattedDuration() + ")";
    }

    public String getFormattedDuration() {
        if (durationSeconds == null) return "Unknown duration";
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}

@Entity
@DiscriminatorValue("TEXT")
@Data
@NoArgsConstructor
public class TextResource extends Resource {

    @Lob
    @Column(name = "text_content")
    private String textContent;

    @Column(name = "word_count")
    private Integer wordCount;

    @Override
    public String getResourceInfo() {
        return "Text: " + resourceName + " (" + wordCount + " words)";
    }

    public void updateWordCount() {
        this.wordCount = textContent != null ? textContent.split("\\s+").length : 0;
    }
}

@Entity
@DiscriminatorValue("IMAGE")
@Data
@NoArgsConstructor
public class ImageResource extends Resource {

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "alt_text")
    private String altText;

    @Column(name = "file_size_bytes")
    private Long fileSizeBytes;

    @Override
    public String getResourceInfo() {
        return "Image: " + resourceName + " (" + getFormattedFileSize() + ")";
    }

    public String getFormattedFileSize() {
        if (fileSizeBytes == null) return "Unknown size";

        if (fileSizeBytes < 1024) {
            return fileSizeBytes + " bytes";
        } else if (fileSizeBytes < 1024 * 1024) {
            return String.format("%.1f KB", fileSizeBytes / 1024.0);
        } else {
            return String.format("%.1f MB", fileSizeBytes / (1024.0 * 1024.0));
        }
    }
}
