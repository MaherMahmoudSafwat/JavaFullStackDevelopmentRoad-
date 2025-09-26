package JPA_Course.example.JpaDemoExamples.Dtos;

public record CourseTeacherDto
        (
                String TeacherName,
                String TeacherEmail
        )
{
    public CourseTeacherDto(String TeacherName,String TeacherEmail)
    {
        this.TeacherName = TeacherName;
        this.TeacherEmail = TeacherEmail;
    }
}
