import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Main class to demonstrate working with files in Java
public class Main {
    public static void main(String[] args) throws IOException {

        // Create a File object representing a file named "ABC.txt"
        File F = new File("ABC.txt");

        // Check if the file already exists and print the result (true or false)
        System.out.println(F.exists());

        // Create the file if it doesn't already exist (returns true if file is created)
        F.createNewFile();

        // After attempting to create, check if the file now exists
        System.out.println(F.exists());

        // Print the name of the file (just the name, without the full path)
        System.out.println("File Name: " + F.getName());

        // Print the absolute path of the file (includes the full path from the root)
        System.out.println("File Absolute Path : " + F.getAbsolutePath());

        // Print the file's path (relative to the current directory)
        System.out.println("File Path: " + F.getPath());

        // Check if the file is writable and print the result (true or false)
        System.out.println("Writable: " + F.canWrite());

        // Check if the file is readable and print the result (true or false)
        System.out.println("Readable: " + F.canRead());

        // Print the size of the file in bytes
        System.out.println("File Size in Bytes: " + F.length());

        // Check if the file is a directory and print the result (true or false)
        System.out.println("Is Directory: " + F.isDirectory());

        // Check if the file is a regular file and print the result (true or false)
        System.out.println("Is File: " + F.isFile());

        // Check if the file is hidden and print the result (true or false)
        System.out.println("Is Hidden: " + F.isHidden());

        // Get the last modified time of the file in milliseconds (unix timestamp)
        long lastModified = F.lastModified();

        // Format the last modified timestamp into a readable date format (e.g., "yyyy-MM-dd HH:mm:ss")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = sdf.format(new Date(lastModified));

        // Print the formatted last modified date
        System.out.println("Last Modified: " + formattedDate);

        // Check if the file is executable and print the result (true or false)
        System.out.println("can Execute it: " + F.canExecute());
    }
}
