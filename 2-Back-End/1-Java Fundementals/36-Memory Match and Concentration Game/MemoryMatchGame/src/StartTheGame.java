import java.util.Scanner;

public class StartTheGame
{
    static Scanner Input = new Scanner(System.in);
    public static boolean CheckUserPoints(GamePlay P)
    {
        for(int i =0; i<P.EmptyStringCards.length;i++)
        {
            for(int j = 0 ; j<P.EmptyStringCards[i].length; j++)
            {
                if(P.EmptyStringCards[i][j]=="X")
                    return false;
            }
        }
        return true;
    }
    public static void PlayTheGame()
    {
        System.out.println("Welcome to the memory match game.");
        String UserInput = "";
        do
        {
            GamePlay P = new GamePlay();
            P.PrepareTheGame();
            if(CheckUserPoints(P))
            {
                System.out.println("Congratulations, you have won :-).");
            }
            else
            {
                System.out.println("You have lost :-(.");
            }
            System.out.println("Would you like to play again ? Y/N ?:-");
            UserInput = Input.nextLine().toUpperCase();
        }while(UserInput.equals("Y") || UserInput.equals("YES"));
    }
}
