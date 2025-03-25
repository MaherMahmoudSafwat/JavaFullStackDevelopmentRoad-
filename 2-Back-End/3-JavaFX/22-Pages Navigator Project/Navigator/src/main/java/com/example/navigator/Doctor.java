package com.example.navigator;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends Person
{
    private String FullName;
    private String Specialization;
    private static File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX" +
            "\\22-Pages Navigator Project\\Navigator\\DoctorsAccountsFiles.txt");
    Doctor(int ID,String FirstName,String LastName, String Email,String Password,String PhoneNumber,
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

    private static Doctor ConvertStringIntoDoctorObject(String Data)
    {
        String [] AllDoctorsData = Data.split(",");
        String [] FullName = AllDoctorsData[1].split(" ");
        return new Doctor(Integer.parseInt(AllDoctorsData[0]),FullName[0],FullName[1],
                AllDoctorsData[2],AllDoctorsData[3],AllDoctorsData[4], AllDoctorsData[5]);
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
    private static ArrayList<Doctor> GetAllPatientsData()
    {
        ArrayList<Doctor> D = new ArrayList<>();
        try {
            Scanner Scan = new Scanner(F);
            while(Scan.hasNextLine())
            {
                D.add(ConvertStringIntoDoctorObject(Scan.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return D;
    }
    public static Boolean IsDoctorUserAlreadyExists(String Email, String Password)
    {
        ArrayList<Doctor> D = GetAllPatientsData();
        for(int i = 0; i<D.size();i++)
        {
            if(D.get(i).Email.equals(Email) && D.get(i).Password.equals(Password))
                return true;
        }
        return false;
    }
    public static Boolean IsDoctorEmailAlreadyExists(String Email)
    {
        ArrayList<Doctor> D = GetAllPatientsData();
        for(int i = 0; i<D.size();i++)
        {
            if(D.get(i).Email.equals(Email))
                return true;
        }
        return false;
    }
}
