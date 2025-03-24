package com.example.navigator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Doctor extends Person
{
    private String FullName;
    private String Specialization;
    Doctor(String FirstName,String LastName,int ID,String Email,String Password,String PhoneNumber,
           String Specialization)
    {
        super(FirstName,LastName,ID,Email,Password,PhoneNumber);
        FullName = FirstName+ " " + LastName;
        this.Specialization = Specialization;
    }

    private String ConvertDoctorDataAsIntoString(Doctor Data)
    {
        String DoctorAccountData="";
        DoctorAccountData+=String.valueOf(Data.getID())+",";
        DoctorAccountData+=Data.getFullName()+",";
        DoctorAccountData+=Data.getEmail()+",";
        DoctorAccountData+=Data.getPassword()+",";
        DoctorAccountData+=Data.getPhoneNumber()+",";
        DoctorAccountData+=Data.GetSpecialization();
        return DoctorAccountData;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getFullName() {
        return FullName;
    }

    public void SetSpecialization(String Specialization)
    {
        this.Specialization = Specialization;
    }
    public String GetSpecialization()
    {
        return Specialization;
    }
    public void CreateNewAccount()
    {
        File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX" +
                "\\22-Pages Navigator Project\\Navigator\\DoctorsAccountsFiles.txt");
        String S = ConvertDoctorDataAsIntoString(this);
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
