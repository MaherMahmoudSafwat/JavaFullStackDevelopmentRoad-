package JPA_Course.example.JpaDemoExamples.Courses;

import JPA_Course.example.JpaDemoExamples.Dtos.CourseDto;
import JPA_Course.example.JpaDemoExamples.Mappers.CourseMapper;
import JPA_Course.example.JpaDemoExamples.Student.Student;
import JPA_Course.example.JpaDemoExamples.Student.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService
{
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private CourseMapper courseMapper;
    public CourseService(CourseRepository courseRepository,StudentRepository studentRepository,CourseMapper courseMapper)
    {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.courseMapper = courseMapper;
    }
    public void SaveAllCourses(Courses courses)
    {
        List<Student>StudentsData = new ArrayList<>();
        if(courses.getStudents()!=null)
        {
            int Length = courses.getStudents().size();
            int i = 0;
            while(i < Length)
            {
                Student StudentData = studentRepository.findById(courses.getStudents().get(i).getStudentId()).orElse(null);
                StudentsData.add(StudentData);
                i++;
            }
            courses.setStudents(StudentsData);
        }
        courseRepository.save(courses);
    }
    public List<CourseDto> GetAllCourses()
    {
        return courseMapper.ToDto(courseRepository.findAll());
    }
}
