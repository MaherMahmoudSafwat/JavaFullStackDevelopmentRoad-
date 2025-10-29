package JPA_Course.example.JpaDemoExamples.Dtos;

public record CourseStudentDto
        (
                String FirstName,
                String Email
        )
{
    public CourseStudentDto(String FirstName,String Email)
    {
        this.FirstName = FirstName;
        this.Email = Email;
    }
}
