import java.util.ArrayList;

public class Triangle implements Shapes{
    private double Side1 = 0;
    private double Side2 = 0;
    private double Side3 = 0;
    private int  AngleOfTriangle=0;
    public Triangle(double Side1, double Side2, double Side3)
    {
        this.Side1=Side1;
        this.Side2=Side2;
        this.Side3=Side3;
    }
    public Triangle(double Side1, double Side2, int AngleOfTriangle)
    {
        this.Side1=Side1;
        this.Side2=Side2;
        this.AngleOfTriangle=AngleOfTriangle;
    }
    @Override
    public void CalculateShapesArea(ArrayList<String> Widgets) {
     this.Side1=Double.parseDouble(Widgets.get(0));
     this.Side2=Double.parseDouble(Widgets.get(1));
     this.Side3=Double.parseDouble(Widgets.get(2));
    }
    @Override
    public void CalculateTrignometricAngles(ArrayList<String>Widgets)
    {
        this.Side1=Double.parseDouble(Widgets.get(0));
        this.Side2=Double.parseDouble(Widgets.get(1));
        this.Side3=Double.parseDouble(Widgets.get(2));
        this.AngleOfTriangle=Integer.parseInt(Widgets.get(3));
    }
    public double CalculateAreaOfTriangleUsingSidesOnly()
    {
        double Perimeter = (Side1+Side2+Side3)/2;
        return Math.sqrt(Perimeter*(Perimeter-Side1)*(Perimeter-Side2)*(Perimeter-Side3));
    }
    public double CalculateAreaOfTriangleUsingAnglesOnly()
    {
        return 0.5 * Side1 * Side2 * Math.sin(Math.toRadians(AngleOfTriangle));
    }
    public double CalculatePerimeterOfTriangle()
    {
        return Side1+Side2+Side3;
    }
}
