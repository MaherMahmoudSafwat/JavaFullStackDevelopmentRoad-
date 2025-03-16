import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Main class
public class Main {
    public static void main(String[] args) throws IOException {

        // --- Appending to a file ---
        // The FileWriter constructor is called with 'true' as the second argument, which enables appending mode.
        // This means the file "ABC.txt" will not be overwritten; instead, new data will be added to the end.
        FileWriter FW = new FileWriter("ABC.txt", true);

        // Create a PrintWriter object which is used to write formatted text to the file.
        // The 'true' argument enables auto-flushing, so it automatically writes to the file after each println/print.
        PrintWriter PW = new PrintWriter(FW, true);

        // Write various types of data to the file (numbers, text, booleans, etc.)
        PW.println(97);            // Write an integer (ASCII value for 'a')
        PW.println("ABC.TXT");     // Write a string to the file
        PW.println(true);          // Write a boolean value
        PW.print(195.999);         // Write a double value (without newline after it)
        PW.println();              // Write a newline character

        // Writing a single byte/character (ASCII value 197) directly to the file
        PW.write(197);             // Writes a specific byte value (which can be interpreted as a character in the file)

        // Flush the buffer to ensure everything is written to the file before closing
        PW.flush();

        // Close the PrintWriter (this also closes the underlying FileWriter)
        PW.close();

        // --- Overwriting a file ---
        // The next block demonstrates how to overwrite a file instead of appending.
        // This time, the second argument to FileWriter is omitted, which defaults to 'false', meaning overwriting.

        FW = new FileWriter("ABC.txt");  // Overwrite mode (false by default)

        // Create a new PrintWriter object
        PW = new PrintWriter(FW, true);

        // Write a single character to the file ('A' in this case)
        PW.println('A');

        // Close the PrintWriter and FileWriter to release resources
        PW.close();
        FW.close();
    }
}
