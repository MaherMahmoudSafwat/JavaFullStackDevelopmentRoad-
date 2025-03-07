package TryCatchException;

public class TryCatchException {
public static void main(String[]args)
{
	/*
	 1-Compile-Time Errors (Syntax Errors):
	 -These errors occur when the Java compiler cannot understand your code
	 -They are detected during the compilation process.
	 2-Runtime Errors (Exceptions):
	 -These errors occur during the execution of your program.
	 -They are not detected until the program is running.
	 3-Logical Errors:
	 These errors occur when your program compiles and runs without crashing, but it produces incorrect results.
	 They are the most difficult to find and fix because they are not detected by the compiler or runtime environment.
	 4- Logical Errors:
	 These errors occur when your program compiles and runs without crashing, but it produces incorrect results.
	 They are the most difficult to find and fix because they are not detected by the compiler or runtime environment.
	 They are caused by mistakes in your program's logic.
	 Examples:
	 Missing libraries
     Incorrect library versions.
	 Incompatible library architectures.
	 5. Resource Errors:
	 These errors occur when the program cannot obtain a needed system resource.
	 Examples:
	 OutOfMemoryError: The program is unable to allocate more memory.
 	 StackOverflowError: Occurs when the program's call stack overflows, often due to excessive recursion.
	*/
	 try {
         int result = 10 / 0;  // This will throw ArithmeticException
     } catch (ArithmeticException e) {
         System.out.println("Error: Cannot divide by zero!");  // Handling the exception
     }
	 try {
         int[] arr = new int[5];
         arr[10] = 30;  // This will throw ArrayIndexOutOfBoundsException
         int result = 10 / 0;  // This will throw ArithmeticException
     } catch (ArithmeticException e) {
         System.out.println("Arithmetic Exception: " + e.getMessage());
     } catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("Array Index Out of Bounds: " + e.getMessage());
     }
	 try {
         String s = null;
         System.out.println(s.length());  // NullPointerException
         int result = 10 / 0;  // ArithmeticException
     } catch (NullPointerException | ArithmeticException e) {
         System.out.println("Exception: " + e.getMessage());
     }
	// 4. Finally Block: Code That Always Runs After Try-Catch 
	 try {
         System.out.println("In try block");
         int result = 10 / 0;  // This will throw ArithmeticException
     } catch (ArithmeticException e) {
         System.out.println("Exception caught: " + e.getMessage());
     } finally {
         System.out.println("This is the finally block, always executed!");
     }
	 //5. Nested try and Catch 
	 try {
         try {
             int[] arr = new int[3];
             arr[5] = 10;  // This will throw ArrayIndexOutOfBoundsException
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("Caught in inner catch block: " + e.getMessage());
         }
     } catch (Exception e) {
         System.out.println("Caught in outer catch block: " + e.getMessage());
     }
	 //6. Catching Exception Object and Printing Stack Trace
	 try {
         int[] arr = new int[2];
         arr[5] = 10;  // This will throw ArrayIndexOutOfBoundsException
     } catch (Exception e) {
         e.printStackTrace();  // Print the exception stack trace
     }
	 //7. Defining Multiple Catch Blocks for Parent and Child Exceptions
	  try {
		  String S = null;
          throw new NullPointerException("Null reference exception");
      } catch (Exception e) {
          System.out.println("Caught general Exception: " + e.getMessage());
      }
		/*
		 * catch (NullPointerException e) {
		 * System.out.println("Caught NullPointerException: " + e.getMessage()); }
		 *///This is Not Allowed as it is unreachable and will never be reached as the Exception parent class is above NullPointerException. 
}
}
