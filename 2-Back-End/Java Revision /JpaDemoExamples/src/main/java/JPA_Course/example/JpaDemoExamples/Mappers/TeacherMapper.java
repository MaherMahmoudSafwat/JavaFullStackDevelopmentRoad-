package JPA_Course.example.JpaDemoExamples.Mappers;

import JPA_Course.example.JpaDemoExamples.Courses.CourseRepository;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Dtos.*;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import JPA_Course.example.JpaDemoExamples.Student.StudentRepository;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherMapper {
    private StudentRepository studentRepository;
    private CourseRepository courseRepository;

    public TeacherMapper(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<TeacherDto> ToDto(List<Teacher> teachers) {
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for(Teacher teacher : teachers) {
            TeacherStudentDto teacherStudentDto = null;
            TeacherCoursesDto teacherCourseDto = null;

            // Check if student exists and is not null
            if(teacher.getStudent() != null && teacher.getStudent().getStudentId() != null) {
                Student student = studentRepository.findById(teacher.getStudent().getStudentId()).orElse(null);
                if(student != null) {
                    teacherStudentDto = new TeacherStudentDto(
                            student.getFirstName(),
                            student.getEmail()
                    );
                }
            }

            // Check if courses exists and is not null
            if(teacher.getCourses() != null && teacher.getCourses().getCourseId() != null) {
                Courses course = courseRepository.findById(teacher.getCourses().getCourseId()).orElse(null);
                if(course != null) {
                    teacherCourseDto = new TeacherCoursesDto(
                            course.getCourseName(),
                            course.getCourseCode()
                    );
                }
            }

            teacherDtos.add(new TeacherDto(
                    teacher.getTeacherId(),
                    teacher.getTeacherName(),
                    teacher.getTeacherEmail(),
                    teacher.getTeacherPassword(),
                    teacherStudentDto,
                    teacherCourseDto
            ));
        }

        return teacherDtos;
    }
}
