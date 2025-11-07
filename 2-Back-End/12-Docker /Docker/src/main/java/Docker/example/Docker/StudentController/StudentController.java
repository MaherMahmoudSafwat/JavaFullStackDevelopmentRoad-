package Docker.example.Docker.StudentController;

import Docker.example.Docker.Models.Students;
import Docker.example.Docker.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    private boolean dataInitialized = false;

    @GetMapping("/")
    public List<Students> GetAllStudents() {
        // Initialize data only once
        if (!dataInitialized && studentRepository.count() == 0) {
            List<Students> testStudents = List.of(
                    new Students("Samir", "123"),
                    new Students("Jamel", "456"),
                    new Students("Ramez", "8910")
            );
            studentRepository.saveAll(testStudents);
            dataInitialized = true;
        }

        return studentRepository.findAll();
    }

    @GetMapping("/AddNewStudents")
    public String AddNewStudents() {
        Students Student = new Students("Ramy", "12345");
        studentRepository.save(Student);
        return "Student added successfully!";
    }
}