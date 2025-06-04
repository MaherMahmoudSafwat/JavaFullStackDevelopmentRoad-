package org.example;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents a Student entity that maps to a database table.
 * Demonstrates JPA annotations and relationship mappings.
 */
@Entity  // Indicates this class is a JPA entity that will be persisted to a database table
@Table(name = "StudentCollege")  // Specifies the table name in the database (default would be "Student")
@Cacheable  // Marks this entity as cacheable in Hibernate's second-level cache
public class Student {

    // PRIMARY KEY FIELD
    @Id  // Identifies this field as the primary key of the entity
            int RollNo;  // Will map to "RollNo" column (same name as field)

    // BASIC FIELDS (no annotation needed for default mapping)
    String SName;  // Maps to "SName" column (default same name as field)

    // COLUMN WITH EXPLICIT NAME MAPPING
    @Column(name = "StudentAge")  // Maps this field to "StudentAge" column instead of default "SAge"
            int SAge;

    // RELATIONSHIP MAPPING
    @ManyToMany(fetch = FetchType.EAGER)  // Defines a many-to-many relationship with Laptop
            /*
             * FetchType.EAGER means:
             * - Laptops will be loaded immediately with Student
             * - Can cause performance issues with large collections
             * - Consider FetchType.LAZY for better performance
             */
            List<Laptop> laptop;  // Represents the collection of associated laptops

    // GETTERS AND SETTERS
    public List<Laptop> getLaptop() {
        return laptop;
    }

    public void setLaptop(List<Laptop> laptop) {
        this.laptop = laptop;
    }

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
}

// TO STRING METHOD (includes relationship to avoid lazy loading
