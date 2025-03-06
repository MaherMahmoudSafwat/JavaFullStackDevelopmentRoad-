package Methods;

public class PassByRefernceAndPassByValueInsideTheMethods {

    public static void main(String[] args) {

        // Example 1: All primitive data types are passed by value.
        int Value1 = 5; // Declare and initialize a primitive int variable Value1 with 5.
        System.out.println(Value1); // Print the original value of Value1 (5).

        // Pass the Value1 to the method, the value will be passed by copy, so changes won't affect the original Value1.
        AddNewNumber(Value1);

        // Print Value1 again. It will remain 5, as primitives are passed by value (only a copy was passed).
        System.out.println(Value1);

        // Example 2: String is a reference type, but it's passed by value (immutable behavior).
        String S1 = new String("Maher"); // Create a new String object with value "Maher".
        System.out.println(S1); // Print the original String (Maher).

        // Pass the String to the method. Strings are passed by value, but since they are immutable, the original String won't change.
        AddNewString(S1);

        // Print S1 again. It remains "Maher", as Strings cannot be modified directly due to immutability.
        System.out.println(S1);

        // Example 3: Arrays and objects of classes are passed by reference. Arrays can be modified inside methods.
        int[] Array1 = {1, 2, 3}; // Declare an integer array with three elements.
        System.out.println(Array1); // Print the memory reference of the array object (not the actual content).
        System.out.println(Array1[0]); // Print the first element of the array (1).

        // Pass the array to the method. Since arrays are passed by reference, the original array can be modified.
        AddNewElementsInTheArray(Array1);

        // Print the first element of the array again. It will be modified to 2 (from 1) because arrays are passed by reference.
        System.out.println(Array1[0]);

        // New Example 4: StringBuilder and Mutability
        StringBuilder sb1 = new StringBuilder("Hello");
        System.out.println("Original StringBuilder: " + sb1); // Print the original StringBuilder (Hello).

        // Pass the StringBuilder to the method. StringBuilder is mutable, so it can be modified inside the method.
        ModifyStringBuilder(sb1);

        // Print StringBuilder after modification.
        System.out.println("StringBuilder after method call: " + sb1); // Print modified StringBuilder (Hello World!).

        // New Example 5: String Concatenation (New String object created)
        String S2 = "Hello"; // Original String.
        String S3 = S2.concat(" World!"); // Concatenate " World!" to the original String.

        // Print original String and the concatenated result.
        System.out.println("Original String S2: " + S2); // Print the original string (Hello).
        System.out.println("New String S3: " + S3); // Print the new concatenated string (Hello World!).
        // S2 remains unchanged because Strings are immutable. S3 holds the new concatenated String.
    }

    // Method to demonstrate Pass by Value with primitive data type (int).
    public static void AddNewNumber(int Value1) {
        Value1++; // Increment the value of Value1 by 1.
        System.out.println("The Number of the Value is: " + Value1); // Print the incremented value (6).
    }

    // Method to demonstrate Pass by Value with String (Strings are passed by value but cannot be modified directly).
    public static void AddNewString(String Name) {
        Name = "MHR"; // Attempt to modify the String (this creates a new String object).
        System.out.println(Name); // Print the new String value ("MHR").
    }

    // Method to demonstrate Pass by Reference with Arrays (Arrays are passed by reference in Java).
    public static void AddNewElementsInTheArray(int[] Array1) {
        Array1[0]++; // Modify the first element of the array (increment it).
        System.out.println(Array1[0]); // Print the modified value of the first element (2).
    }

    // New method to demonstrate the mutability of StringBuilder.
    public static void ModifyStringBuilder(StringBuilder sb) {
        sb.append(" World!"); // Modify the StringBuilder by appending " World!" to it.
        System.out.println("Modified StringBuilder inside method: " + sb); // Print the modified StringBuilder ("Hello World!").
    }
} 
