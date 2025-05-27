import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    enum UserStatus {
        BUYER,
        DEALER
    }

    ;

    private static void Buyer() {

    }

    private static void Dealer() throws IOException {
        Dealer.DealerMenu();
    }


     public static void UserMainMenu() throws IOException {
        System.out.println("\t\t\t\t\tWelcome to the Virtual Car DealerShip Simulator.");
        System.out.println("Please enter if you are a User or Dealer");
        System.out.println("1-Buyer");
        System.out.println("2-Dealer");
        Scanner Input = new Scanner(System.in);
        String User = Input.nextLine().toUpperCase();
        if (UserStatus.BUYER == UserStatus.valueOf(User)) {
            Buyer();
        } else if (UserStatus.DEALER == UserStatus.valueOf(User)) {
            Dealer();
        }
    }
}
