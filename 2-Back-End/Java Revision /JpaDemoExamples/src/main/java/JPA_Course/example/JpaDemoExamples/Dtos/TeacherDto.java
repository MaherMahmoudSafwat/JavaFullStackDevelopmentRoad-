package JPA_Course.example.JpaDemoExamples.Dtos;

public record TeacherDto
        (
                Integer TeacherId,
                String TeacherName,
                String TeacherEmail,
                String TeacherPassword,
                TeacherStudentDto Student,
                TeacherCoursesDto Courses
        )
{
}
