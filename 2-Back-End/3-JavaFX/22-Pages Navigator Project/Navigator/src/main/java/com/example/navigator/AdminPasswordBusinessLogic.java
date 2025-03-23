package com.example.navigator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AdminPasswordBusinessLogic
{
    public static boolean IsAdminPasswordCorrect(String Password)
    {
        File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX\\22" +
                "-Pages Navigator Project\\Navigator\\AdminPasswordFile.txt");
        try {
            Scanner Scan = new Scanner(F);
            while(Scan.hasNextLine())
            {
                if(Scan.nextLine().equals(Password))
                    return true;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public static void ChangeAdminPassword(String Password)
    {
        File F = new File("C:\\Users\\Maher\\Desktop\\JavaFullStackDevelopmentRoad-\\2-Back-End\\3-JavaFX\\22" +
                "-Pages Navigator Project\\Navigator\\AdminPasswordFile.txt");
        try {
            PrintWriter PWT =new PrintWriter(F);
            PWT.print(Password);
            PWT.flush();
            PWT.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
