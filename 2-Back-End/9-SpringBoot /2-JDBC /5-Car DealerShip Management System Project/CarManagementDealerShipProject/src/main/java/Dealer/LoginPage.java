package Dealer;

import Utility.UtilityManager;

import java.util.Scanner;

public class LoginPage
{
    private static void DealerPage()
    {
        DealerMainMenu.TheDealerMenu();
    }
    public static void DealerLoginPage()
    {
        System.out.println("\n\n\nWelcome to Dealer Login Page");
        boolean IsPasswordCorrect = true;
        do
        {
            System.out.println("Please enter your password");
            Scanner Input = new Scanner(System.in);
            String Password = Password = Input.nextLine();
            IsPasswordCorrect = !UtilityManager.CheckDealerPassword(Password);
            if(IsPasswordCorrect)
            {
                System.out.println("Invalid password, please try again.");
            }
        }while(IsPasswordCorrect);
        DealerPage();
    }
}
