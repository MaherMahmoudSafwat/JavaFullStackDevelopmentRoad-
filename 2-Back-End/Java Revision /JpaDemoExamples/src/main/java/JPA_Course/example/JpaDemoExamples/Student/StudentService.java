package JPA_Course.example.JpaDemoExamples.Student;

import JPA_Course.example.JpaDemoExamples.Dtos.StudentDto;
import JPA_Course.example.JpaDemoExamples.Examples.Student;
import JPA_Course.example.JpaDemoExamples.Mappers.StudentMapper;
import JPA_Course.example.JpaDemoExamples.Teacher.Teacher;
import JPA_Course.example.JpaDemoExamples.Teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService
{
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private StudentMapper studentMapper;
    public StudentService(StudentRepository studentRepository,TeacherRepository teacherRepository,StudentMapper studentMapper)
    {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentMapper = studentMapper;
    }
    // In your StudentService save method
    public void SaveAllStudentsData(Student student) {
        if (student.getTeacher() != null && student.getTeacher().getTeacherId() != null) {
            // If teacher already has ID, merge it instead of trying to persist
            Teacher existingTeacher = teacherRepository.findById(student.getTeacher().getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            student.setTeacher(existingTeacher);
        }
        studentRepository.save(student);
    }
    public List<StudentDto> GetAllStudentsData()
    {
        return studentMapper.ToDto(studentRepository.findAll());
    }
}
