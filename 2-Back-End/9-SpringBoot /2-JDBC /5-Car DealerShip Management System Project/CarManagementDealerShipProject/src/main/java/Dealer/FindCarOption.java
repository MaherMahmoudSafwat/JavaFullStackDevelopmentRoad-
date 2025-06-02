package Dealer;

import java.util.Scanner;

public class FindCarOption
{
    private static void SearchByBrand()
    {
        SearchByBrand.SearchForBrand();
    }
    private  static void SearchByModel()
    {
        SearchByModel.SearchForModel();
    }
    private static void SearchByQuantity()
    {
        SearchByQuantity.SearchForBrand();
    }
    private static void SearchByPrice()
    {
        SearchByPrice.SearchForBrand();
    }
    public static void FindCar()
    {
        System.out.println("Find car option.");
        String Choice = "";
        Scanner Input = new Scanner(System.in);
        do
        {
            System.out.println("Choose what kind of search do you want ? ");
            System.out.println("1-Search by brand.");
            System.out.println("2-Search by model.");
            System.out.println("3-Search by quantity.");
            System.out.println("4-Search by price.");
            Choice = Input.nextLine();
            switch(Choice)
            {
                case "1":
                    SearchByBrand();
                    break;
                case "2":
                    SearchByModel();
                    break;
                case "3":
                    SearchByQuantity();
                    break;
                case "4":
                    SearchByPrice();
                    break;
                default:
                    Choice = "X";
                    System.out.println("Invalid input from the user, please try again");
            }
            if(Choice.equals("X"))
            {
                continue;
            }
            System.out.println("Would you like to find any other element ? Y/N ? ");
            Choice = Input.nextLine().toUpperCase();
        }while(Choice.equals("Y") || Choice.equals("X"));
        DealerMainMenu.TheDealerMenu();
    }
}
