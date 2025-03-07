package TryCatchException;

//The Error Class in Java

//1. Error Class Hierarchy
//The Error class is a subclass of Throwable. It represents serious system-level problems
//that are beyond the control of an application.
public class Errors {
 public static void main(String[] args) {
     // The hierarchy of the Error class:
//Throwable -> Error -> (Various Subclasses like OutOfMemoryError, VirtualMachineError)
     try {
         throw new OutOfMemoryError("Out of memory error occurred!");
     } catch (Error e) {
         System.out.println("Caught Error: " + e.getMessage());  // This catches the OutOfMemoryError.
     }
     try {
         // Trigger a StackOverflowError with recursive method
         recursiveMethod();
     } catch (StackOverflowError e) {
         System.out.println("Caught StackOverflowError: " + e.getMessage());  // Caught StackOverflowError
     }
 }

 public static void recursiveMethod() {
     recursiveMethod();  // Will throw StackOverflowError
 }

}

//2. Errors are Unchecked
//Errors in Java are unchecked, meaning you don’t need to declare them with `throws` 
//or catch them explicitly.
/*
 * public class UncheckedError { public static void main(String[] args) { // No
 * need to catch or declare OutOfMemoryError because it is unchecked. try {
 * throw new OutOfMemoryError("Out of memory error"); } catch (OutOfMemoryError
 * e) { System.out.println("Caught Error: " + e.getMessage()); // Caught the
 * OutOfMemoryError } } }
 */

//3. Common Subclasses of Error
//Some common subclasses of Error:
//- VirtualMachineError: JVM failure
//- OutOfMemoryError: Out of memory
//- StackOverflowError: Stack exceeded due to deep recursion
//- AssertionError: Assertion failed
//- InternalError: Internal JVM error

//4. Should You Catch Errors? 
//- Generally, you should NOT catch Errors, as they represent serious problems 
//that the application cannot recover from.
//- It's not recommended to handle Errors in application code since they typically 
//signal severe JVM-level issues (like out of memory).

//5. No Recovery Expected for Errors
//Errors usually represent problems that the application cannot recover from.
//The system may need to be restarted or require external intervention.

//6. Example of Handling a StackOverflowError
//A StackOverflowError occurs due to deep recursion that exhausts the stack space.

 // Method that calls itself indefinitely to cause StackOverflowError

//7. When Errors Are Thrown
//Errors are thrown by the JVM in response to serious issues such as memory exhaustion,
//infinite recursion, or internal JVM failures.

//8. Errors Are Fatal and Indicate JVM Problems
//Errors like OutOfMemoryError, StackOverflowError, and VirtualMachineError indicate 
//fatal issues with the JVM or application, and recovery is generally not possible.

//9. No Need to Catch Errors Unless Monitoring JVM
//Errors are typically not caught unless you are doing something like monitoring the JVM, 
//which is uncommon in regular application development.

//10. Example of Handling AssertionError (A subclass of Error)

//11. Best Practice: Do Not Catch Errors
//Java best practice suggests that Errors should not be caught or handled. If they occur, 
//the application is usually in an irrecoverable state.

