package JPA_Course.example.JpaDemoExamples.Dtos;

import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;

import java.util.List;

public record StudentDto
        (
                Integer StudentId,
                String StudentFirstName,
                String StudentLastName,
                String StudentEmail,
                StudentTeacherDto teacher,
                List<StudentCourseDto> StudentCourse
        )
{}

