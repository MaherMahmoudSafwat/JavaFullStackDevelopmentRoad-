package Dealer;

import Utility.UtilityManager;
import org.MainMenu.MainMenu;

import java.util.Scanner;

public class DealerMainMenu
{
    private enum DealerOptions
    {
        ADD(1),
        UPDATE(2),
        DELETE(3),
        FIND(4),
        SHOW_ALL(5),
        INVALID(6);

        final int Number;
        DealerOptions(int Number)
        {
            this.Number = Number;
        }
        public static DealerOptions GetFromNumber(int Choice)
        {
            for(DealerOptions DealerChoices : DealerOptions.values())
            {
                if(DealerChoices.Number == Choice)
                {
                    return DealerChoices;
                }
            }
            return INVALID;
        };
    }
    private static void AddCar()
    {
        AddCar.AddNewCar();
    }
    private static void UpdateCar()
    {
        UpdateCar.UpdateExistingCar();
    }
    private static void DeleteCar()
    {
        DeleteCar.DeleteExistingCar();
    }
    private static void FindCar()
    {
        FindCarOption.FindCar();
    }
    private static void ShowAllCars()
    {
        ShowAllCars.GetAllAvaliableCars();
    }
    public static void TheDealerMenu() {
        Scanner Input = new Scanner(System.in);
        System.out.println("\n\n\nDealer menu ");
        System.out.println("Please choose one of thew following :- ");
        System.out.println("1-Add a new car");
        System.out.println("2-Update a car");
        System.out.println("3-Delete a car");
        System.out.println("4-Find a car");
        System.out.println("5-Show all cars");
        DealerOptions Choices;
        do {
            String Choice = Input.nextLine();
            if (!UtilityManager.IsNumberDigitCorrect(Choice)) {
                System.out.println("The choice is incorrect, please try again.");
                Choices = DealerOptions.INVALID;
                continue;
            }
            Choices = DealerOptions.GetFromNumber(Integer.parseInt(Choice));
            switch (Choices) {
                case ADD -> AddCar();
                case UPDATE -> UpdateCar();
                case DELETE -> DeleteCar();
                case FIND -> FindCar();
                case SHOW_ALL -> ShowAllCars();
                default -> System.out.println("Invalid Choice");
            }
        } while (DealerOptions.INVALID == Choices);
        MainMenu.MainMenuWelcome();
    }
}
