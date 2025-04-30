//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        // Example 1: Using Calculator interface with lambda expressions

        // Declare a Calculator interface reference
        Calculator C1;

        // Lambda 1: Simple addition
        C1 = (N1, N2) -> N1 + N2;
        System.out.println(C1.Sum(3, 7)); // Output: 10 (3 + 7)

        // Lambda 2: Average calculation
        C1 = (N1, N2) -> (N1 + N2) / 2;
        System.out.println(C1.Sum(3, 7)); // Output: 5 (average of 3 and 7)

        // Lambda 3: Average plus 10
        C1 = (N1, N2) -> ((N1 + N2) / 2) + 10;
        System.out.println(C1.Sum(3, 7)); // Output: 15 (average + 10)

        // Example 2: Using TheGenericClass with Integer type
        TheGenericClass<Integer> TGC;

        // Lambda 4: Integer equality check
        TGC = (S1, S2) -> S1 == S2;
        System.out.println(TGC.IsTrue(3, 5)); // Output: false (3 != 5)

        // Example 3: Using TheGenericClass with String type
        TheGenericClass<String> TGSC;

        // Lambda 5: String equality check
        TGSC = (S1, S2) -> S1.equals(S2);
        System.out.println(TGSC.IsTrue("Ahmed", "Ahmed")); // Output: true

        // Lambda 6: Complex string comparison
        TGSC = (N1, N2) -> {
            // Concatenate the strings
            String S3 = N1 + N2;
            // Convert to uppercase
            S3 = S3.toUpperCase();
            // Check if first string equals the concatenated uppercase string
            return N1.equals(S3);
        };
        System.out.println(TGSC.IsTrue("Mostafa", "Yousef")); // Output: false
    }
}



