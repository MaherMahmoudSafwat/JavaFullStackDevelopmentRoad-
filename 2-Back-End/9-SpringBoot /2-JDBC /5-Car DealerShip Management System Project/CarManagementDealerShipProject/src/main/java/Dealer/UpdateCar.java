package Dealer;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCar
{
    private static CarSettings Car = new CarSettings();
    private static void UpdateUserCar()
    {
        Scanner Input = new Scanner(System.in);
        String Update = "Y";
        System.out.println("Would you like to update car quantity ? Y/N ?");
        Update = Input.nextLine().toUpperCase().trim();
        boolean IsValid = false;
        if(Update.equals("Y"))
        {
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
        }
        System.out.println("Would you like to update car price ? Y/N ?");
        Update = Input.nextLine().toUpperCase().trim();
        IsValid = false;
        if(Update.equals("Y"))
        {
            System.out.println("Please enter the car price");
            while(!IsValid)
            {
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
        }
       try {
            Car.UpdateExistingCar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Car has been Updated successfully.");
    }
    public static void UpdateExistingCar()
    {
        System.out.println("\n\n\nUpdate car");
        String Update = "Y";
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
            UpdateUserCar();
            System.out.println("Would you like to Update another car ? Y/N ? ");
            Update = Input.nextLine();
        }while(Update.equalsIgnoreCase("Y") || IsCarAlreadyExists);
        DealerMainMenu.TheDealerMenu();
    }
}
