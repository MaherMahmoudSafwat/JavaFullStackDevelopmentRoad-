import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner Input = new Scanner(System.in);
    static EnumSet<UserShapeChoice> UserShapeChoices = EnumSet.allOf(UserShapeChoice.class);
    static UserShapeChoice Shapes;
    public static void main(String[] args)
    {
        System.out.println("Welcome to the Geometry Application.");
        while(true)
        {
            System.out.println("1-Rectangle.");
            System.out.println("2-Rhombus.");
            System.out.println("3-Triangle.");
            System.out.println("4-Circle");
            String Choice=Input.nextLine();
            UsersShapesChoices(Choice);
        }
    }
    public static void UsersShapesChoices(String Choice)
    {
        Shapes = UserShapeChoice.valueOf(Choice.toUpperCase());
        switch(Shapes)
        {
            case RECTANGLE:
              RectangleUserChoice();
              break;
            case RHOMBUS:
                RhombusUserChoice();
                break;
            case TRIANGLE:
                TriangleUserChoice();
                break;
            case CIRCLE:
                CircleUserChoice();
                break;
        }
    }
    public static void RectangleUserChoice()
    {
        ArrayList<String>PointsOfRectangle=new ArrayList<>(4);
        Rectangle R1= new Rectangle(0,0);
        for(int i = 0 ; i < 4; i++)
        {
            System.out.println("Please enter the " + i + " points");
            PointsOfRectangle.add(Input.nextLine());
        }
        R1.CalculateShapesArea(PointsOfRectangle);
        try {
            R1.IsRectanglePointsVerticesAreCorrect();
            System.out.println("Area of Rectangle is " + R1.CalculateAreaOfTheRectangle());
            System.out.println("Diagnol of Rectangle is " + R1.CalculateDiagnolOfTheRectangle());
            System.out.println("Perimeter of Rectangle is " + R1.CalculatePerimeterOfTheRectangle());
            System.out.print("\n\n\n");
        } catch (InvalidPointsVerticesException e) {
            throw new RuntimeException(e);
        }
    }
    public static void RhombusUserChoice()
    {
        ArrayList<String>PointsOfRhombus=new ArrayList<>(4);
        Rhombus R1 = new Rhombus(0,0,0);
        for(int i = 0 ; i < 3; i++)
        {
            System.out.println("Please enter the " + i + " Sides");
            PointsOfRhombus.add(Input.nextLine());
        }
        R1.CalculateShapesArea(PointsOfRhombus);
        System.out.println("Area of Rhombus is " + R1.CalculateAreaOfRhombus());
        System.out.println("Diagnol of Rhombus is " + R1.CalculateDiagnolOfRhombus());
        System.out.println("Perimeter of Rhombus is " + R1.CalculatePerimeterOfRhombus());
        System.out.print("\n\n\n");
    }
    public static void TriangleUserChoice()
    {
        ArrayList<String>PointOfTriangle=new ArrayList<>(3);
        Triangle R1 = new Triangle(0,0,0);
        System.out.println("Would you like to print Area based on Sides or Angles ?");
        System.out.println("1-Sides.");
        System.out.println("2-Angles.");
        String Choice = Input.nextLine();
        if(Choice.equals("1"))
        {
            for (int i = 0; i < 3; i++)
            {
                System.out.println("Please enter the " + i + " Sides");
                PointOfTriangle.add(Input.nextLine());
            }
            R1.CalculateShapesArea(PointOfTriangle);
            System.out.println("Area of Triangle is " + R1.CalculateAreaOfTriangleUsingSidesOnly());
            System.out.println("Perimeter of Triangle is " + R1.CalculatePerimeterOfTriangle());
            System.out.print("\n\n\n");
        }
        else if(Choice.equals("2"))
        {
            for (int i = 0; i < 4; i++)
            {
                System.out.println("Please enter the " + i + " Sides");
                PointOfTriangle.add(Input.nextLine());
            }
            R1.CalculateTrignometricAngles(PointOfTriangle);
            System.out.println("Area of Triangle is " + R1.CalculateAreaOfTriangleUsingAnglesOnly());
            System.out.println("Diagnol of Triangle is " + R1.CalculatePerimeterOfTriangle());
            System.out.print("\n\n\n");
        }
    }
    public static void CircleUserChoice()
    {
        ArrayList<String>PointOfCircle=new ArrayList<>(1);
        Circle R1 = new Circle(0);
        System.out.println("Enter the Radius of The Circle.");
        PointOfCircle.add(Input.nextLine());
        R1.CalculateShapesArea(PointOfCircle);
        System.out.println("Area of Circle is " + R1.CalculateAreaOfTheCircle());
        System.out.println("Diagnol of Circle is " + R1.CalculateDiagnolOfTheCircle());
        System.out.println("Circumference of Circle is " + R1.CalculateTheCircumferenceOfCircle());
    }
}
