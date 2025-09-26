package JPA_Course.example.JpaDemoExamples.Teacher;

import JPA_Course.example.JpaDemoExamples.Courses.CourseRepository;
import JPA_Course.example.JpaDemoExamples.Courses.Courses;
import JPA_Course.example.JpaDemoExamples.Dtos.TeacherDto;
import JPA_Course.example.JpaDemoExamples.Mappers.TeacherMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService
{
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;
    private TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, CourseRepository courseRepository,TeacherMapper teacherMapper)
    {
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.teacherMapper = teacherMapper;
    }
    public void SaveAllTeachersData(Teacher teacher)
    {
        if(teacher.getCourses()!=null && teacher.getCourses().getCourseId()!=null)
        {
            Courses course = courseRepository.findById(teacher.getCourses().getCourseId()).orElse(null);
            teacher.setCourses(course);
        }
        teacherRepository.save(teacher);
    }
    public List<TeacherDto>GetAllTeachersData()
    {
        return teacherMapper.ToDto(teacherRepository.findAll());
    }
}
