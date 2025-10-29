package JPA_Course.example.JpaDemoExamples.Dtos;

public record TeacherStudentDto
        (
                String FirstName,
                String Email
        )
{
    public TeacherStudentDto(String FirstName,String Email)
    {
        this.FirstName = FirstName;
        this.Email = Email;
    }
}
