// ==================== SECTION ENTITY ====================

package JPA_Course.example.JpaDemoExamples.Section;

import JPA_Course.example.JpaDemoExamples.Base.BaseEntity;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Lecture.Lecture;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * SECTION ENTITY - Divides courses into manageable sections
 *
 * Real world example:
 * Course: "Java Programming"
 *   Section 1: "Java Basics"
 *     Lecture 1: "Variables and Data Types"  
 *     Lecture 2: "Control Structures"
 *   Section 2: "Object Oriented Programming"
 *     Lecture 1: "Classes and Objects"
 *     Lecture 2: "Inheritance"
 *
 * Expected Database Table:
 * CREATE TABLE sections (
 *     id INTEGER AUTO_INCREMENT PRIMARY KEY,
 *     created_at TIMESTAMP NOT NULL,
 *     updated_at TIMESTAMP,
 *     section_name VARCHAR(255) NOT NULL,
 *     section_order INTEGER,
 *     course_id INTEGER NOT NULL,
 *     FOREIGN KEY (course_id) REFERENCES courses(id)
 * );
 */
@Entity
@Table(name = "sections")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Section extends BaseEntity {

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @Column(name = "section_order")
    private Integer sectionOrder; // For ordering sections within a course

    /**
     * MANY-TO-ONE RELATIONSHIP: Section BELONGS-TO Course
     *
     * Many sections belong to one course
     * This is the OWNING side (no mappedBy)
     * Creates foreign key column in sections table
     *
     * @JoinColumn(name = "course_id"): Creates course_id foreign key column
     *
     * Expected Database Column: course_id INTEGER NOT NULL
     * Expected Foreign Key: FOREIGN KEY (course_id) REFERENCES courses(id)
     *
     * Navigation Example:
     * Section section = sectionRepository.findById(1);
     * Course course = section.getCourse(); // Gets the course this section belongs to
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnoreProperties({"sections", "students", "teachers"})
    private Courses course;

    /**
     * ONE-TO-MANY RELATIONSHIP: Section HAS-A lectures
     *
     * Each section contains multiple lectures in order
     * mappedBy = "section": Lecture entity owns the relationship
     *
     * Expected: section_id foreign key in lectures table
     *
     * Navigation Example:
     * Section section = sectionRepository.findById(1);
     * List<Lecture> sectionLectures = section.getLectures(); // All lectures in this section
     */
    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("lectureOrder ASC") // Orders lectures by their order number
    @JsonIgnoreProperties("section")
    private List<Lecture> lectures = new ArrayList<>();

    // Helper methods
    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
        lecture.setSection(this);
        System.out.println("Added lecture " + lecture.getLectureName() + " to section " + sectionName);
    }
}