package com.example.SpringJDBC.JDBC.StudentRepository;

import com.example.SpringJDBC.JDBC.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for handling database operations related to Student entities.
 * Uses Spring JDBC for database access.
 */
@Repository // Marks this class as a Spring Data Repository component
public class StudentRepository {

    // JDBC Template for executing SQL queries
    private JdbcTemplate jdbc;

    /**
     * Setter injection for JdbcTemplate
     * @param jdbc The JdbcTemplate instance to be injected by Spring
     */
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Saves a Student entity to the database
     * @param s The Student object to be saved
     */
    public void save(Student s) {
        // SQL query with parameter placeholders
        String sql = "INSERT INTO Student(rollno, name, marks) VALUES (?, ?, ?)";

        // Execute the update and get number of affected rows
        int rows = jdbc.update(sql, s.getRollNo(), s.getName(), s.getMarks());

        System.out.println(rows + " row(s) affected");
    }

    /**
     * Retrieves all Student records from the database
     * @return List of all Student entities
     */
    public List<Student> findAll() {
        // SQL query to select all students
        String SQL = "SELECT * FROM Student";

        // Custom RowMapper implementation using lambda expression
        // Maps each row in the ResultSet to a Student object
        RowMapper<Student> mapper = (resultSet, rowNumber) -> {
            Student student = new Student();

            // Set properties from ResultSet columns
            student.setRollNo(resultSet.getInt("rollno"));
            student.setName(resultSet.getString("name"));
            student.setMarks(resultSet.getInt("marks"));

            return student;
        };

        // Execute query and return mapped results
        return jdbc.query(SQL, mapper);
    }
}
