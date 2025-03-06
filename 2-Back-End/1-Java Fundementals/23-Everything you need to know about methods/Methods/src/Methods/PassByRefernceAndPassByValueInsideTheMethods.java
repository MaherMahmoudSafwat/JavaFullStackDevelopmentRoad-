package Methods;

public class PassByRefernceAndPassByValueInsideTheMethods {

    public static void main(String[] args) {

        // All Primitive DataTypes are pass by value.
        int Value1 = 5;
        System.out.println(Value1);
        AddNewNumber(Value1);
        System.out.println(Value1); // Value1 remains 5 (pass by value).

        // Although String is a Complex Datatype but it acts as a primitive datatype in case and in terms of pass by Value.
        String S1 = new String("Maher");
        System.out.println(S1); // Prints "Maher".
        AddNewString(S1);
        System.out.println(S1); // S1 remains "Maher" (Strings are immutable, effectively passed by value).

        // Array and Objects of the classes are Pass By Reference.
        int[] Array1 = { 1, 2, 3 };
        System.out.println(Array1); // Prints the memory address of Array1.
        System.out.println(Array1[0]); // Prints 1.
        AddNewElementsInTheArray(Array1);
        System.out.println(Array1[0]); // Prints 2 (Array1[0] is modified due to pass by reference).

        // Example 1: Changing S1 directly in main.
        String S2 = new String("Original");
        System.out.println("S2 before direct change: " + S2); //Prints original
        S2 = "Changed"; // S2 now points to a new String object.
        System.out.println("S2 after direct change: " + S2); //Prints Changed

        // Example 2: String immutability in action.
        String S3 = "Hello";
        String S4 = S3.toUpperCase(); // Creates a new String "HELLO".
        System.out.println("S3: " + S3); // Prints "Hello" (unchanged).
        System.out.println("S4: " + S4); // Prints "HELLO" (new String).

        // Example 3: String concatenation creates new String objects.
        String S5 = "Part 1";
        S5 = S5 + " Part 2"; // Creates a new String "Part 1 Part 2".
        System.out.println("S5: " + S5); // Prints "Part 1 Part 2".

        // Example 4: illustrating that String is effectively passed by value.
        String S6 = "Test";
        changeString(S6);
        System.out.println("S6 after method call: " + S6); // Prints "Test".

        // Example 5: showing that the content of a string is not changed in the original string.
        String S7 = "test 7";
        stringChange(S7);
        System.out.println("S7 after method call: " + S7); // prints test 7

    }

    public static void AddNewNumber(int Value1) {
        Value1++;
        System.out.println("The Number of the Value is : " + Value1);
    }

    public static void AddNewString(String Name) {
        Name = "MHR"; // Name now points to a new String object.
        System.out.println(Name);
    }

    public static void AddNewElementsInTheArray(int[] Array1) {
        Array1[0]++; // Modifies the original array.
        System.out.println(Array1[0]);
    }

    public static void changeString(String str) {
        str = "New Value"; // Local str now points to a new String.
    }

    public static void stringChange(String stringToChange){
        stringToChange = stringToChange.toUpperCase();
    }
}