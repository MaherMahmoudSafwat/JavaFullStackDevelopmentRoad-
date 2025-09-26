package JPA_Course.example.JpaDemoExamples.Teacher;

import JPA_Course.example.JpaDemoExamples.Dtos.TeacherDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Teachers")
public class TeacherController
{
    private TeacherService teacherService;
    public TeacherController(TeacherService teacherService)
    {
        this.teacherService = teacherService;
    }
    @GetMapping
    public List<TeacherDto>GetAllTeachersData()
    {
        return teacherService.GetAllTeachersData();
    }
    @PostMapping
    public void SaveAllTeachersData(@RequestBody Teacher teacher)
    {
        teacherService.SaveAllTeachersData(teacher);
    }
}
