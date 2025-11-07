package Docker.example.Docker.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("studentId")
    private Integer studentId;

    @JsonProperty("studentName")
    private String studentName;

    @JsonProperty("studentPassword")
    private String studentPassword;

    // DEFAULT CONSTRUCTOR (REQUIRED BY HIBERNATE)
    public Students() {
        // Empty constructor required by JPA/Hibernate
    }

    // Constructor for creating new students
    public Students(String studentName, String studentPassword) {
        this.studentName = studentName;
        this.studentPassword = studentPassword;
    }

    // Getters and setters
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }
}