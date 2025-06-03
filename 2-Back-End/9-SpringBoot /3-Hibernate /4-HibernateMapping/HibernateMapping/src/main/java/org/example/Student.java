package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity // Marks this class as a JPA entity (will be mapped to a database table)
@Table(name = "StudentCollege") // Specifies the name of the database table
public class Student
{
    @Id // Marks this field as the primary key
    int RollNo;

    String SName; // No annotation - defaults to same column name

    @Column(name = "StudentAge") // Maps this field to a column named "StudentAge"
    int SAge;

    @OneToOne // INCORRECT: Defines one-to-one relationship (conflicts with others)
    @OneToMany(mappedBy = "student") // INCORRECT: Defines one-to-many relationship (conflicts)
    @ManyToMany // Defines many-to-many relationship with Laptop (correct but conflicts with above)
    List<Laptop> laptop;

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

    @Override
    public String toString() {
        return "Student{" +
                "RollNo=" + RollNo +
                ", SName='" + SName + '\'' +
                ", SAge=" + SAge +
                ", laptop=" + laptop +
                '}';
    }
}
