import java.util.ArrayList;

public final class UtilityStringClass
{
    public static double [] GetVerticesPointsFromString(ArrayList<String>Points)
    {
        double [] ArrayOfRectangle = new double[4];
        String [] Spilter1 = Points.get(0).split(",");
        String [] Spilter2 = Points.get(1).split(",");
        String [] Spilter3 = Points.get(2).split(",");
        String [] Spilter4 = Points.get(3).split(",");

        ArrayOfRectangle[0]=Math.sqrt(Math.pow(Double.parseDouble(Spilter2[0])-Double.parseDouble(Spilter1[0]),2) +
                Math.pow(Double.parseDouble(Spilter2[1])-Double.parseDouble(Spilter1[1]),2));
        ArrayOfRectangle[1]=Math.sqrt(Math.pow(Double.parseDouble(Spilter4[0])-Double.parseDouble(Spilter1[0]),2) +
                Math.pow(Double.parseDouble(Spilter4[1])-Double.parseDouble(Spilter1[1]),2));
        ArrayOfRectangle[2]=Math.sqrt(Math.pow(Double.parseDouble(Spilter3[0])-Double.parseDouble(Spilter2[0]),2) +
                Math.pow(Double.parseDouble(Spilter3[1])-Double.parseDouble(Spilter2[1]),2));
        ArrayOfRectangle[3]=Math.sqrt(Math.pow(Double.parseDouble(Spilter4[0])-Double.parseDouble(Spilter3[0]),2) +
                Math.pow(Double.parseDouble(Spilter4[1])-Double.parseDouble(Spilter3[1]),2));
        return ArrayOfRectangle;
    }
}

