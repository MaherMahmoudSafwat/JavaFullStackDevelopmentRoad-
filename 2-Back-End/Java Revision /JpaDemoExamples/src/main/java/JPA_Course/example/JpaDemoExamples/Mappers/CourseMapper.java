package JPA_Course.example.JpaDemoExamples.Mappers;

import JPA_Course.example.JpaDemoExamples.Courses.CourseRepository;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Dtos.*;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import JPA_Course.example.JpaDemoExamples.Student.StudentRepository;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import JPA_Course.example.JpaDemoExamples.Teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseMapper {

    // Remove repository dependencies - you don't need them!
    public List<CourseDto> ToDto(List<Courses> courses) {
        List<CourseDto> courseDtos = new ArrayList<>();

        for(Courses course : courses) {
            // Use already loaded teachers directly
            List<CourseTeacherDto> teachersData = new ArrayList<>();
            for(Teacher teacher : course.getTeachers()) {
                if(teacher != null) {
                    teachersData.add(new CourseTeacherDto(
                            teacher.getTeacherName(),
                            teacher.getTeacherEmail()
                    ));
                }
            }

            // Use already loaded students directly
            List<CourseStudentDto> studentsData = new ArrayList<>();
            for(Student student : course.getStudents()) {
                if(student != null) {
                    studentsData.add(new CourseStudentDto(
                            student.getFirstName(),
                            student.getEmail()
                    ));
                }
            }

            courseDtos.add(new CourseDto(
                    course.getCourseId(),
                    course.getCourseName(),
                    course.getCourseCode(),
                    studentsData,
                    teachersData
            ));
        }

        return courseDtos;
    }
}