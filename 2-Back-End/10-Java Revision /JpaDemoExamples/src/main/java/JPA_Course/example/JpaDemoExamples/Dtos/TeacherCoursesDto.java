package JPA_Course.example.JpaDemoExamples.Dtos;

public record TeacherCoursesDto
        (
                String CourseName,
                String CourseCode
        )
{
    public TeacherCoursesDto(String CourseName,String CourseCode)
    {
        this.CourseName = CourseName;
        this.CourseCode = CourseCode;
    }
}
