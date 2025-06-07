package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Student class representing a student entity with device dependency.
 * Demonstrates various Spring dependency injection techniques.
 */
@Component // Marks this class as a Spring component for auto-detection
public class Student {

    // Field injection with default value
    @Value("21") // Injects default value 21 for StudentAge
            int StudentAge;

    // Fields that can be set via setter injection
    String StudentName;
    int StudentRollNo;

    // Field injection with qualifier (note the lowercase bean name)
    @Autowired
    @Qualifier("computer") // Looks for a bean named "computer" (case-sensitive)
            Device Com;

    /**
     * Constructor - prints message when Student is created
     */
    public Student() {
        System.out.println("The object has been created");
    }

    /**
     * Setter injection with different qualifier than field injection
     * This demonstrates you can have multiple injection points with different qualifiers
     */
    @Autowired
    @Qualifier("Com2") // Uses the explicitly named Computer bean
    public void setCom(Device com) {
        Com = com;
    }

    // Standard getters and setters
    public Device getCom() {
        return Com;
    }

    public int getStudentAge() {
        return StudentAge;
    }

    public void setStudentAge(int studentAge) {
        StudentAge = studentAge;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public int getStudentRollNo() {
        return StudentRollNo;
    }

    public void setStudentRollNo(int studentRollNo) {
        StudentRollNo = studentRollNo;
    }

    /**
     * Delegates coding behavior to the injected Device
     */
    public void ComputerCode() {
        Com.Code();
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentAge=" + StudentAge +
                ", StudentName='" + StudentName + '\'' +
                ", StudentRollNo=" + StudentRollNo +
                '}';
    }
}



