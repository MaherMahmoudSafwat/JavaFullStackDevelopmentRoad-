import java.util.StringTokenizer;

/**
 * This class demonstrates the differences between String, StringBuilder,
 * StringBuffer, and StringTokenizer in Java with practical examples.
 */
public class Main {
    public static void main(String[] args) {
        // ==============================================
        // STRING IMMUTABILITY DEMONSTRATION
        // ==============================================
        /*
         * Strings in Java are immutable - once created they cannot be modified.
         * Any operation that appears to modify a string actually creates a new string object.
         * The commented line below would cause a compilation error.
         */
        String x = "hello";
        // x[0] = "d";  // This would fail - Strings are immutable

        // ==============================================
        // STRINGBUILDER DEMONSTRATION
        // ==============================================
        /*
         * StringBuilder is mutable and NOT thread-safe.
         * It provides efficient string manipulation operations.
         * Ideal for single-threaded environments where performance matters.
         */
        StringBuilder SB = new StringBuilder("Hello ");
        SB.append(" World");      // Appends text to existing string
        System.out.println(SB);   // Output: Hello World

        SB.reverse();             // Reverses the string content
        System.out.println(SB);    // Output: dlroW olleH

        SB.insert(5, "123 ");      // Inserts text at position 5
        System.out.println(SB);    // Output: dlroW 123 olleH

        SB.delete(3, 9);           // Deletes characters from index 3 to 8
        System.out.println(SB);    // Output: dlr 123 olleH

        // ==============================================
        // STRINGBUFFER DEMONSTRATION
        // ==============================================
        /*
         * StringBuffer is similar to StringBuilder but is thread-safe.
         * It uses synchronization which makes it slightly slower.
         * Use when thread safety is required in multi-threaded environments.
         */
        StringBuffer Sb = new StringBuffer("I love java");
        Sb.append(" Language");    // Appends text to existing string
        System.out.println(Sb);    // Output: I love java Language

        Sb.reverse();              // Reverses the string content
        System.out.println(Sb);     // Output: egaugnaL avaj evol I

        Sb.insert(7, "12345");     // Inserts text at position 7
        System.out.println(Sb);    // Output: egaugna12345L avaj evol I

        Sb.delete(5, 13);           // Deletes characters from index 5 to 12
        System.out.println(Sb);     // Output: egaug12345L avaj evol I

        // ==============================================
        // STRINGTOKENIZER DEMONSTRATION
        // ==============================================
        /*
         * StringTokenizer is a legacy class for splitting strings into tokens.
         * It's simpler than String.split() but doesn't support regular expressions.
         * Modern code should typically use String.split() instead.
         */
        String Str = "Name|Email|Password|PhoneNumber|Address";
        StringTokenizer Data = new StringTokenizer(Str, "|");

        // Loop through all available tokens
        while(Data.hasMoreTokens()) {
            // Print each token (substring separated by |)
            System.out.println(Data.nextToken());
        }
        /* Output:
           Name
           Email
           Password
           PhoneNumber
           Address
        */
    }
}
