import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// Main class to demonstrate working with files and directories in Java
public class Main {
    public static void main(String[] args) throws IOException {

        // Create a File object representing a folder named "Folder1"
        // The `mkdir()` method is used to create a single directory
        File F1 = new File("Folder1");
        F1.mkdir(); // Creates Folder1

        // Delete the "Folder1" directory after creating it
        F1.delete(); // Deletes Folder1

        // Create a new File object representing a nested directory structure
        // The `mkdirs()` method creates all the directories in the given path if they don't exist
        F1 = new File("Folder1/Folder2/Folder3");
        F1.mkdirs(); // Creates Folder1/Folder2/Folder3

        // Delete the "Folder3" directory and all its parent directories
        F1.delete(); // Deletes Folder1/Folder2/Folder3

        // Now create a new File object for "Folder1" to list its contents
        F1 = new File("Folder1");

        // The `list()` method returns the names of all files and directories in "Folder1"
        String[] Arr = F1.list();

        // Print all the items (files or directories) in "Folder1"
        for (String S : Arr) {
            System.out.println(S); // Prints the name of each item in Folder1
        }

        // Create a new File object representing "Folder1/ABC" to list its contents
        F1 = new File("Folder1/ABC");

        // Check if "Folder1/ABC" is a directory before listing its contents
        if (F1.isDirectory()) {
            // If it's a directory, print the names of all files or subdirectories inside it
            Arr = F1.list();
            for (String S : Arr) {
                System.out.println(S); // Prints the name of each item in Folder1/ABC
            }
        }

        // Create a new file named "A.txt" inside "Folder1"
        File F = new File("Folder1", "A.txt");
        F.createNewFile(); // Creates "A.txt" in Folder1

        // Create another file named "Text.txt" inside "Folder1"
        F = new File("Folder1");
        F1 = new File(F, "Text.txt");
        F1.createNewFile(); // Creates "Text.txt" inside Folder1

        // Create a FileOutputStream to write data to "Text.txt"
        FileOutputStream FOS = new FileOutputStream(F1);
        FOS.write("AAAAA".getBytes()); // Writes the string "AAAAA" to the file

        // Create a FileInputStream to read data from "Text.txt"
        FileInputStream FIN = new FileInputStream(F1);

        // Read and print the contents of the file byte by byte
        int Data = 1;
        while ((Data = FIN.read()) != -1) {
            System.out.println((char) Data); // Prints the characters from "Text.txt"
        }
    }
}
