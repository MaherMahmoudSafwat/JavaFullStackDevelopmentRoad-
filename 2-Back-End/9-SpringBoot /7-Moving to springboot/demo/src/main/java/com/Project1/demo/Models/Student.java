package com.Project1.demo.Models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Student
{
    public Student()
    {
        System.out.println("The student object has been created.");
    }
    @Value("21")
    int age;
    @Autowired
    Laptop Lap;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Laptop getLap() {
        return Lap;
    }

    public void setLap(Laptop lap) {
        Lap = lap;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", Lap=" + Lap +
                '}';
    }
    public void Code()
    {
        Lap.Code();
    }
}
