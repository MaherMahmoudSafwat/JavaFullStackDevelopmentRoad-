import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        //You can also open the documentations through the command line Javadocs -d docs TheFileName.java that is found in src.
        System.out.println(AddTwoNumber(1,2));
        Person P = new Person();
    }
    //Single Line Comment
    /*
     Multi-Line Comment
    */

    /**
     * Add two numbers
     * @param N1 The First Number
     * @param N2 The Second Number
     * @return Return the sum
     */
    public static int AddTwoNumber(int N1, int N2)
    {
        return N1+N2;
    }

    /**
     * This is a class of type person.
     */
    static class Person
    {
        String Name;
        String Address;
        int ID;
    }
}