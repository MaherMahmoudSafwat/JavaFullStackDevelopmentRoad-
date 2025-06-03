package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// @Entity marks this class as a Hibernate entity (maps to a database table)
// By default, Hibernate will look for a table named "Student" (same as class name)
@Entity
public class Student {

    // @Id marks this field as the primary key in the database table
    @Id
    private int RollNo;  // Maps to "RollNo" column in the table

    // These will automatically map to columns "SName" and "SAge"
    private String SName;
    private int SAge;

    // REQUIRED: A no-argument constructor (Hibernate uses this internally)
    public Student() {
    }

    // Standard getters and setters (Hibernate uses these to access fields)

    public int getRollNo() {
        return RollNo;
    }

    public void setRollNo(int rollNo) {
        RollNo = rollNo;
    }

    public String getSName() {
        return SName;
    }

    public void setSName(String SName) {
        this.SName = SName;
    }

    public int getSAge() {
        return SAge;
    }

    public void setSAge(int SAge) {
        this.SAge = SAge;
    }

    // toString() helps with debugging (not required by Hibernate)
    @Override
    public String toString() {
        return "Student{" +
                "RollNo=" + RollNo +
                ", SName='" + SName + '\'' +
                ", SAge=" + SAge +
                '}';
    }
}


