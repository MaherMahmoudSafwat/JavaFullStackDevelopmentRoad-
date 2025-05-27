import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Dealer
{
    enum DealerOptions
    {
        ADD,
        UPDATE,
        FIND,
        DELETE,
        SHOW_ALL
    }
    private static void AddCar() throws IOException {
        AddNewCar.AddCars();
    }
    private static void UpdateCar()
    {

    }
    private static void FindCar()
    {

    }
    private static void DeleteCar()
    {

    }
    private static void Show_AllCar()
    {

    }

    public static void DealerMenu() throws IOException {
        System.out.println("\n\n\nDealer Main Menu");
        System.out.println("Please Choose one of the following :- ");
        System.out.println("1-Add a new car ");
        System.out.println("2-Update a car");
        System.out.println("3-Find a car");
        System.out.println("4-Delete a car");
        System.out.println("5-Show all the cars");
        Scanner Input = new Scanner(System.in);
        String Str = Input.nextLine().toUpperCase();
        int flag = 0;
        do {
            flag = 0;
            switch (DealerOptions.valueOf(Str)) {
                case DealerOptions.ADD -> AddCar();
                case DealerOptions.UPDATE -> UpdateCar();
                case DealerOptions.FIND -> FindCar();
                case DealerOptions.DELETE -> DeleteCar();
                case DealerOptions.SHOW_ALL -> Show_AllCar();
                default ->
                {
                    flag = 1;
                    System.out.println("Invalid option please try again");
                }
            }
        }while(flag==1);
    }
}
