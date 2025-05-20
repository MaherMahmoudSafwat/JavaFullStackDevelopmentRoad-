// Import necessary Java I/O classes
import java.io.BufferedReader;       // For efficient reading of text from a character-input stream
import java.io.IOException;          // For handling input/output exceptions
import java.io.InputStreamReader;    // For bridging byte streams to character streams
import java.util.Scanner;           // For parsing primitive types and strings using regular expressions

// Main class that contains the program
public class Main {
    // Main method - entry point of the program
    // 'throws IOException' indicates this method might throw an input/output exception
    public static void main(String[] args) throws IOException {
        // Prompt the user to enter a number
        System.out.println("Please enter a number :- ");

        /*
         * First method: Using InputStreamReader and BufferedReader
         * This is a classic Java approach for reading user input
         */

        // Create an InputStreamReader that reads from standard input (System.in)
        InputStreamReader In = new InputStreamReader(System.in);
        // Wrap the InputStreamReader in a BufferedReader for efficient reading
        BufferedReader BF = new BufferedReader(In);

        // Read a line of text from the user and convert it to an integer
        // Integer.parseInt() converts the String to an int
        // readLine() might throw IOException, which is declared in method signature
        int Number = Integer.parseInt(BF.readLine());

        // Output the number that was read
        System.out.println("You entered (via BufferedReader): " + Number);

        /*
         * Second method: Using Scanner
         * This is a more modern and convenient approach in Java
         */

        // Create a Scanner object that reads from standard input
        Scanner Input = new Scanner(System.in);
        // Use Scanner's nextInt() method to directly read an integer
        Number = Input.nextInt();
        // Output the number that was read
        System.out.println("You entered (via Scanner): " + Number);

        // Note: In real applications, you should close these resources when done
        // BF.close();
        // Input.close();
    }
}


