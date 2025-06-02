package org.MainMenu;

import Dealer.LoginPage;
import Utility.UtilityManager;

import java.util.Scanner;

public class MainMenu
{
    private static void DealerChoice()
    {
        LoginPage.DealerLoginPage();
    }
    public static void MainMenuWelcome()
    {
        Scanner Input = new Scanner(System.in);
        System.out.println("\t\t\t\t\tWelcome to the car management system.");
        DealerChoice();
    }
}
