package com.JPAExample.JPA_Demo.Repository;

import com.JPAExample.JPA_Demo.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>
{
    @Query("Select s FROM Student s Where s.name=?1")
    List<Student> findByName(String Name);
    List<Student> findByMarksGreaterThan(int Marks);
}
