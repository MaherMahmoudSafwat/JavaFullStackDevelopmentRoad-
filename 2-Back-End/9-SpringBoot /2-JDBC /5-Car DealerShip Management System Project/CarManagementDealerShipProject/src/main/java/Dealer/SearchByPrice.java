package Dealer;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchByPrice
{
    private static int SearchBy = 0;
    private static void Search()
    {
        CarSettings Car = new CarSettings();
        ArrayList<Car> ListOfCarsSearchedByBrand = Car.SearchByPrice(SearchBy);
        if (ListOfCarsSearchedByBrand.isEmpty())
        {
            System.out.println("This car doesn't exists.");
            return;
        }
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
    public static void SearchForBrand()
    {
        System.out.println("\nPlease enter the car price you want to search for :- ");
        Scanner Input = new Scanner(System.in);
        SearchBy = Input.nextInt();
        Search();
    }
}
