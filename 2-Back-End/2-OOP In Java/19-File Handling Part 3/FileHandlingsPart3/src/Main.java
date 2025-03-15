import java.io.*;
import java.nio.charset.StandardCharsets;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        // Create a FileOutputStream to write data to a file named "Test.txt"
        FileOutputStream fos = new FileOutputStream(("Test.txt"));

        // Write the string "10" to the file by first converting it to bytes
        fos.write(Integer.toString(10).getBytes());

        // Create a FileInputStream to read data from "Test.txt"
        FileInputStream fis = new FileInputStream("Test.txt");

        // Read and print the first byte from the file (ASCII value of the first character)
        System.out.println(fis.read());

        // Write the byte value 10 (ASCII value for newline) to the file
        fos.write(10);

        // Write the string "ABC" to the file in byte format
        fos.write("ABC".getBytes());

        // Read and print the next byte from the file (this would be "A")
        System.out.println((char)fis.read());

        // Write the byte value 10 again (new line)
        fos.write(10);

        // Write the string "مرحبا" to the file using UTF-8 encoding
        fos.write("مرحبا".getBytes(StandardCharsets.UTF_8));

        // Create a File object representing the file "Picture1.jpg"
        File file = new File("C:\\Users\\Maher\\Pictures\\Camera Roll\\Picture1.jpg");

        // Create a FileInputStream to read the content of the image file
        FileInputStream fin = new FileInputStream(file);

        // Create a byte array large enough to hold the entire content of the image
        byte[] b = new byte[(int)file.length()];

        // Read the entire content of the image into the byte array
        fin.read(b);

        // Loop through the byte array and print each byte (this will display binary data)
        for (int i = 0; i < b.length; i++) {
            System.out.println(b[i]);
        }

        // Create a FileOutputStream to write the image content to a new file "Picture2.jpg"
        FileOutputStream Fos = new FileOutputStream("C:\\Users\\Maher\\Pictures\\Camera Roll\\Picture2.jpg");

        // Write the byte array containing the image content to the new file
        Fos.write(b);

        // Ensure all data is written to the file
        Fos.flush();

        // Close the output stream to release the system resources
        Fos.close();
        /*
        Yes, you can handle image files (or any binary data) that are larger than just a few bytes, such as those in the megabyte (MB), gigabyte (GB),
        or even terabyte (TB) range, using the same byte-based approach. The key difference is that larger files simply require handling larger byte arrays.

        Let me break it down:

        How Images Work with Byte Arrays
        Image Files (like .jpg, .png, etc.) are made up of binary data, not characters. In Java, you can treat this binary data as a byte array (byte[]).
        Byte Arrays are capable of holding any amount of data, no matter how large the file is. The only constraint is the available memory (RAM)
        and, in some cases, file system limitations.
        Handling Large Image Files (MB, GB, or TB)
        When you're dealing with large image files:

        Byte Arrays can still represent very large files, but the size of the array might become quite large as the image increases in
        size (for example, an image in the GB or TB range).
        Memory Management: You need to be mindful of the memory available. Large files might require more memory to load into a byte array,
        and attempting to load very large files directly into memory could lead to OutOfMemoryError if the JVM doesn't have enough heap space to allocate the byte array.
        */
    }
}
