package com.JPA.JPA_Application;

import com.JPA.JPA_Application.Model.*;
import com.JPA.JPA_Application.StudentRepository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class JpaApplication {

	public static void main(String[] args) {
		// Initialize Spring application context
		ApplicationContext context = SpringApplication.run(JpaApplication.class, args);

		// JPA Entity Creation and Persistence Example:
		// Demonstrates basic entity creation and saving

		// Create a new Course entity (transient state - not yet persisted)
		Course course = new Course();

		// Create a new Student entity (transient state)
		Student student = new Student();
		student.setFirstName("ABCDEFG");
		student.setLastName("HIJKLMN");
		student.setEmailId("TPOGGGMYZ");

		// Establish relationship (Note: This will cause an error without proper cascading)
		//course.setStudents(List.of(student));

		// Get CourseRepository from Spring context
		CourseRepository courseRepo = context.getBean(CourseRepository.class);

		// Save the course (will fail due to unsaved student - see CommandLineRunner for proper approach)
		courseRepo.save(course);
	}

	@Bean
	@Transactional  // Ensures all operations run in a single transaction
	public CommandLineRunner demo(
			TeacherRepository teacherRepo,
			CourseRepository courseRepo,
			CourseMaterialRepository materialRepo) {
		return args -> {
			// =============================================
			// 1. TEACHER ENTITY CREATION AND PERSISTENCE
			// =============================================
			System.out.println("\n=== CREATING AND SAVING TEACHERS ===");

			// Create and save 10 Teacher entities
			for (int i = 1; i <= 10; i++) {
				Teacher teacher = new Teacher();  // New transient entity
				teacher.setFirstName("Teacher" + i);
				teacher.setLastName("LastName" + i);

				// Save teacher to database (transient → persistent)
				Teacher savedTeacher = teacherRepo.save(teacher);
				System.out.println("Saved teacher: " + savedTeacher.getFirstName());
			}

			// =============================================
			// 2. COURSE ENTITY CREATION WITH RELATIONSHIPS
			// =============================================
			System.out.println("\n=== CREATING COURSES WITH MATERIALS AND TEACHERS ===");

			// Create 20 Course entities with relationships
			for (int i = 1; i <= 20; i++) {
				// Create CourseMaterial (value object)
				CourseMaterial material = new CourseMaterial();
				material.setUrl("www.course" + i + ".com");

				// Create Course entity
				Course course = new Course();

				// Establish ManyToOne relationship with Teacher
				// Using modulo to distribute courses among teachers

				// Establish OneToOne bidirectional relationship with CourseMaterial
				course.setCourseMaterial(material);
				material.setCourse(course);  // Maintain both sides of relationship

				// Save course (cascades to CourseMaterial due to CascadeType.ALL)
				Course savedCourse = courseRepo.save(course);
			}

			// =============================================
			// 3. PAGINATION DEMONSTRATION
			// =============================================
			System.out.println("\n=== PAGINATION DEMO ===");

			// PageRequest implements Pageable interface
			int pageSize = 5;  // Number of items per page

			// Retrieve pages of courses (first 4 pages)
			for (int page = 0; page < 4; page++) {
				// Create PageRequest for current page
				PageRequest pageable = PageRequest.of(page, pageSize);

				// Get page of courses (Page extends Slice which contains content + metadata)
				Page<Course> coursePage = courseRepo.findAll(pageable);

				// Display page information
				System.out.println("\nPage " + (page + 1) + " of " + coursePage.getTotalPages());
				System.out.println("Total courses: " + coursePage.getTotalElements());

			}

			// =============================================
			// 4. SORTING DEMONSTRATION
			// =============================================
			System.out.println("\n=== SORTING DEMO ===");

			// Sort by single field (title ascending)
			System.out.println("\nCourses sorted by title (A-Z):");
			Sort titleSort = Sort.by("title");  // Create Sort specification

			// Sort by field with direction (credit descending)
			System.out.println("\nCourses sorted by credit (high to low):");
			Sort creditSort = Sort.by(Sort.Direction.DESC, "credit");


			// =============================================
			// 5. COMBINED PAGINATION AND SORTING
			// =============================================
			System.out.println("\n=== PAGINATION + SORTING DEMO ===");

			// Create PageRequest with sorting (first page, 5 items, title descending)
			PageRequest sortedPageable = PageRequest.of(
					0,                      // Page number (0-based)
					5,                      // Page size
					Sort.by(Sort.Direction.DESC, "title")  // Sort criteria
			);

			// Get sorted page
			Page<Course> sortedPage = courseRepo.findAll(sortedPageable);
		};
	}
}
