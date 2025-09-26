@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {  // INHERITANCE: Student IS-A BaseEntity

    /**
     * VALIDATION ANNOTATIONS + COLUMN MAPPING
     *
     * @Column(name = "first_name", nullable = false):
     * - Maps Java field to database column "first_name"
     * - nullable = false creates NOT NULL constraint
     *
     * @NotEmpty: Validation - field cannot be empty string or null
     * @Size(min = 2, max = 57): Validation - length between 2 and 57 characters
     *
     * Expected Database Column: first_name VARCHAR(57) NOT NULL
     *
     * Validation Examples:
     * Valid: "John", "Mary", "Jean-Claude"
     * Invalid: "", null, "A", "Very very very long name exceeding 57 characters..."
     */
    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, max = 57, message = "First name must be between 2 and 57 characters")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    /**
     * EMAIL VALIDATION WITH REGEX PATTERN
     *
     * REGEX BREAKDOWN: ^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$
     *
     * WHY DOUBLE BACKSLASHES (\\)?
     * In Java strings, backslash (\) is an ESCAPE CHARACTER
     * To get a literal backslash in the regex, you need TWO backslashes in Java
     *
     * Java String: "\\d"    becomes    Actual Regex: \d
     * Java String: "\\("    becomes    Actual Regex: \(
     *
     * ^ = Start of string (must match from beginning)
     * [a-zA-Z0-9._%+-] = Character class for username part:
     *   - a-z: lowercase letters
     *   - A-Z: uppercase letters
     *   - 0-9: digits
     *   - .: dot
     *   - _: underscore
     *   - %: percent (email systems use this)
     *   - +: plus (Gmail uses: user+tag@gmail.com)
     *   - -: hyphen
     * + = One or more of the above characters
     * @ = Literal @ symbol (must appear exactly once)
     * [a-zA-Z0-9.-] = Character class for domain:
     *   - Letters, numbers, dots, hyphens only
     * + = One or more domain characters
     * \\. = ESCAPED DOT (literal dot, not "any character")
     *   - Without escape: . means "any character"
     *   - With escape: \\. means literal dot character
     * [a-zA-Z] = Domain extension (letters only)
     * {2,} = Two or more characters for extension
     * $ = End of string (must match to end)
     *
     * Valid emails: "user@domain.com", "test.user+tag@my-site.co.uk"
     * Invalid emails: "user@domain", "@domain.com", "user@.com"
     *
     * Expected Database Column: email VARCHAR(255) NOT NULL UNIQUE
     */
    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Please provide a valid email address")
    private String email;

    /**
     * MANY-TO-MANY RELATIONSHIP: Student HAS-A courses, Course HAS-A students
     *
     * @ManyToMany: Many students can be enrolled in many courses
     * mappedBy = "students": The Courses.students field owns this relationship
     * This means the foreign key is managed by the Courses entity
     *
     * @JsonIgnoreProperties: Prevents infinite JSON serialization loops
     * When serializing Student to JSON, don't include courses.students.courses.students...
     *
     * Expected Database: NO foreign key column in students table
     * Instead, creates join table: students_courses
     *
     * Navigation Examples:
     * Student student = studentRepository.findById(1);
     * List<Courses> studentCourses = student.getCourses(); // Gets all courses for this student
     *
     * SQL Generated:
     * SELECT c.* FROM courses c 
     * JOIN students_courses sc ON c.id = sc.course_id 
     * WHERE sc.student_id = 1;
     */
    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"students", "teachers"})
    private List<Courses> courses = new ArrayList<>();

    /**
     * ONE-TO-ONE RELATIONSHIP: Student HAS-A teacher (and vice versa)
     *
     * @OneToOne: Each student has exactly one teacher, each teacher has exactly one student
     * cascade = CascadeType.ALL: Operations on student cascade to teacher
     *   - When you save student, teacher is saved too
     *   - When you delete student, teacher is deleted too
     *   - When you update student, teacher updates are saved too
     *
     * @JoinColumn(name = "teacher_id", unique = true):
     * - Creates "teacher_id" foreign key column in students table
     * - unique = true: Ensures one-to-one relationship (no two students can have same teacher)
     *
     * Expected Database Column: teacher_id INTEGER UNIQUE
     * Expected Foreign Key: FOREIGN KEY (teacher_id) REFERENCES teachers(id)
     *
     * Usage Examples:
     * Student student = new Student();
     * Teacher teacher = new Teacher();
     * student.setTeacher(teacher); // Links student to teacher
     * studentRepository.save(student); // Saves both student AND teacher (cascade)
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", unique = true)
    @JsonIgnoreProperties("student")
    private Teacher teacher;

    // Helper methods for managing relationships
    public void addCourse(Courses course) {
        courses.add(course);
        course.getStudents().add(this);
    }

    public void removeCourse(Courses course) {
        courses.remove(course);
        course.getStudents().remove(this);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
