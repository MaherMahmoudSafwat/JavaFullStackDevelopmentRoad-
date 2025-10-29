// ==================== UNIDIRECTIONAL VS BIDIRECTIONAL RELATIONSHIPS ====================

/*
🎯 UNIDIRECTIONAL vs BIDIRECTIONAL RELATIONSHIPS EXPLAINED

UNIDIRECTIONAL RELATIONSHIP:
- Only ONE entity knows about the other
- Like a one-way street
- You can navigate from A to B, but not from B to A

Example of Unidirectional:
@Entity
public class Student {
    @ManyToMany
    private List<Course> courses; // Student knows about courses
}

@Entity
public class Course {
    // No reference to students - Course doesn't know about students
}

Navigation:
student.getCourses(); // ✅ Works - can get courses from student
course.getStudents(); // ❌ Doesn't exist - can't get students from course

BIDIRECTIONAL RELATIONSHIP:
- BOTH entities know about each other
- Like a two-way street
- You can navigate from A to B AND from B to A
- ONE side is the "owning" side (manages the foreign key)
- OTHER side is the "inverse" side (uses mappedBy)

Example of Bidirectional:
@Entity
public class Student {
    @ManyToMany(mappedBy = "students") // Inverse side - doesn't own foreign key
    private List<Course> courses;
}

@Entity
public class Course {
    @ManyToMany // Owning side - manages the foreign key
    @JoinTable(name = "students_courses")
    private List<Student> students;
}

Navigation:
student.getCourses(); // ✅ Works - can get courses from student
course.getStudents(); // ✅ Works - can get students from course

WHICH SIDE OWNS THE RELATIONSHIP?
- The side WITHOUT mappedBy is the owning side
- The side WITH mappedBy is the inverse side
- Only the owning side's annotations affect database structure
- Inverse side is ignored for database schema generation

WHY BIDIRECTIONAL?
- More convenient navigation
- Can query from either direction
- Better object-oriented design
- Reflects real-world relationships
*/

// ==================== ENHANCED COURSES ENTITY ====================

package JPA_Course.example.JpaDemoExamples.Courses;

import JPA_Course.example.JpaDemoExamples.Base.BaseEntity;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import JPA_Course.example.JpaDemoExamples.Section.Section;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * COURSES ENTITY - The central entity in our educational system
 *
 * Expected Database Table Structure:
 * CREATE TABLE courses (
 *     id INTEGER AUTO_INCREMENT PRIMARY KEY,          -- From BaseEntity
 *     created_at TIMESTAMP NOT NULL,                  -- From BaseEntity
 *     updated_at TIMESTAMP,                           -- From BaseEntity
 *     course_name VARCHAR(255) NOT NULL,             -- Course specific
 *     course_code VARCHAR(255) NOT NULL UNIQUE,      -- Course specific
 *     credits INTEGER                                 -- Course specific
 * );
 */
@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courses extends BaseEntity {

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "course_code", nullable = false, unique = true)
    private String courseCode;

    @Column(name = "credits")
    private Integer credits;

    /**
     * MANY-TO-MANY RELATIONSHIP: Course HAS-A students (OWNING SIDE)
     *
     * This is the OWNING side because there's no mappedBy
     * This side controls the database structure
     *
     * @JoinTable: Specifies the join table details
     *   - name = "students_courses": Join table name
     *   - joinColumns: This entity's foreign key in join table
     *   - inverseJoinColumns: Other entity's foreign key in join table
     *
     * CASCADE TYPES EXPLAINED:
     * - PERSIST: When you save course, save new students too
     * - MERGE: When you update course, update students too
     * - REMOVE: When you delete course, delete students too (DANGEROUS!)
     * - REFRESH: When you refresh course, refresh students too
     * - DETACH: When you detach course, detach students too
     * - ALL: All of the above operations
     *
     * CASCADE EXAMPLE:
     * Course course = new Course();
     * Student newStudent = new Student(); // Not yet saved to database
     * course.getStudents().add(newStudent);
     * courseRepository.save(course); // With CASCADE.PERSIST, newStudent is saved automatically
     *
     * Expected Database Structure:
     * CREATE TABLE students_courses (
     *     student_id INTEGER NOT NULL,
     *     course_id INTEGER NOT NULL,
     *     PRIMARY KEY (student_id, course_id),
     *     FOREIGN KEY (student_id) REFERENCES students(id),
     *     FOREIGN KEY (course_id) REFERENCES courses(id)
     * );
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @JsonIgnoreProperties({"courses", "teacher"})
    private List<Student> students = new ArrayList<>();

    /**
     * ONE-TO-MANY RELATIONSHIP: Course HAS-A teachers
     *
     * @OneToMany: One course can have many teachers
     * mappedBy = "courses": The Teacher.courses field owns this relationship
     * This means Teacher entity has the foreign key column
     *
     * CASCADE.ALL: When you save/delete course, save/delete all teachers
     *
     * Expected: NO foreign key in courses table
     * Expected: course_id foreign key column in teachers table
     *
     * Navigation Example:
     * Course course = courseRepository.findById(1);
     * List<Teacher> courseTeachers = course.getTeachers(); // Lazy loading triggers SQL
     *
     * SQL Generated:
     * SELECT * FROM teachers WHERE course_id = 1;
     */
    @OneToMany(mappedBy = "courses", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"courses", "student"})
    private List<Teacher> teachers = new ArrayList<>();

    /**
     * ONE-TO-MANY RELATIONSHIP: Course HAS-A sections
     *
     * Each course is divided into sections (like chapters)
     * Each section contains multiple lectures
     *
     * Expected: section_id foreign key in sections table pointing to courses.id
     */
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("course")
    private List<Section> sections = new ArrayList<>();

    // Helper methods for managing bidirectional relationships
    public void addStudent(Student student) {
        students.add(student);
        student.getCourses().add(this);
        System.out.println("Added student " + student.getFullName() + " to course " + courseName);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.getCourses().remove(this);
        System.out.println("Removed student " + student.getFullName() + " from course " + courseName);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacher.setCourses(this);
        System.out.println("Added teacher " + teacher.getTeacherName() + " to course " + courseName);
    }

    public void addSection(Section section) {
        sections.add(section);
        section.setCourse(this);
        System.out.println("Added section " + section.getSectionName() + " to course " + courseName);
    }
}
