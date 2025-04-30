import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*
         * METHOD REFERENCE TYPE 1: Reference to a Static Method
         * Syntax: ClassName::staticMethodName
         * Used when calling static methods of a class
         */
        Mathematical<Integer> Mntl;

        // Using lambda expression to call Math.max()
        Mntl = (N1, N2) -> Math.max(N1, N2);
        System.out.println(Mntl.Comparsionable(5, 7)); // Output: 7

        // Using static method reference (equivalent to above lambda)
        Mntl = Math::max;  // Same as (a, b) -> Math.max(a, b)
        System.out.println(Mntl.Comparsionable(10, 15)); // Output: 15

        /*
         * METHOD REFERENCE TYPE 2: Reference to an Instance Method of a Particular Type
         * Syntax: ClassName::instanceMethodName
         * Used when calling instance methods on objects of a specific class
         */
        Mathematical<String> MntlString;

        // Using lambda to call concat() on String objects
        MntlString = (N1, N2) -> N1.concat(N2);
        System.out.println(MntlString.Comparsionable("Ahmed", "Mohammed")); // Output: AhmedMohammed

        // Using instance method reference (equivalent to above lambda)
        MntlString = String::concat;  // Same as (a, b) -> a.concat(b)
        System.out.println(MntlString.Comparsionable("Ahmed", "Ahmed")); // Output: AhmedAhmed

        // Example showing when NOT to use method reference (extra logic)
        MntlString = (N1, N2) -> String.valueOf(N1.length() + N2.length());
        System.out.println(MntlString.Comparsionable("Ahmed", "Mostafa")); // Output: 11

        /*
         * METHOD REFERENCE TYPE 3: Reference to an Instance Method of an Object
         * Syntax: object::instanceMethodName
         * Used when calling methods on a specific object instance
         */
        S S1;

        // Using lambda to call length() on String
        S1 = (N1) -> N1.length();
        System.out.println(S1.SSS("Ahmed")); // Output: 5

        // Using instance method reference (equivalent to above lambda)
        S1 = String::length;  // Same as (s) -> s.length()
        System.out.println(S1.SSS("Ahmed Mostafa El-Sayed")); // Output: 20

        /*
         * METHOD REFERENCE TYPE 4: Reference to a Constructor
         * Syntax: ClassName::new
         * Used when creating new objects
         */
        ArrayLists ALS;

        // Using lambda to create new ArrayList
        ALS = () -> new ArrayList<String>();
        System.out.println(ALS.CreatedList()); // Output: []

        // Using constructor reference (equivalent to above lambda)
        ALS = ArrayList::new;  // Same as () -> new ArrayList<>()
        System.out.println(ALS.CreatedList()); // Output: []
    }
}


