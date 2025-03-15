import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        // Create a FileWriter object to write data to a file named "Test.txt".
        // FileWriter is used to write raw characters to a file.
        Writer FW = new FileWriter("Test.txt");

        // Create a BufferedWriter that wraps around the FileWriter for efficient writing.
        // BufferedWriter provides buffering to the underlying Writer and can improve performance.
        BufferedWriter BW = new BufferedWriter(FW);

        // Create another FileWriter instance to write to "Test.txt" again.
        FileWriter fw = new FileWriter("Test.txt");

        // Wrap the new FileWriter with a BufferedWriter for efficient character output.
        // BufferedWriter is used to write text data line by line.
        BW = new BufferedWriter(fw);

        // Both of the above BufferedWriter setups are correct — one wraps a FileWriter
        // and the other wraps a FileWriter in a BufferedWriter. This is to ensure fast and efficient writing.

        // Write the integer 97 to the file. BufferedWriter writes the character corresponding
        // to the Unicode value of 97, which is 'a'.
        BW.write(97);

        // Move to the next line in the file.
        BW.newLine();

        // Write the string "AAA" to the file.
        BW.write("AAA");

        // Make sure all data is flushed from the BufferedWriter to the file.
        BW.flush();

        // Close the BufferedWriter to save data to the file and release resources.
        BW.close();

        // Also close the original FileWriter to ensure proper file closing and saving.
        fw.close();

        // Open the file again in **append mode**.
        // This allows us to add more content to the file without overwriting the existing content.
        fw = new FileWriter("Test.txt", true);

        // Create a new BufferedWriter to wrap around the new FileWriter.
        BW = new BufferedWriter(fw);

        // Move to a new line in the file before writing.
        BW.newLine();

        // Write the string "aAAA" to the file.
        BW.write("aAAA");

        // **Note:** You cannot write floating-point or boolean values directly using BufferedWriter.
        // If you try, it will result in an error.
        // BW.write(100.9);  // This is NOT allowed because BufferedWriter works only with characters.

        // Flush the BufferedWriter to ensure everything is written to the file.
        BW.flush();

        // Now, let's read the content of the file using BufferedReader.
        // BufferedReader is used to read the text from a file line by line efficiently.
        BufferedReader BR = new BufferedReader(new FileReader("Test.txt"));

        // Declare a string variable to hold each line of text as we read it.
        String Line = null;

        // Read each line of the file until the end is reached (when `readLine()` returns null).
        while ((Line = BR.readLine()) != null) {
            // Print each line to the console.
            System.out.println(Line);
        }

        // After reading, close the BufferedReader to release the file resources.
        BR.close();
    }
}
