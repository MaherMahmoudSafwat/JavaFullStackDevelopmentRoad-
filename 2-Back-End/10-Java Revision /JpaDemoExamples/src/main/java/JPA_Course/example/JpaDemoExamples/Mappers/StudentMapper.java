package JPA_Course.example.JpaDemoExamples.Mappers;

import JPA_Course.example.JpaDemoExamples.Courses.CourseRepository;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Dtos.StudentCourseDto;
import JPA_Course.example.JpaDemoExamples.Dtos.StudentDto;
import JPA_Course.example.JpaDemoExamples.Dtos.StudentTeacherDto;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import JPA_Course.example.JpaDemoExamples.Teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentMapper
{
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;
    public StudentMapper(TeacherRepository teacherRepository,CourseRepository courseRepository)
    {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    public List<StudentDto> ToDto(List<Student> student)
    {
        // Simplified version using streams and lambda expressions
        return student.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());

// Helper method to map a single student
        private StudentDto mapToStudentDto(Student student) {
        // Get teacher data
        Teacher teacherData = teacherRepository
                .findById(student.getTeacher().getTeacherId())
                .orElse(null);

        // Map courses to DTOs
        List<StudentCourseDto> studentCourseDtos = student.getCourses().stream()
                .filter(course -> course != null && course.getCourseId() != null)
                .map(this::mapToCourseDto)
                .collect(Collectors.toList());

        // Create teacher DTO
        StudentTeacherDto teacherDto = new StudentTeacherDto(
                teacherData.getTeacherId(),
                teacherData.getTeacherName()
        );

        // Return student DTO
        return new StudentDto(
                student.getStudentId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                teacherDto,
                studentCourseDtos
        );
    }

// Helper method to map course to DTO
        private StudentCourseDto mapToCourseDto(Course course) {
        Courses courseData = courseRepository
                .findById(course.getCourseId())
                .orElse(null);

        return new StudentCourseDto(
                courseData.getCourseName(),
                courseData.getCourseCode()
        );
    }

// Alternative: Even more concise single-method approach
        return student.stream()
                .map(s -> {
                    Teacher teacher = teacherRepository.findById(s.getTeacher().getTeacherId()).orElse(null);

                    List<StudentCourseDto> courses = s.getCourses().stream()
                            .filter(c -> c != null && c.getCourseId() != null)
                            .map(c -> {
                                Courses courseData = courseRepository.findById(c.getCourseId()).orElse(null);
                                return new StudentCourseDto(courseData.getCourseName(), courseData.getCourseCode());
                            })
                            .collect(Collectors.toList());

                    return new StudentDto(
                            s.getStudentId(),
                            s.getFirstName(),
                            s.getLastName(),
                            s.getEmail(),
                            new StudentTeacherDto(teacher.getTeacherId(), teacher.getTeacherName()),
                            courses
                    );
                })
                .collect(Collectors.toList());
    }
}


