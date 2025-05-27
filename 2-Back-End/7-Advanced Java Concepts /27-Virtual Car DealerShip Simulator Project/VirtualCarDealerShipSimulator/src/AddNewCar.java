import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AddNewCar
{
    private static Car car;

    static {
        try {
            car = new Car();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Car>CarsData;

    static {
        try {
            CarsData = car.GetCarData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Scanner Input = new Scanner(System.in);

    public AddNewCar() throws FileNotFoundException {
    }

    private static void AddCarID() throws IOException
    {
        Random RDN = new Random();
        int ID = RDN.nextInt(10000);
        while(car.IsCarID_AlreadyExists(ID))
        {
            ID = RDN.nextInt(10000);
        }
        car.setID(ID);
    }
    private static void AddCarBrand() throws FileNotFoundException
    {
        System.out.println("Please enter the car brand :- ");
        car.setBrand(Input.nextLine());
    }
    private static void AddCarModel() throws FileNotFoundException
    {
        System.out.println("Please enter the car model :- ");
        car.setModel(Input.nextLine());
    }
    private static void AddCarColor() throws FileNotFoundException
    {
        System.out.println("Please enter the car color :- ");
        car.setColor(Input.nextLine());
    }
    private static void AddCarManufacturingYear() throws FileNotFoundException
    {
        System.out.println("Please enter the car Manufacturing year :- ");
        car.setManufacturingYear(Input.nextInt());
    }
    private static void AddCarPrice() throws FileNotFoundException
    {
        System.out.println("Please enter the car price :- ");
        car.setManufacturingYear(Input.nextInt());
    }
    public static void AddCars() throws IOException {
        System.out.println("\n\n\nAdd new car screen");
        Input.reset();
        AddCarID();
        AddCarBrand();
        AddCarModel();
        AddCarColor();
        AddCarManufacturingYear();
        AddCarPrice();
        car.AddCar();
        System.out.println("Car has been added successfully");
        Dealer.DealerMenu();
    }
}
