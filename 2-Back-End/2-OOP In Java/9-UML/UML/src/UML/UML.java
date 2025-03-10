package UML;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

// Superclass Person
class Person {
    private String name;
    private String dob;

    public Person(String name, String dob) {
        this.name = name;
        this.dob = dob;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }
}

// Student subclass inherits Person
class Student extends Person {
    private String studentId;
    private float gpa;

    public Student(String name, String dob, String studentId, float gpa) {
        super(name, dob); // Calls the constructor of Person
        this.studentId = studentId;
        this.gpa = gpa;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public float getGpa() {
        return gpa;
    }
}

// Professor subclass inherits Person
class Professor extends Person {
    private String professorId;
    private String specialization;
    private List<Student> mentees; // Unary association (Professor can mentor many Students)

    public Professor(String name, String dob, String professorId, String specialization) {
        super(name, dob);
        this.professorId = professorId;
        this.specialization = specialization;
        this.mentees = new ArrayList<>(); // Professor starts with an empty list of mentees
    }

    // Getters and Setters
    public String getProfessorId() {
        return professorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Student> getMentees() {
        return mentees;
    }

    public void addMentee(Student student) {
        mentees.add(student); // Add a student to the mentee list
    }
}

// Employee subclass inherits Person
class Employee extends Person {
    private String employeeId;
    private String position;

    public Employee(String name, String dob, String employeeId, String position) {
        super(name, dob);
        this.employeeId = employeeId;
        this.position = position;
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }
}

// Course class (Composition)
class Course {
    private String courseId;
    private String courseName;
    private int credits;
    private CourseAddress address;
    private PhoneNumber phoneNumber;

    // Constructor with Composition: Address and PhoneNumber are created inside Course
    public Course(String courseId, String courseName, int credits, String street, String city, String zipcode, String phoneNumber) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;

        // Composition: Creating new objects for Address and PhoneNumber
        this.address = new CourseAddress(street, city, zipcode); // Course owns the Address object
        this.phoneNumber = new PhoneNumber(phoneNumber); // Course owns the PhoneNumber object
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public CourseAddress getAddress() {
        return address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}

// CourseAddress class (Composition)
class CourseAddress {
    private String street;
    private String city;
    private String zipcode;

    public CourseAddress(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    // Getters and Setters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}

// PhoneNumber class (Composition)
class PhoneNumber {
    private String number;

    public PhoneNumber(String number) {
        this.number = number;
    }

    // Getters and Setters
    public String getNumber() {
        return number;
    }
}

// Department class (Aggregation)
class Department {
    private String departmentName;
    private List<Professor> professors;
    private List<Course> courses;

    public Department(String departmentName, List<Professor> professors, List<Course> courses) {
        this.departmentName = departmentName;
        this.professors = professors;
        this.courses = courses;
    }

    // Getters and Setters
    public String getDepartmentName() {
        return departmentName;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public List<Course> getCourses() {
        return courses;
    }
}

// CourseEnrollment class (Many-to-Many Relationship)
class CourseEnrollment {
    private Student student;
    private Course course;
    private Date enrollmentDate;

    public CourseEnrollment(Student student, Course course, Date enrollmentDate) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }
}

// Address class (Has-A Relationship)
class Address {
    private String street;
    private String city;
    private String zipcode;

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    // Getters and Setters
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }
}

// Main class for testing
public class UML {
    public static void main(String[] args) {
        // Creating instances and linking them
        Professor professor = new Professor("Dr. John Doe", "01/01/1970", "P001", "Computer Science");

        // Creating Course with Composition: Address and PhoneNumber are created inside Course
        Course course = new Course("CS101", "Introduction to Computer Science", 3, "123 Main St", "Some City", "12345", "555-1234");

        // Aggregation: Department has Professors and Courses
        Department department = new Department("Computer Science", List.of(professor), List.of(course));

        // Unary: Professor can mentor multiple Students (Professors have many Students)
        Student student = new Student("Jane Doe", "02/02/2000", "S001", 3.8f);
        professor.addMentee(student); // Adding student to professor's mentee list

        // Course Enrollment: Many-to-Many relationship between Students and Courses
        CourseEnrollment enrollment = new CourseEnrollment(student, course, new Date());

        // Outputting the relationships
        System.out.println("Department: " + department.getDepartmentName());
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Course Address: " + course.getAddress().getStreet() + ", " + course.getAddress().getCity());
        System.out.println("Professor: " + professor.getName());
        System.out.println("Mentees of Professor " + professor.getName() + ": ");
        for (Student mentee : professor.getMentees()) {
            System.out.println("  - " + mentee.getName());
        }
        System.out.println("Student: " + student.getName());
        System.out.println("Enrolled in Course: " + enrollment.getCourse().getCourseName());
    }
}
