import java.io.File;              // Import for File class that represents file or directory path.
import java.io.FileInputStream;    // Import for FileInputStream class that reads bytes from a file.
import java.io.FileNotFoundException;  // Import for exception handling in case the file is not found.
import java.io.IOException;        // Import for exception handling in case of IO errors.

public class Main {
    public static void main(String[] args) throws IOException {

        // Create a File object representing the file at the specified location.
        // This is used to work with the file's path and properties.
        File F = new File("C:\\Users\\Maher\\a.txt");

        // Create a FileInputStream to read bytes from the specified file.
        // If the file does not exist, a FileNotFoundException will be thrown.
        FileInputStream fis = new FileInputStream(F);

        // Create a byte array large enough to hold all the bytes in the file.
        // The length of the file is determined using F.length(), which returns the file's size in bytes.
        byte[] b = new byte[(int) F.length()];

        // Read the entire file content into the byte array 'b'.
        fis.read(b);

        // Convert the byte array into a String. This assumes the file contains text encoded in a standard charset like UTF-8.
        String S = new String(b);

        // Print the string representation of the file content.
        System.out.println(S);

        // Now, we'll read the file byte by byte and print each character.

        // Initialize a variable to hold byte data.
        int data = 0;

        // Read the file byte by byte using fis.read().
        // The read() method returns -1 when the end of the file is reached.
        while ((data = fis.read()) != -1) {
            // Cast the byte to a char and print it.
            System.out.println((char) data);
        }

        // Additional reads to show specific characters, will print more characters as long as they are available.
        // Note: These reads will return -1 if the end of the file is reached.

        System.out.println((char) fis.read()); // Prints the next character from the file.
        System.out.println((char) fis.read()); // Prints the next character from the file.
        System.out.println((char) fis.read()); // Prints the next character from the file.
        System.out.println((char) fis.read()); // Prints the next character from the file.

        // Close the FileInputStream after use to release the resources.
        fis.close();
    }
}
