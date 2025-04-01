package com.example.ElTawoonHospitalFullProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public final class Utility
{
    public static String LoginUsersEmail = "";
    public static int GenerateRandomID_ForPatients()
    {
        int ID=0;
        ArrayList<String>Data = new ArrayList<>();
        File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX" +
                "\\22-Pages Navigator Project\\Navigator\\PatientsAccountsFiles.txt");
        try {
            Scanner Scan = new Scanner(F);
            while(Scan.hasNextLine())
            {
                Data.add(Scan.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random RDM = new Random();
        ID = RDM.nextInt(500,10500);
        int Length = Data.size()-1;
        while(Length >= 0)
        {
            String S = Data.get(Length);
            if(ID==Integer.parseInt(S.substring(0,S.indexOf(","))))
            {
                ID = RDM.nextInt(500,10500);
                Length = Data.size()-1;
                continue;
            }
            Length--;
        }
        return ID;
    }
    public static int GenerateRandomID_ForDoctors()
    {
        int ID=0;
        ArrayList<String>Data = new ArrayList<>();
        File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX" +
                "\\22-Pages Navigator Project\\Navigator\\DoctorsAccountsFiles.txt");
        try {
            Scanner Scan = new Scanner(F);
            while(Scan.hasNextLine())
            {
                Data.add(Scan.nextLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Random RDM = new Random();
        ID = RDM.nextInt(0,500);
        int Length = Data.size()-1;
        while(Length >= 0)
        {
            String S = Data.get(Length);
            if(ID==Integer.parseInt(S.substring(0,S.indexOf(","))))
            {
                ID = RDM.nextInt(500,10500);
                Length = Data.size()-1;
                continue;
            }
            Length--;
        }
        return ID;
    }
}
