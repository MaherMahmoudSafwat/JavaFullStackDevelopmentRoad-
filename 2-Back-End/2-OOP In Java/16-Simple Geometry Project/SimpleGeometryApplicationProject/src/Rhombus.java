import java.util.ArrayList;

public class Rhombus implements Shapes
{
    private double D1=0;
    private double D2=0;
    private double A=0;

    public Rhombus(double D1,double D2,double A)
    {
        this.D1=D1;
        this.D2=D2;
        this.A=A;
    }
    public void CalculateShapesArea(ArrayList<String>Widgets)
    {
        D1=Double.parseDouble(Widgets.get(0));
        D2=Double.parseDouble(Widgets.get(1));
        A=Double.parseDouble(Widgets.get(2));
    }
    public double CalculateAreaOfRhombus()
    {
        return (D1*D2)/2;
    }
    public double CalculateDiagnolOfRhombus()
    {
        return Math.sqrt(Math.pow(4*A,2)+D1);
    }
    public double CalculatePerimeterOfRhombus()
    {
        return A*4;
    }
}