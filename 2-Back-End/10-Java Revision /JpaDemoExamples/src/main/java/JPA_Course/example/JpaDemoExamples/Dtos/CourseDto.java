package JPA_Course.example.JpaDemoExamples.Dtos;

import java.util.List;

public record CourseDto
        (
                Integer CourseId,
                String CourseName,
                String CourseCode,
                List<CourseStudentDto> CourseStudent,
                List<CourseTeacherDto> CourseTeacher
        )
{}
