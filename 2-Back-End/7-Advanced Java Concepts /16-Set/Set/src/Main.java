import java.util.*;

public class Main {
    public static void main(String[] args)
    {
        Collection Numbers = new HashSet();
        Numbers.add(7);
        Numbers.add(3);
        Numbers.add(2);
        Numbers.add(1);

        System.out.println(Numbers);

        Numbers.add(7);

        System.out.println(Numbers);

        Set<Integer>Numbers2 = new HashSet<Integer>();

        Numbers2.add(5);
        Numbers2.add(3);
        Numbers2.add(10);
        Numbers2.add(9);
        Numbers2.add(8);
        Numbers2.add(3);

        System.out.println(Numbers2);
        System.out.println(Numbers2);

        Iterator<Integer> Nums = Numbers2.iterator();

        while(Nums.hasNext())
        {
            System.out.println(Nums.next());
        }

        TreeSet<Integer>Numbers3 = new TreeSet<Integer>();

        Numbers3.add(5);
        Numbers3.add(1);
        Numbers3.add(4);
        Numbers3.add(1);

        System.out.println(Numbers3);
    }
}