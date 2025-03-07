package TryCatchException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//6. Throws Keyword: Declaring Exceptions to be Handled Later
public class Throws {
 public static void main(String[] args) {
     try {
         checkAge(15);
     } catch (Exception e) {
         System.out.println("Caught Exception: " + e.getMessage());
     }
     //Unchecked Exceptions They may not have try and catch (Optional).
     try {
         String s = null;
         System.out.println(s.length());  // This will throw NullPointerException
     } catch (NullPointerException e) {
         System.out.println("Caught exception: " + e.getMessage());  // Handling unchecked exception
     }
     //Checked Exceptions they must have try and catch 
     try {
     FileReader file = new FileReader("nonexistentfile.txt");  // Checked Exception
     BufferedReader fileInput = new BufferedReader(file);
     fileInput.read();
     fileInput.close();
     } catch (IOException e) {
     System.out.println("Exception: " + e.getMessage());  // Handling checked exception
     }
 }

 // Declaring that this method may throw an exception
 public static void checkAge(int age) throws Exception {
     if (age < 18) {
         throw new Exception("Age must be 18 or older");
     }
 }
}