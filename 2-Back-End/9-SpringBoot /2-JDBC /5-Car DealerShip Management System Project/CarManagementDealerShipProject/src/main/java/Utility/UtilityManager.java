package Utility;

import java.sql.*;

public final class UtilityManager
{
    private static String URL = "jdbc:postgresql://localhost:5432/CarManagementSystemProject";
    private static String User = "postgres";
    private static String Password = "User";
    private static Connection Con;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            Con = DriverManager.getConnection(URL,User,Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static boolean IsPasswordCorrect(String Password) throws SQLException {
        Statement St;
        ResultSet Rs;
        St = Con.createStatement();
        String Query = "SELECT \"Password\" FROM public.\"PasswordAdmin\"";
        Rs = St.executeQuery(Query);
        Rs.next();
        if(Rs.getString(1).equals(Password))
        {
            return true;
        }
        return false;
    }
    public static Boolean IsNumberDigitCorrect (String Number)
    {
        String NumberString = String.valueOf(Number).toLowerCase();
        for(int i =0;i<NumberString.length();i++)
        {
            if(NumberString.charAt(i) < 48 || NumberString.charAt(i) > 57)
            {
                return false;
            }
        }
        return true;
    }
    public static boolean CheckDealerPassword(String Password)
    {
        try {
            return IsPasswordCorrect(Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
