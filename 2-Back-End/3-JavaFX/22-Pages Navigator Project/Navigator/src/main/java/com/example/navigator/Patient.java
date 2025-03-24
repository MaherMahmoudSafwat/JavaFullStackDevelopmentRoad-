package com.example.navigator;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

    private String ConvertPatientDataAsIntoString(Patient Data)
    {
        String PatientAccountData="";
        PatientAccountData+=String.valueOf(Data.getID());
        PatientAccountData+=Data.getFullName()+",";
        PatientAccountData+=Data.getEmail()+",";
        PatientAccountData+=Data.getPassword()+",";
        PatientAccountData+=Data.getPhoneNumber()+",";
        PatientAccountData+=String.valueOf(Data.getAge())+",";
        PatientAccountData+=Data.getGender()+",";
        PatientAccountData+=Data.getDiseases();
        return PatientAccountData;
    }
    public String getFullName() {
        return FirstName + " " + LastName;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getAge() {
        return Age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }


    public void setDiseases(String diseases) {
        Diseases = diseases;
    }

    public String getDiseases() {
        return Diseases;
    }

    public void CreateNewAccount()
    {
        File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX\\22-Pages Navigator Project" +
                "\\Navigator\\PatientsAccountsFiles.txt");
        String S = ConvertPatientDataAsIntoString(this);
        try {
            FileWriter FW = new FileWriter(F,true);
            PrintWriter PWT = new PrintWriter(FW);
            PWT.println(S);
            PWT.flush();
            PWT.close();
            FW.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
