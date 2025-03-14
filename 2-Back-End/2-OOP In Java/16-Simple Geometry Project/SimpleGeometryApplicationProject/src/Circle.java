import java.util.ArrayList;

public class Circle implements Shapes
{
    private double Radius=0;
    Circle(double Radius)
    {
        this.Radius=Radius;
    }
    public void CalculateShapesArea(ArrayList<String>Widgets)
    {
        this.Radius = Double.parseDouble(Widgets.getFirst());
    }
    public double CalculateAreaOfTheCircle()
    {
        return Math.PI*(Math.pow(Radius,2));
    }
    public double CalculateDiagnolOfTheCircle()
    {
        return 2*Radius;
    }
    public double CalculateTheCircumferenceOfCircle()
    {
        return 2*Math.PI*Radius;
    }
}
