package Dealer;

import java.util.ArrayList;
import java.util.Scanner;

public class ShowAllCars
{
    public static void GetAllAvaliableCars()
    {
        System.out.println("The following is all the available cars.");
        CarSettings Car = new CarSettings();
        ArrayList<Car> ListOfCarsSearchedByBrand = Car.GetAllCars();
        for(Car car : ListOfCarsSearchedByBrand)
        {
            System.out.println("--------------------------------------------------");
            System.out.println("1-Car ID :- " + car.getCarID());
            System.out.println("2-Car Brand :- " + car.getCarBrand());
            System.out.println("3-Car Model :- " + car.getCarModel());
            System.out.println("4-Car Quantity :- " + car.getCarQuantity());
            System.out.println("5-Car Price :- " + car.getCarPrice());
        }
        System.out.println("--------------------------------------------------");
    }
}
