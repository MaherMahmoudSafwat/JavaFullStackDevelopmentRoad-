package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity // Marks this class as a JPA entity (will have its own table) - CONFLICTS with @Embeddable
public class Laptop
{
    @Id // Marks this field as the primary key
    int LID;

    String BrandName; // No annotation - defaults to same column name

    int Ram; // No annotation - defaults to same column name

    @ManyToMany(mappedBy = "laptop") // Defines inverse side of many-to-many relationship
    List<Student> student;

    // ... [ALL OTHER CODE REMAINS EXACTLY THE SAME] ...

    public int getLID() {
        return LID;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public void setLID(int LID) {
        this.LID = LID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public int getRam() {
        return Ram;
    }

    public void setRam(int ram) {
        Ram = ram;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "LID=" + LID +
                ", BrandName='" + BrandName + '\'' +
                ", Ram=" + Ram +
                '}';
    }
}
