import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        Rectangle R1=new Rectangle(0,0);
        ArrayList<String>Widgets = new ArrayList<String>(4);
        Widgets.add("1,1");
        Widgets.add("5,1");
        Widgets.add("6,4");
        Widgets.add("1,4");
        R1.CalculateShapesArea(Widgets);
        try {
            R1.IsRectanglePointsVerticesAreCorrect();
            System.out.println(R1.CalculateAreaOfTheRectangle());
        } catch (InvalidPointsVerticesException e) {
            System.out.println(e.getMessage());
        }
    }
}
