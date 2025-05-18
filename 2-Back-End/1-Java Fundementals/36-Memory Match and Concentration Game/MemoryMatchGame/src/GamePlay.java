import java.util.Scanner;

public class GamePlay extends EmojisInCards
{
    String [][] EmptyStringCards;
    int Number1;
    int Number2;
    int Flag = 0;
    Scanner Input = new Scanner(System.in);
    int Tries = 10;
    public GamePlay()
    {
        super();
        EmptyStringCards =
                new String[][]{
                        {"X", "X", "X", "X"},
                        {"X", "X", "X", "X"},
                        {"X", "X", "X", "X"},
                        {"X", "X", "X", "X"}
                };
    }
    public void ShowEmptyStringCards()
    {
        for(int i = 0; i < EmptyStringCards.length; i++)
        {
            System.out.println("------------------------------------------");
            for(int j =0; j < EmptyStringCards[i].length; j++)
            {
                System.out.print("| " + EmptyStringCards[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------");
    }
    public boolean CheckAreEmojisTheSame()
    {
        if(ArrCards[(Number1-1)/4][(Number1-1)%4] == ArrCards[(Number2-1)/4][(Number2-1)%4])
        {
            if(EmptyStringCards[(Number1-1)/4][(Number1-1)%4]!="X" || EmptyStringCards[(Number2-1)/4][(Number2-1)%4]!="X")
            {
                System.out.println("The Chosen blocks have already been selected before, Please try again.");
                return false;
            }
            EmptyStringCards[(Number1-1)/4][(Number1-1)%4] = ArrCards[(Number1-1)/4][(Number1-1)%4];
            EmptyStringCards[(Number2-1)/4][(Number2-1)%4] = ArrCards[(Number2-1)/4][(Number2-1)%4];
            return true;
        }
        else
        {
            if(EmptyStringCards[(Number1-1)/4][(Number1-1)%4]!= "X" || EmptyStringCards[(Number2-1)/4][(Number2-1)%4] != "X")
            {
                return false;
            }
            EmptyStringCards[(Number1-1)/4][(Number1-1)%4] = ArrCards[(Number1-1)/4][(Number1-1)%4];
            EmptyStringCards[(Number2-1)/4][(Number2-1)%4] = ArrCards[(Number2-1)/4][(Number2-1)%4];
            ShowEmptyStringCards();
            EmptyStringCards[(Number1-1)/4][(Number1-1)%4] = "X";
            EmptyStringCards[(Number2-1)/4][(Number2-1)%4] = "X";
            return false;
        }
    }
    public void StartGamePlay()
    {
        ShowAllCards();
        while (Flag < 16)
        {
            ShowEmptyStringCards();
            Input.reset();
            System.out.println("Please enter the Number 1 : ");
            Number1 = Input.nextInt();
            System.out.println("Please enter the number 2 : ");
            Number2 = Input.nextInt();
            if(Number1 > 16 || 16 < Number2)
            {
                System.out.println("Invalid block numbers, please try again.");
                continue;
            }
            else if(Number1==Number2)
            {
                System.out.println("You can't have the two numbers of the block, please try again.");
                continue;
            }
            if(CheckAreEmojisTheSame())
            {
                System.out.println("Good Job :-)");
                Flag +=2;
            }
            else
            {
                Tries--;
                if(Tries==0)
                {
                    break;
                }
                System.out.println("The Emojis are not the same, Try again :-(.");
            }
            if(Tries == 0)
                break;
        }
    }
    public void PrepareTheGame()
    {
        CreateTheBoxesOfCards();
        StartGamePlay();
    }
}
