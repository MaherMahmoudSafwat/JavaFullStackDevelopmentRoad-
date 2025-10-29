package JPA_Course.example.JpaDemoExamples.Courses;

import JPA_Course.example.JpaDemoExamples.Dtos.CourseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Courses")
public class CourseController
{
    private CourseService courseService;
    public CourseController(CourseService courseService)
    {
        this.courseService = courseService;
    }
    @GetMapping
    public List<CourseDto> GetAllCoursesData()
    {
        return courseService.GetAllCourses();
    }
    @PostMapping
    public void SAveALlCoursesData(@RequestBody Courses courses)
    {
        courseService.SaveAllCourses(courses);
    }
}
