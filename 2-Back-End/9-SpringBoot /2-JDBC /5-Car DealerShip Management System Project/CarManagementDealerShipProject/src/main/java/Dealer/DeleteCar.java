package Dealer;

import java.sql.SQLException;
import java.util.Scanner;

public class DeleteCar
{
    private static CarSettings Car = new CarSettings();
    private static void DeleteCarOption()
    {
        try {
            Car.DeleteExistingCar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Car has been deleted successfully");
    }
    public static void DeleteExistingCar()
    {
        System.out.println("\n\n\nDelete car option");
        String Delete = "Y";
        Scanner Input = new Scanner(System.in);
        boolean IsCarAlreadyExists = true;
        do
        {
            System.out.println("Please enter the following details :- ");
            System.out.println("Please enter the brand of the car you want to update :- ");
            Car.setCarBrand(Input.nextLine());
            System.out.println("Please enter the model of the car you want to update :- ");
            Car.setCarModel(Input.nextLine());
            IsCarAlreadyExists = !Car.checkIfCarDataAlreadyExists();
            if(IsCarAlreadyExists)
            {
                System.out.println("This car doesn't exist, please try again.");
                continue;
            }
            DeleteCarOption();
            System.out.println("Would you like to delete another car ? Y/N ? ");
            Delete = Input.nextLine();
        }while(Delete.equalsIgnoreCase("Y") || IsCarAlreadyExists);
        DealerMainMenu.TheDealerMenu();
    }
}
