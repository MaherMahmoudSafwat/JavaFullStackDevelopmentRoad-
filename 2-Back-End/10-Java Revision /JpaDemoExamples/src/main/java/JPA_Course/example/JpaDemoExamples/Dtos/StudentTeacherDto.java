package JPA_Course.example.JpaDemoExamples.Dtos;

public record StudentTeacherDto
        (
                Integer TeacherId,
                String TeacherName
        )
{
        public StudentTeacherDto(Integer TeacherId, String TeacherName) {
                this.TeacherId = TeacherId;
                this.TeacherName = TeacherName;
        }
}
