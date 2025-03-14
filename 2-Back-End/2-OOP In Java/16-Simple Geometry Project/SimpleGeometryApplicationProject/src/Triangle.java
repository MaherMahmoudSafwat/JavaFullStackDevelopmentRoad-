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

    }
    public
    public double CalculateAreaOfRhombus() {
        return (D1 * D2) / 2;
    }

    public double CalculateDiagnolOfRhombus() {
        return Math.sqrt(Math.pow(4 * A, 2) + D1);
    }

    public double CalculatePerimeterOfRhombus() {
        return A * 4;
    }
}
