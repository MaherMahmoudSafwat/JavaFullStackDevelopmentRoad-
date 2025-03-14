import java.util.ArrayList;

public class Rectangle implements Shapes{
    private double Length=0;
    private double Width=0;
    private double D1=0;
    private double D2=0;
    private double D3=0;
    private double D4=0;
    Rectangle(double Length,double Width)
    {
        this.Length=Length;
        this.Width=Width;
    }
    public void CalculateShapesArea(ArrayList<String>Widgets)
    {
        double [] ArrayOfRectangleVerticesPoints = UtilityStringClass.GetVerticesPointsFromString(Widgets);
        D1 = ArrayOfRectangleVerticesPoints[0];
        D2 = ArrayOfRectangleVerticesPoints[1];
        D3 = ArrayOfRectangleVerticesPoints[2];
        D4 = ArrayOfRectangleVerticesPoints[3];
    }
    private boolean IsRectangleVerticesPointsAreTrue()
    {
        if(D1==D2 && D3==D4 && D1==D4 && D2==D3)
            return false;
        if(D1==D2 &&D3==D4)
        {
            if(D1>D3)
            {
                this.Length = D1;
                this.Width = D3;
            }
            else if(D3>D1)
            {
                this.Length = D3;
                this.Width = D1;
            }
            return true;
        }
        else if(D1==D3 &&D2==D4)
        {
            if(D1>D2)
            {
                this.Length = D1;
                this.Width = D2;
            }
            else if(D2>D1)
            {
                this.Length = D2;
                this.Width = D1;
            }
            return true;
        }
        else if(D1==D4 &&D2==D3)
        {
            if(D1>D2)
            {
                this.Length = D1;
                this.Width = D2;
            }
            else if(D2>D1)
            {
                this.Length = D2;
                this.Width = D1;
            }
            return true;
        }
        return false;
    }
    public void IsRectanglePointsVerticesAreCorrect() throws InvalidPointsVerticesException {
        if(!IsRectangleVerticesPointsAreTrue())
            throw new InvalidPointsVerticesException("Invalid Points, these is not a rectangle.");
    }
    public double CalculateAreaOfTheRectangle()
    {
        return Length*Width;
    }
    public double CalculateDiagnolOfTheRectangle()
    {
            return Math.sqrt(Math.pow(Length,2)+Math.pow(Width,2));
    }
    public double CalculatePerimeterOfTheRectangle()
    {
        return 2*(Length+Width);
    }
    }
