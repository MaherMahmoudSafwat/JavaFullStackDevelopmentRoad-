// ==================== JAVA FAKER FOR TEST DATA ====================

/*
ADD JAVA FAKER DEPENDENCY TO pom.xml:

<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>

Java Faker generates realistic fake data for testing:
- Names, addresses, phone numbers
- Lorem ipsum text
- Dates, numbers, booleans
- Internet-related data (emails, URLs)
- And much more!
*/

package JPA_Course.example.JpaDemoExamples.Examples;

import com.github.javafaker.Faker;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import JPA_Course.example.JpaDemoExamples.Student.StudentRepository;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Courses.CourseRepository;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import JPA_Course.example.JpaDemoExamples.Teacher.TeacherRepository;
import JPA_Course.example.JpaDemoExamples.Embedded.Address;
import JPA_Course.example.JpaDemoExamples.Resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * FAKE DATA GENERATION SERVICE
 * Uses Java Faker to create realistic test data
 */
@Service
@Transactional
public class FakeDataService {

    @Autowired private StudentRepository studentRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private TeacherRepository teacherRepository;
    @Autowired private ResourceRepository resourceRepository;

    private final Faker faker = new Faker(Locale.ENGLISH);
    private final Random random = new Random();

    /**
     * GENERATE FAKE STUDENTS
     * Creates realistic student data with addresses
     */
    public List<Student> generateFakeStudents(int count) {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Student student = new Student();

            // Basic info with Faker
            student.setFirstName(faker.name().firstName());
            student.setLastName(faker.name().lastName());
            student.setEmail(faker.internet().emailAddress());

            // Generate home address
            Address homeAddress = new Address();
            homeAddress.setStreetAddress(faker.address().streetAddress());
            homeAddress.setCity(faker.address().city());
            homeAddress.setState(faker.address().state());
            homeAddress.setZipCode(faker.address().zipCode());
            homeAddress.setCountry("USA");
            student.setHomeAddress(homeAddress);

            // Generate billing address (sometimes same as home)
            Address billingAddress;
            if (random.nextBoolean()) {
                // Same as home address
                billingAddress = new Address(
                        homeAddress.getStreetAddress(),
                        homeAddress.getCity(),
                        homeAddress.getState(),
                        homeAddress.getZipCode(),
                        homeAddress.getCountry()
                );
            } else {
                // Different billing address
                billingAddress = new Address();
                billingAddress.setStreetAddress(faker.address().streetAddress());
                billingAddress.setCity(faker.address().city());
                billingAddress.setState(faker.address().state());
                billingAddress.setZipCode(faker.address().zipCode());
                billingAddress.setCountry("USA");
            }
            student.setBillingAddress(billingAddress);

            students.add(student);
        }

        return studentRepository.saveAll(students);
    }

    /**
     * GENERATE FAKE COURSES
     */
    public List<Courses> generateFakeCourses(int count) {
        List<Courses> courses = new ArrayList<>();

        String[] subjects = {"Java", "Python", "JavaScript", "C++", "Data Science", "Machine Learning",
                "Web Development", "Mobile Development", "Database Design", "System Architecture"};
        String[] levels = {"Beginner", "Intermediate", "Advanced", "Expert"};

        for (int i = 0; i < count; i++) {
            Courses course = new Courses();

            String subject = subjects[random.nextInt(subjects.length)];
            String level = levels[random.nextInt(levels.length)];

            course.setCourseName(level + " " + subject);
            course.setCourseCode(generateCourseCode(subject));
            course.setCredits(random.nextInt(4) + 1); // 1-4 credits

            courses.add(course);
        }

        return courseRepository.saveAll(courses);
    }

    /**
     * GENERATE FAKE TEACHERS
     */
    public List<Teacher> generateFakeTeachers(int count, List<Courses> courses) {
        List<Teacher> teachers = new ArrayList<>();

        String[] titles = {"Dr.", "Prof.", "Mr.", "Ms.", "Mrs."};

        for (int i = 0; i < count; i++) {
            Teacher teacher = new Teacher();

            String title = titles[random.nextInt(titles.length)];
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();

            teacher.setTeacherName(title + " " + firstName + " " + lastName);
            teacher.setTeacherEmail(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@university.edu");
            teacher.setTeacherPassword(faker.internet().password());

            // Assign random course
            if (!courses.isEmpty()) {
                teacher.setCourses(courses.get(random.nextInt(courses.size())));
            }

            teachers.add(teacher);
        }

        return teacherRepository.saveAll(teachers);
    }

    /**
     * GENERATE FAKE RESOURCES with inheritance
     */
    public List<Resource> generateFakeResources(int count) {
        List<Resource> resources = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Resource resource;

            // Randomly choose resource type
            int typeChoice = random.nextInt(3);

            switch (typeChoice) {
                case 0: // Video Resource
                    VideoResource video = new VideoResource();
                    video.setResourceName("Video: " + faker.lorem().words(3).toString().replace(",", ""));
                    video.setResourceDescription(faker.lorem().sentence());
                    video.setVideoUrl("https://youtube.com/watch?v=" + faker.lorem().characters(11));
                    video.setDurationSeconds(faker.number().numberBetween(300, 3600)); // 5min to 1hour
                    resource = video;
                    break;

                case 1: // Text Resource
                    TextResource text = new TextResource();
                    text.setResourceName("Article: " + faker.lorem().words(4).toString().replace(",", ""));
                    text.setResourceDescription(faker.lorem().sentence());
                    text.setTextContent(faker.lorem().paragraphs(random.nextInt(5) + 1).toString());
                    text.updateWordCount();
                    resource = text;
                    break;

                case 2: // Image Resource
                default:
                    ImageResource image = new ImageResource();
                    image.setResourceName("Image: " + faker.lorem().words(3).toString().replace(",", ""));
                    image.setResourceDescription(faker.lorem().sentence());
                    image.setImageUrl("https://picsum.photos/" + (random.nextInt(800) + 200) + "/" + (random.nextInt(600) + 200));
                    image.setAltText(faker.lorem().sentence());
                    image.setFileSizeBytes(faker.number().numberBetween(50000L, 5000000L)); // 50KB to 5MB
                    resource = image;
                    break;
            }

            resources.add(resource);
        }

        return resourceRepository.saveAll(resources);
    }

    /**
     * Helper method to generate course codes
     */
    private String generateCourseCode(String subject) {
        String prefix = subject.length() >= 2 ? subject.substring(0, 2).toUpperCase() : "CS";
        int number = faker.number().numberBetween(100, 599);
        return prefix + number;
    }

/**
 * COMPREHENSIVE DATA GENERATION
 * Creates a complete fake education system
 */
// ==================== COMPREHENSIVE REGEX GUIDE ====================

/*

 */
