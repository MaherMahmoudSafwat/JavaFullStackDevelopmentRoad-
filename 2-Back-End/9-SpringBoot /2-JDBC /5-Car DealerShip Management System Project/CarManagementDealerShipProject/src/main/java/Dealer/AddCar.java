package Dealer;

import java.sql.SQLException;
import java.util.Scanner;

public class AddCar
{
    private static void InsertNewCar()
    {
        CarSettings Car = new CarSettings();
        Scanner Input = new Scanner(System.in);
        boolean IsCarDataAlreadyExists = true;
        do
        {
            System.out.println("Please enter the car brand");
            Car.setCarBrand(Input.nextLine());
            System.out.println("Please enter the car model");
            Car.setCarModel(Input.nextLine());
            IsCarDataAlreadyExists = Car.checkIfCarDataAlreadyExists();
            if(IsCarDataAlreadyExists)
            {
                System.out.println("Car data brand and model already exists, please try again.");
            }
        }while(IsCarDataAlreadyExists);
        boolean IsValid = false;
        while(!IsValid)
        {
            System.out.println("Please enter the car Quantity");
            try {
                Car.setCarQuantity(Integer.parseInt(Input.nextLine()));
                IsValid = true;
            } catch (InvalidQuantityException e) {
                System.out.println(e.getMessage());
            }
            catch(NumberFormatException e)
            {
                System.out.println("The input is invalid, please try again");
            }
        }
        IsValid = false;
        while(!IsValid)
        {
            System.out.println("Please enter the car price");
            try {
                Car.setCarPrice(Integer.parseInt(Input.nextLine()));
                IsValid = true;
            } catch (InvalidPriceException e) {
                System.out.println(e.getMessage());
            }
            catch(NumberFormatException e)
            {
                System.out.println("The input is invalid, please try again");
            }
        }
        try {
            Car.AddNewCar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Car has been added successfully.");
    }
    public static void AddNewCar()
    {
        System.out.println("\n\n\nAdd new car");
        String InsertNewCar = "Y";
        Scanner Input = new Scanner(System.in);
        do
        {
            System.out.println("Please enter the following details :- ");
            InsertNewCar();
            System.out.println("Would you like to insert another new car ? Y/N ? ");
            InsertNewCar = Input.nextLine();
        }while(InsertNewCar.equalsIgnoreCase("Y"));
        DealerMainMenu.TheDealerMenu();
    }
}
