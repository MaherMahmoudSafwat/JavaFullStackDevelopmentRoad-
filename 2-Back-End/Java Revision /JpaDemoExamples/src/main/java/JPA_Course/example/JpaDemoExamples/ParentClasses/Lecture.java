// ==================== LECTURE ENTITY ====================

package JPA_Course.example.JpaDemoExamples.Lecture;

import JPA_Course.example.JpaDemoExamples.Base.BaseEntity;
import JPA_Course.example.JpaDemoExamples.Section.Section;
import JPA_Course.example.JpaDemoExamples.Resource.Resource;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * LECTURE ENTITY - Individual lessons within sections
 *
 * Expected Database Table:
 * CREATE TABLE lectures (
 *     id INTEGER AUTO_INCREMENT PRIMARY KEY,
 *     created_at TIMESTAMP NOT NULL,
 *     updated_at TIMESTAMP,
 *     lecture_name VARCHAR(255) NOT NULL,
 *     lecture_description TEXT,
 *     lecture_order INTEGER,
 *     duration_minutes INTEGER,
 *     section_id INTEGER NOT NULL,
 *     FOREIGN KEY (section_id) REFERENCES sections(id)
 * );
 */
@Entity
@Table(name = "lectures")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecture extends BaseEntity {

    @Column(name = "lecture_name", nullable = false)
    private String lectureName;

    @Column(name = "lecture_description", columnDefinition = "TEXT")
    private String lectureDescription;

    @Column(name = "lecture_order")
    private Integer lectureOrder; // Order within the section

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    /**
     * MANY-TO-ONE RELATIONSHIP: Lecture BELONGS-TO Section
     *
     * Many lectures belong to one section
     * Creates section_id foreign key column
     *
     * Expected Database Column: section_id INTEGER NOT NULL
     * Expected Foreign Key: FOREIGN KEY (section_id) REFERENCES sections(id)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties({"lectures", "course"})
    private Section section;

    /**
     * ONE-TO-MANY RELATIONSHIP: Lecture HAS-A resources
     *
     * Each lecture can have multiple resources (videos, documents, images)
     * mappedBy = "lecture": Resource entity owns the relationship
     *
     * Expected: lecture_id foreign key in resources table
     */
    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lecture")
    private List<Resource> resources = new ArrayList<>();

    // Helper method
    public void addResource(Resource resource) {
        resources.add(resource);
        resource.setLecture(this);
        System.out.println("Added resource " + resource.getResourceName() + " to lecture " + lectureName);
    }
}
