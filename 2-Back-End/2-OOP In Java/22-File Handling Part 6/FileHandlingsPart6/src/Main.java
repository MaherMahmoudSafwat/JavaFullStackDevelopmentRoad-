import java.io.*;

// Main class to demonstrate various file handling and writing/reading techniques in Java
public class Main {
    public static void main(String[] args) throws IOException {

        // Create a FileOutputStream to write data to a file named "Test.txt"
        // FileOutputStream writes raw bytes to a file (works with byte data)
        FileOutputStream fos = new FileOutputStream("Test.txt");

        // Create a string to write to the file
        String S = "Hello";

        // Convert the string into a byte array (since FileOutputStream works with bytes)
        byte[] b = S.getBytes();

        // Write the byte array to the file
        fos.write(b);

        // Close the FileOutputStream to save the file and release resources
        fos.close();

        // Create an OutputStreamWriter, which allows you to write characters to a file.
        // It converts characters to bytes, using a specified encoding (default is UTF-8)
        Writer writer = new OutputStreamWriter(new FileOutputStream("Test.txt"));

        // Change the string to be written to "Hi"
        S = "Hi";

        // Write the string to the file using the OutputStreamWriter
        writer.write(S);

        // Flush the writer to ensure all characters are written to the file
        writer.flush();

        // Create a FileWriter, which is a subclass of Writer, to write characters to a file
        // It also uses a default encoding (UTF-8)
        FileWriter FW = new FileWriter("Test.txt");

        // Write the integer value 97 to the file as a character (since FileWriter writes characters)
        // The value 97 corresponds to the character 'a' in the ASCII/UTF-8 encoding
        FW.write(97); // This will write the character 'a' to the file

        // Close the FileWriter to save the file and release resources
        FW.close();

        // Create a File object pointing to the "Test.txt" file to be read
        File file = new File("Test.txt");

        // Create a FileReader to read the contents of the file
        // FileReader is used to read the file as characters (not bytes)
        FileReader fr = new FileReader(file);

        // Declare a variable to hold the character being read
        int i = 5;

        // Read the file character by character, printing each one to the console
        // The read() method returns the character as an integer (its Unicode value)
        while((i = fr.read()) != -1) {
            System.out.println((char) i); // Convert the integer to a character and print it
        }

        // Create a char array to hold all characters of the file
        // Use the file's length to determine the array size
        char[] ch = new char[(int) file.length()];

        // Read the entire file into the char array
        fr.read(ch);

        // Print each character in the char array
        for (char c : ch) {
            System.out.println(c); // Prints each character one by one
        }

        // Close the FileReader to release resources
        fr.close();
    }
    /*
     * UTF-8 (Unicode Transformation Format - 8-bit) is a character encoding standard that can represent virtually all characters in the
     * Unicode standard. It is widely used in modern applications, web technologies, and programming languages.
     *
     * Below is a breakdown of the key concepts, benefits, and important points related to UTF-8 encoding:
     */

// 1. **Unicode Standard**:
//    - UTF-8 is part of the **Unicode** standard, which aims to represent every character used in all human writing systems.
    //   - Unicode assigns a unique **code point** to every character, supporting over **1.1 million characters** (including symbols, emoji, and even ancient scripts).

// 2. **Variable-Length Encoding**:
//    - UTF-8 is a **variable-length encoding**, meaning that different characters take different numbers of bytes (1 to 4 bytes).
//    - For example:
//        - Characters in the **ASCII range** (U+0000 to U+007F) use **1 byte**.
//        - Characters outside the ASCII range use **2, 3, or 4 bytes**, depending on the character's code point.
//    - This makes UTF-8 both **compact and flexible**.

// 3. **Compatibility with ASCII**:
//    - UTF-8 is **backward-compatible** with **ASCII**.
//    - ASCII characters (U+0000 to U+007F) are represented with exactly **1 byte**, which makes any valid ASCII text also valid UTF-8 text.
//    - This compatibility means that UTF-8 works seamlessly for languages like English, which predominantly use ASCII characters.

    // 4. **Global Coverage**:
    //    - UTF-8 is capable of representing characters from **all languages**, including:
    //        - **Latin-based alphabets** (English, Spanish, French, etc.).
    //        - **Non-Latin scripts** (Cyrillic, Greek, Hebrew, Arabic, etc.).
    //        - **Asian scripts** (Chinese, Japanese, Korean, Hindi, etc.).
    //        - **Symbols and emoji** (like heart symbols, smiley faces, and modern icons).
    //        - **Ancient and historical scripts** (e.g., Egyptian hieroglyphs, ancient Greek, etc.).
    //    - This makes UTF-8 ideal for global applications, websites, and communications, as it supports a wide range of languages and characters.

// 5. **Efficient Storage**:
//    - UTF-8 is efficient because it uses **1 byte** for ASCII characters (which are common in English text) and only uses **additional
//    bytes** (up to 4 bytes) for less common characters.
    //    - This means that **text with mostly ASCII characters (like English)** is stored efficiently, while still supporting a vast set of
    //    global characters without wasting space.

    // 6. **How UTF-8 Encoding Works**:
    //    - UTF-8 uses a special encoding system where:
    //        - **1 byte** is used for code points in the ASCII range (U+0000 to U+007F).
    //        - **2 bytes** for code points in the range U+0080 to U+07FF.
    //        - **3 bytes** for code points in the range U+0800 to U+FFFF.
    //        - **4 bytes** for code points in the range U+10000 to U+10FFFF.
    //    - The encoding is **self-synchronizing** — meaning that you can easily identify where a character starts by examining the first byte.
    //    - Example:
    //      - The character `'A'` (Unicode U+0041) is encoded as `0x41` in UTF-8 (1 byte).
    //      - The character `'€'` (Euro symbol, Unicode U+20AC) is encoded as `0xE2 0x82 0xAC` (3 bytes).
    //      - The character `'𐍈'` (Gothic letter, Unicode U+10348) is encoded as `0xF0 0x90 0x8D 0x88` (4 bytes).

// 7. **Unicode Code Points and UTF-8**:
//    - A **Unicode code point** is a unique identifier for each character. For example:
//        - The character `'A'` has the code point **U+0041**.
//        - The character `'€'` (Euro symbol) has the code point **U+20AC**.
//    - UTF-8 represents these code points as sequences of 1 to 4 bytes, depending on their value in the Unicode table.

// 8. **Wide Adoption**:
//    - UTF-8 is the **default encoding** for many web technologies like HTML, XML, JSON, and APIs.
    //    - It is the **most widely used character encoding** on the internet, with **over 90%** of websites using UTF-8 as the standard.
    //    - It is also the preferred encoding in many programming languages (including Java, Python, and JavaScript).

// 9. **Advantages of UTF-8**:
//    - **Backward compatibility** with ASCII ensures easy integration with older systems and applications.
    //    - It is **self-synchronizing**, meaning you can easily determine the start of each character.
    //    - UTF-8 is highly **efficient** for ASCII-based text while still being able to handle complex characters.
    //    - It's widely **supported**, meaning it works across different systems, programming languages, and platforms.
    //    - It **minimizes storage requirements** for English and other ASCII-heavy text.

    // 10. **Disadvantages of UTF-8**:
    //    - The variable-length nature of UTF-8 means that **non-ASCII characters** (e.g., Chinese, Japanese, emoji) take **more storage
    //    space** compared to single-byte encodings like ASCII or ISO-8859-1.
    //    - However, this drawback is often outweighed by the benefits of supporting the global character set.

    // 11. **Practical Examples**:
    //    - Example of UTF-8 encoding:
    //      - **Character 'A' (U+0041)**: Encoded as `0x41` (1 byte).
    //      - **Character '€' (U+20AC)**: Encoded as `0xE2 0x82 0xAC` (3 bytes).
    //      - **Character '𐍈' (U+10348)**: Encoded as `0xF0 0x90 0x8D 0x88` (4 bytes).
    //
    //    - **UTF-8 is used for representing characters in modern databases, web pages, and applications**. It ensures that text, including special
    //    characters, symbols, and multilingual data, can be processed and displayed correctly across platforms.

    // 12. **Common Uses of UTF-8**:
    //    - **Web pages**: Most websites are built using UTF-8 to display a variety of characters and symbols.
    //    - **File formats**: Common file formats like JSON, XML, and HTML use UTF-8 to store and transmit data.
    //    - **Programming languages**: UTF-8 is used by default in many modern programming languages and environments.

    // Conclusion:
    // UTF-8 is a powerful, flexible, and efficient character encoding that can handle all the world's writing systems.
    // It is the most widely used encoding for modern computing and web development due to its compatibility with ASCII, support for a
    // vast range of characters, and storage efficiency.
}
