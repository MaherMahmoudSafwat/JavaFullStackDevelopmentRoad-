package TryCatchException;

//Define a custom exception class
class InvalidAgeException extends Exception {
 public InvalidAgeException(String message) {
     super(message);  // Pass the custom message to the Exception class
 }
}

public class Throw {
 public static void main(String[] args) {
     try {
         int age = -5;  // The try block is executed, and the age variable is set to -5.
         if (age < 0) {
             // The if statement checks if age is less than 0.
             // Since age is -5, the condition evaluates to true, and the InvalidAgeException is thrown with the message "Age cannot be negative".
             throw new InvalidAgeException("Age cannot be negative");
         }
     } catch (InvalidAgeException e) {
         // The thrown exception is caught by the catch block, which handles the InvalidAgeException.
         // Inside the catch block, the System.out.println("Caught Exception: " + e.getMessage()); is executed,
         // printing the message "Caught Exception: Age cannot be negative" to the console.
         System.out.println("Caught Exception: " + e.getMessage());
     }
 }
}
