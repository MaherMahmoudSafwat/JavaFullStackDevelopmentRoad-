// ==================== ENHANCED TEACHER ENTITY ====================

package JPA_Course.example.JpaDemoExamples.Teacher;

import JPA_Course.example.JpaDemoExamples.Base.BaseEntity;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TEACHER ENTITY - Demonstrates multiple relationship types
 *
 * Expected Database Table:
 * CREATE TABLE teachers (
 *     id INTEGER AUTO_INCREMENT PRIMARY KEY,
 *     created_at TIMESTAMP NOT NULL,
 *     updated_at TIMESTAMP,
 *     teacher_name VARCHAR(255) NOT NULL,
 *     teacher_email VARCHAR(255) NOT NULL UNIQUE,
 *     teacher_password VARCHAR(255),
 *     course_id INTEGER,
 *     FOREIGN KEY (course_id) REFERENCES courses(id)
 * );
 */
@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends BaseEntity {

    @Column(name = "teacher_name", nullable = false)
    private String teacherName;

    @Column(name = "teacher_email", nullable = false, unique = true)
    private String teacherEmail;

    @Column(name = "teacher_password")
    private String teacherPassword;

    /**
     * MANY-TO-ONE RELATIONSHIP: Teacher BELONGS-TO Course
     *
     * Many teachers can teach one course
     * Creates course_id foreign key in teachers table
     *
     * Expected Database Column: course_id INTEGER
     * Expected Foreign Key: FOREIGN KEY (course_id) REFERENCES courses(id)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonIgnoreProperties({"teachers", "students"})
    private Courses courses;

    /**
     * ONE-TO-ONE RELATIONSHIP: Teacher HAS-A Student (inverse side)
     *
     * This is the INVERSE side of the one-to-one relationship
     * Student entity owns the relationship (has the foreign key)
     * mappedBy = "teacher": References the teacher field in Student entity
     *
     * Expected: NO foreign key column in teachers table
     * Expected: teacher_id foreign key in students table
     *
     * Navigation Example:
     * Teacher teacher = teacherRepository.findById(1);
     * Student assignedStudent = teacher.getStudent(); // Gets the student assigned to this teacher
     */
    @OneToOne(mappedBy = "teacher", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("teacher")
    private Student student;
}

// ==================== CASCADE TYPES EXPLAINED IN DETAIL ====================

/*
CASCADE TYPES - What happens to related entities when you perform operations

🎯 CascadeType.PERSIST:
When you save parent entity, save child entities too

Example:
Course course = new Course("Java Programming", "CS101", 3);
Student newStudent = new Student("John", "Doe", "john@email.com");
course.getStudents().add(newStudent); // newStudent not yet in database

courseRepository.save(course); // With CASCADE.PERSIST, newStudent is saved automatically

Without CASCADE.PERSIST:
- You'd get error: "object references an unsaved transient instance"
- You'd need to save student first: studentRepository.save(newStudent)

🎯 CascadeType.MERGE:
When you update parent entity, update child entities too

Example:
Course course = courseRepository.findById(1);
Student student = course.getStudents().get(0);
student.setFirstName("Updated Name");

courseRepository.save(course); // With CASCADE.MERGE, student update is saved too

🎯 CascadeType.REMOVE:
When you delete parent entity, delete child entities too

Example:
Course course = courseRepository.findById(1);
courseRepository.delete(course); // With CASCADE.REMOVE, all students are deleted too

⚠️ DANGEROUS: Use carefully! You might not want to delete students when deleting a course

🎯 CascadeType.REFRESH:
When you refresh parent entity, refresh child entities too

Example:
Course course = courseRepository.findById(1);
entityManager.refresh(course); // With CASCADE.REFRESH, all students are refreshed too

🎯 CascadeType.DETACH:
When you detach parent entity, detach child entities too

Example:
Course course = courseRepository.findById(1);
entityManager.detach(course); // With CASCADE.DETACH, all students become detached too

🎯 CascadeType.ALL:
All of the above operations (PERSIST, MERGE, REMOVE, REFRESH, DETACH)

COMMON COMBINATIONS:
- {CascadeType.PERSIST, CascadeType.MERGE}: Save and update, but don't delete
- CascadeType.ALL: Complete lifecycle management
- No cascade: Manual management of relationships
*/

// ==================== REGEX PATTERNS - DOUBLE BACKSLASH EXPLAINED ====================

/*
🎯 WHY DOUBLE BACKSLASHES (\\) IN JAVA REGEX?

JAVA STRING ESCAPE SEQUENCES:
In Java strings, backslash (\) is a special ESCAPE character
To include a literal backslash in a string, you need TWO backslashes (\\)

JAVA STRING        →    ACTUAL STRING VALUE
"Hello"            →    Hello
"Hello\nWorld"     →    Hello
                        World
"Hello\\nWorld"    →    Hello\nWorld
"\\d+"            →    \d+
"\\("             →    \(

REGEX SPECIAL CHARACTERS:
In regex, many characters have special meanings:
. = any character
( = start group  
) = end group
+ = one or more
* = zero or more
? = zero or one
[ = start character class
] = end character class

TO USE THESE LITERALLY IN REGEX:
You need to ESCAPE them with backslash:
\. = literal dot
\( = literal opening parenthesis
\) = literal closing parenthesis
\+ = literal plus sign

COMBINING JAVA + REGEX ESCAPING:
To get \( in regex, you need \\( in Java string
To get \. in regex, you need \\. in Java string
To get \d in regex, you need \\d in Java string

EXAMPLES:

1. PHONE NUMBER PATTERN: (123) 456-7890
   Regex needed: \(\d{3}\) \d{3}-\d{4}
   Java string: "\\(\\d{3}\\) \\d{3}-\\d{4}"
   
   Breakdown:
   \\(     → \(     → literal (
   \\d{3}  → \d{3}  → exactly 3 digits
   \\)     → \)     → literal )
   \\s     → \s     → whitespace
   \\d{3}  → \d{3}  → exactly 3 digits
   -       → -      → literal -
   \\d{4}  → \d{4}  → exactly 4 digits

2. EMAIL PATTERN: user@domain.com
   Regex needed: [a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}
   Java string: "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"
   
   The \\. becomes \. which means literal dot (not "any character")

3. SIMPLE ALTERNATIVES (but less precise):
   Instead of: \\( you could use [(] - character class with just (
   Instead of: \\. you could use [.] - character class with just .
   
   But \\( and \\. are more standard and readable

WHY NOT USE SINGLE BACKSLASH?
"Pattern.compile("\d+")" // Compilation error! \d is invalid escape sequence in Java string
Pattern.compile("\\d+")  // Correct! \\d becomes \d in the actual regex
*/