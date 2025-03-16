import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

// This is the main class for the program
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Create a File object that represents the file Test.txt
        File F = new File("Test.txt");

        // Create a Scanner object to read from the file
        Scanner Scan = new Scanner(F);

        // While there are lines in the file, print each line
        while (Scan.hasNextLine()) {
            System.out.println(Scan.nextLine());  // Read and print one line at a time
        }

        // Close the Scanner object after reading the file
        Scan.close();

        // Recreate the Scanner object to read the file again (this is necessary after closing it)
        Scan = new Scanner(F);

        // Use the regular expression "\\z" to read the entire file as one single string
        // This can be useful when you want to process the whole file at once
        Scan.useDelimiter("\\z");

        // Print the whole file as a single string
        while (Scan.hasNextLine()) {
            System.out.println(Scan.nextLine());
        }

        // Close the Scanner object after reading the file
        Scan.close();

        // Recreate the Scanner object to read the file again
        Scan = new Scanner(F);

        // Again, using the "\\z" delimiter but this time reading the file one token at a time
        Scan.useDelimiter("\\z");

        // Print the whole file as a single token (this approach reads everything as one large block of text)
        while (Scan.hasNext()) {
            System.out.println(Scan.next());
        }

        // Close the Scanner object after reading the file
        Scan.close();

        // Recreate the Scanner object for reading the file again
        Scan = new Scanner(F);

        // Use a delimiter to split based on any non-digit characters (using "\\D+" regex)
        // This will split the file into tokens wherever it finds non-digit characters
        Scan.useDelimiter("\\D+");

        // Print each token (split by non-digit characters)
        while (Scan.hasNext()) {
            System.out.println(Scan.next());
        }

        // Close the Scanner object after reading the file
        Scan.close();

        // Now create a new Scanner to process a manually specified string
        // In this case, we're manually specifying the string "AAA,BBB,CCC"
        Scan = new Scanner("AAA,BBB,CCC");

        // Set the delimiter to split the string at commas
        Scan.useDelimiter(Pattern.compile(","));

        // Print the current delimiter being used
        System.out.println(Scan.delimiter());

        // Print each part of the string after splitting it by commas
        while (Scan.hasNext()) {
            System.out.println(Scan.next());
        }

        // Close the Scanner object after processing the string
        Scan.close();

        // Recreate the Scanner object to read the file again
        Scan = new Scanner(F);

        // Process each line from the file and pass it to the ParseLine method
        // This method will handle parsing each line into parts (Name, Address, PhoneNumber)
        while (Scan.hasNextLine()) {
            ParseLine((Scan.nextLine()));  // Process each line from the file
        }

        // Close the Scanner object after reading the file
        Scan.close();
    }

    // This method takes a line of text, splits it into parts, and prints them
    // It assumes that each line has exactly three parts (Name, Address, PhoneNumber)
    public static void ParseLine(String Str) {
        // Declare variables to store Name, Address, and PhoneNumber
        String Name, Address, PhoneNumber;

        // Create a new Scanner object to parse the line (Str)
        Scanner Scan = new Scanner(Str);

        // Set the delimiter to split the string at commas
        Scan.useDelimiter(",");

        // Read and assign each part of the line (Name, Address, PhoneNumber)
        while (Scan.hasNext()) {
            Name = Scan.next().trim();  // Read and trim extra spaces from Name
            Address = Scan.next().trim();  // Read and trim extra spaces from Address
            PhoneNumber = Scan.next().trim();  // Read and trim extra spaces from PhoneNumber

            // Print the parsed information (Name, Address, PhoneNumber)
            System.out.println("Name : " + Name + " Address : " + Address + " Phone Number : " + PhoneNumber);
        }

        // Close the Scanner object after processing the line
        Scan.close();
    }
}
