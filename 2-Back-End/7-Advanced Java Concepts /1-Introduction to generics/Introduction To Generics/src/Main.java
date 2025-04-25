//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        Pair<Integer>P1 = new Pair<Integer>(1,3);
        System.out.println(P1.getPair1() + " " + P1.getPair2());
        Pair<String>P2 = new Pair<String>("Mohammed","Ahmed");
        System.out.println(P2.getPair1() + " " + P2.getPair2());
        Pair<Float>P3 = new Pair<Float>(5.1f,9.7f);
        System.out.println(P3.getPair1() + " " + P3.getPair2());
    }
}