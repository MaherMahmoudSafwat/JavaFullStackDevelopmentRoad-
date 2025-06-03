package org.example;

import jakarta.persistence.*;

/**
 * This class represents a Student entity that will be mapped to a database table.
 * The annotations configure how this Java object maps to database structures.
 */
@Entity(name = "StudentEntity")  // Name used in JPQL/HQL queries
@Table(name = "Student_Table")   // Actual database table name
public class Student {

    /**
     * Marks this field as the primary key in the database table.
     * By default, the column name would be "RollNo" but can be customized with @Column.
     */
    @Id
    int RollNo;

    /**
     * Maps this field to a database column named "StudentName".
     * Without @Column, it would default to "SName".
     */
    @Column(name = "StudentName")
    String SName;

    /**
     * This field will NOT be persisted in the database.
     * @Transient fields are ignored by JPA/Hibernate.
     */
    @Transient
    int SAge;

    // Standard getters and setters
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

    @Override
    public String toString() {
        return "Student{" +
                "RollNo=" + RollNo +
                ", SName='" + SName + '\'' +
                ", SAge=" + SAge +
                '}';
    }
}


