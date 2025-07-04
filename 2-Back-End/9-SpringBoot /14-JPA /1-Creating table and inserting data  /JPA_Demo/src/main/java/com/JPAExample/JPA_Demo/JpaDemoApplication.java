package com.JPAExample.JPA_Demo;

import com.JPAExample.JPA_Demo.Model.Student;
import com.JPAExample.JPA_Demo.Repository.StudentRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class JpaDemoApplication {

	public static void main(String[] args)
	{
		ApplicationContext Context = SpringApplication.run(JpaDemoApplication.class, args);
		Student s1= Context.getBean(Student.class);
		Student s2=Context.getBean(Student.class);
		Student s3=Context.getBean(Student.class);

		StudentRepo repo=Context.getBean(StudentRepo.class);

		s1.setRollNo(101);
		s1.setName("Navin");
		s1.setMarks(75);


		s2.setRollNo(102);
		s2.setName("Kiran");
		s2.setMarks(95);


		s3.setRollNo(103);
		s3.setName("Harsh");
		s3.setMarks(70);

//		repo.save(s1);
//		repo.save(s2);
//		repo.save(s3);

		Optional<Student> S = repo.findById(105);
		System.out.println(repo.findAll());
		System.out.println(S.orElse(new Student()));
		System.out.println(repo.findByName("Navin"));
		System.out.println(repo.findByMarksGreaterThan(75));
		repo.save(s2);
		repo.delete(s2);
	}

}
