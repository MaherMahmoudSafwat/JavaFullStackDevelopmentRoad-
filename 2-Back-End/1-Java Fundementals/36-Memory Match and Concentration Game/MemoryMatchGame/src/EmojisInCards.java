import java.util.HashMap;
import java.util.Random;

public class EmojisInCards
{
    protected String [][] ArrCards = new String [4][4];
    protected HashMap<String,Integer> PairOfEmojis = new HashMap<String,Integer>();
    protected Random R1 = new Random();
    public EmojisInCards()
    {
        PairOfEmojis.put("\uD83D\uDC31",2);
        PairOfEmojis.put("\uD83C\uDF55",2);
        PairOfEmojis.put("\uD83D\uDE80",2);
        PairOfEmojis.put("\uD83C\uDF38",2);
        PairOfEmojis.put("\uD83C\uDF4E",2);
        PairOfEmojis.put("\uD83D\uDE97",2);
        PairOfEmojis.put("⚽",2);
        PairOfEmojis.put("\uD83D\uDE0E",2);
    }
    public void CreateTheBoxesOfCards()
    {
        for(int i = 0; i<ArrCards.length; i++)
        {
            for(int j = 0; j<ArrCards[i].length;j++)
            {
                while(true)
                {
                    int Y = R1.nextInt(1, 9);
                    if (Y == 1) {
                        if (PairOfEmojis.get("\uD83D\uDC31")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "\uD83D\uDC31";
                            PairOfEmojis.put("\uD83D\uDC31",PairOfEmojis.get("\uD83D\uDC31")-1);
                            break;
                        }
                    }
                    else if (Y == 2)
                    {
                        if (PairOfEmojis.get("\uD83C\uDF55")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "\uD83C\uDF55";
                            PairOfEmojis.put("\uD83C\uDF55",PairOfEmojis.get("\uD83C\uDF55")-1);
                            break;
                        }
                    }
                    else if (Y == 3)
                    {
                        if (PairOfEmojis.get("\uD83D\uDE80")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "\uD83D\uDE80";
                            PairOfEmojis.put("\uD83D\uDE80",PairOfEmojis.get("\uD83D\uDE80")-1);
                            break;
                        }
                    }
                    else if (Y == 4)
                    {
                        if (PairOfEmojis.get("\uD83C\uDF38")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "\uD83C\uDF38";
                            PairOfEmojis.put("\uD83C\uDF38",PairOfEmojis.get("\uD83C\uDF38")-1);
                            break;
                        }
                    }
                    else if (Y == 5)
                    {
                        if (PairOfEmojis.get("\uD83C\uDF4E")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "\uD83C\uDF4E";
                            PairOfEmojis.put("\uD83C\uDF4E",PairOfEmojis.get("\uD83C\uDF4E")-1);
                            break;
                        }
                    }
                    else if (Y == 6)
                    {
                        if (PairOfEmojis.get("\uD83D\uDE97")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "\uD83D\uDE97";
                            PairOfEmojis.put("\uD83D\uDE97",PairOfEmojis.get("\uD83D\uDE97")-1);
                            break;
                        }
                    }
                    else if (Y == 7)
                    {
                        if (PairOfEmojis.get("⚽")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "⚽";
                            PairOfEmojis.put("⚽",PairOfEmojis.get("⚽")-1);
                            break;
                        }
                    }
                    else if (Y == 8)
                    {
                        if (PairOfEmojis.get("\uD83D\uDE0E")==0)
                        {
                            continue;
                        }
                        else
                        {
                            ArrCards[i][j] = "\uD83D\uDE0E";
                            PairOfEmojis.put("\uD83D\uDE0E",PairOfEmojis.get("\uD83D\uDE0E")-1);
                            break;
                        }
                    }
                }
            }
        }
    }
    public void ShowAllCards()
    {
        for(int i =0; i<ArrCards.length; i++)
        {
            for(int j = 0; j<ArrCards[i].length; j++)
            {
                System.out.print(ArrCards[i][j] + " ");
            }
            System.out.println();
        }
    }
}
