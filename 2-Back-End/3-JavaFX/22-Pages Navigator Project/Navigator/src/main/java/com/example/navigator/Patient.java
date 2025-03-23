package com.example.navigator;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class Patient extends Person
{
    private enum Gender {MALE,FEMALE};
    private String FullName;
    private int Age;
    Gender gender = Gender.MALE;
    private String Diseases;
    Patient(String FirstName,String LastName,int ID,String Email,String Password,String PhoneNumber,
            String FullName,int Age,Gender Gender,String Diseases)
    {
        super(FirstName,LastName,ID,Email,Password,PhoneNumber);
        FullName = FirstName+ " " + LastName;
        this.Age = Age;
        this.gender = Gender;
        this.Diseases = Diseases;
    }

    public String getFullName() {
        return FirstName + " " + LastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }
    
}
