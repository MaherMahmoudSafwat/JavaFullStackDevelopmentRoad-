package com.example.ElTawoonHospitalFullProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Patient extends Person
{
    private enum Gender {MALE,FEMALE};
    private String FullName;
    private int Age;
    Gender gender;
    private String Diseases;
    private static File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX\\22-Pages Navigator Project" +
            "\\Navigator\\PatientsAccountsFiles.txt");
    Patient(int ID,String FirstName,String LastName, String Email,String Password,String PhoneNumber,
            int Age,String GDR,String Diseases)
    {
        super(FirstName,LastName,ID,Email,Password,PhoneNumber);
        FullName = FirstName+ " " + LastName;
        this.Age = Age;
        if(GDR.equals("Male"))
        {
            this.gender = Gender.MALE;
        }
        else
        {
            this.gender = Gender.FEMALE;
        }
        this.Diseases = Diseases;
    }

    private String ConvertPatientDataAsIntoString(Patient Data)
    {
        String PatientAccountData="";
        PatientAccountData+=String.valueOf(Data.getID())+",";
        PatientAccountData+=Data.getFullName()+",";
        PatientAccountData+=Data.getEmail()+",";
        PatientAccountData+=Data.getPassword()+",";
        PatientAccountData+=Data.getPhoneNumber()+",";
        PatientAccountData+=String.valueOf(Data.getAge())+",";
        PatientAccountData+=Data.getGender()+",";
        PatientAccountData+=Data.getDiseases();
        return PatientAccountData;
    }

    private static Patient ConvertStringIntoPatientObject(String Data)
    {
        String [] AllPatientsData = Data.split(",");
        String [] FullName = AllPatientsData[1].split(" ");
        return new Patient(Integer.parseInt(AllPatientsData[0]),FullName[0],FullName[1],
                AllPatientsData[2],AllPatientsData[3],AllPatientsData[4],Integer.parseInt(AllPatientsData[5]),
                AllPatientsData[6],AllPatientsData[7]);
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
    public static ArrayList<Patient>GetAllPatientsData()
    {
        ArrayList<Patient> P = new ArrayList<>();
        try {
            Scanner Scan = new Scanner(F);
            while(Scan.hasNextLine())
            {
                P.add(ConvertStringIntoPatientObject(Scan.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return P;
    }

    public static Boolean IsPatientUserAlreadyExists(String Email, String Password)
    {
        ArrayList<Patient> P = GetAllPatientsData();
        for(int i = 0; i<P.size();i++)
        {
            if(P.get(i).Email.equals(Email) && P.get(i).Password.equals(Password))
                return true;
        }
        return false;
    }

    public static Boolean IsPatientEmailAlreadyExists(String Email)
    {
        ArrayList<Patient> P = GetAllPatientsData();
        for(int i = 0; i<P.size();i++)
        {
            if(P.get(i).Email.equals(Email))
                return true;
        }
        return false;
    }

    public static ArrayList<String>GetAllPatientsStringFromFile()
    {
        ArrayList<String> PatientsDataList = new ArrayList<String>();
        try {
            Scanner Scan = new Scanner(F);
            while(Scan.hasNextLine())
            {
                PatientsDataList.add(Scan.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return PatientsDataList;
    }

}
