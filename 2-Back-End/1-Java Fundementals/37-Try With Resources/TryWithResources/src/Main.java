// Import required I/O classes from Java's standard library
import java.io.BufferedReader;      // For efficient reading of text
import java.io.IOException;        // For handling input/output errors
import java.io.InputStreamReader;  // Converts byte streams to character streams

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
/*
 * This is a IDE-generated hint comment about how to run the program
 * It's specific to IntelliJ IDEA and doesn't affect code execution
 */
public class Main
{
    /*
     * The main method - entry point of the Java program
     * 'args' parameter can receive command-line arguments (unused here)
     */
    public static void main(String[] args)
    {
        /*
         * Create an InputStreamReader that:
         * - Takes System.in (standard input stream) as source
         * - Converts raw bytes from keyboard to characters
         * - 'In' is the variable name (note unconventional capitalization)
         */
        InputStreamReader In = new InputStreamReader(System.in);

        /*
         * Try-with-resources block:
         * - Automatically manages resources (closes them when done)
         * - BufferedReader ('BF') wraps InputStreamReader for efficient reading
         * - Parentheses after 'try' declare resources to be auto-closed
         */
        try (BufferedReader BF = new BufferedReader(In)){
            /*
             * Read and process input:
             * 1. BF.readLine() waits for user to enter text and press Enter
             * 2. Integer.parseInt() converts the String input to an int
             * 3. Result stored in 'Number' (note unconventional capitalization)
             *
             * Potential exceptions:
             * - IOException if reading fails
             * - NumberFormatException if input isn't a valid integer (unhandled)
             */
            int Number = Integer.parseInt(BF.readLine());

            /*
             * Note: The parsed number isn't used or displayed
             * This is likely just a demonstration of reading input
             */

        } catch (IOException e) {
            /*
             * Handle IO-related errors by:
             * - Wrapping in RuntimeException
             * - Throwing it up the call stack
             *
             * Note: This isn't ideal error handling as it:
             * 1. Doesn't give user-friendly feedback
             * 2. Terminates the program abruptly
             */
            throw new RuntimeException(e);
        }

       /* finally
        {
            try {
                BF.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/
        /*
         * This commented-out finally block shows manual resource cleanup
         * It's not needed because:
         * 1. try-with-resources already handles closing automatically
         * 2. 'BF' wouldn't be accessible here anyway (scoping issue)
         *
         * If used, it would:
         * 1. Attempt to close the BufferedReader
         * 2. Handle any IOExceptions during closing
         * 3. Convert them to RuntimeExceptions
         */
    }
}


