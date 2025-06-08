package com.example.SpringJDBC.JDBC;

import com.example.SpringJDBC.JDBC.Model.Student;
import com.example.SpringJDBC.JDBC.StudentService.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class JdbcApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(JdbcApplication.class, args);

		// Create and save a new student
		Student s = context.getBean(Student.class);
		s.setRollNo(101);
		s.setName("Navin");
		s.setMarks(78);

		StudentService service = context.getBean(StudentService.class);
		service.addStudent(s);

		// Retrieve and display all students
		List<Student> students = service.getStudents();
		System.out.println("All Students:");
		students.forEach(System.out::println);
	}
}