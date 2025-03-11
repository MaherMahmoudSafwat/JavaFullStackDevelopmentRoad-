package InnerAndOuterLoopsInJava;

import java.util.Random;

public class InnerAndOuterLoopsInJava {
    public static void main(String[] args) {
    	 // Example 1: Break and Continue in Nested Loops (Inner and Outer Loops)
        System.out.println("Example 1: Break and Continue with Nested Loops:");

		/*
		 * Labeled break helps you exit both inner and outer loops directly from within
		 * a nested loop. continue is used to skip the current iteration of a loop and
		 * move to the next iteration, preventing the rest of the loop's code from
		 * executing in that iteration.
		 */       
        //Labeled helps you to break or continue to the loop you wanted.
        // Label for the outer loop to refer to it from inner loop
        outerLoop:  // This label is used to refer to the outer loop for the break statement
        for (int i = 1; i <= 5; i++) {  // Outer loop (iterating from 1 to 5)
            // The inner loop starts inside the outer loop and also iterates from 1 to 5
            	for (int j = 1; j <= 5; j++) {  // Inner loop (nested within outer loop)
                
                // Case where we break out of the inner loop if i == 3 and j == 3
                if (i == 3 && j == 3) {
                    // Print message indicating we're breaking the inner loop
                    System.out.println("Breaking inner loop at i = " + i + " and j = " + j);
                    break;  // Breaks out of the inner loop. The inner loop will stop at this point.
                    //break outerLoop;This is Allowed to break from OuterLoop at once now In the For. 
                }
                
                // Case where we skip the current iteration of the inner loop if i == 4 and j == 2
                if (i == 4 && j == 2) {
                    // Print message indicating we're skipping this iteration of the inner loop
                    System.out.println("Skipping inner loop iteration at i = " + i + " and j = " + j);
                    continue;  // Skips the current iteration of the inner loop, moving to the next value of j
                }
                
                // This line is executed if neither break nor continue was triggered
                System.out.println("Inner loop: i = " + i + ", j = " + j); 
            }

            // Case where we break out of the outer loop if i == 4
            if (i == 4) {
                // Print message indicating we're breaking out of the outer loop
                System.out.println("Breaking outer loop at i = " + i);
                break outerLoop;  // This breaks the outer loop (using the label 'outerLoop')
            }
        }
        // Example 2: Using Varargs in a Method
        System.out.println("\nExample 2: Using Varargs in a Method:");
        int sum = sumOfNumbers(5, 10, 15, 20, 25);  // Pass multiple arguments
        System.out.println("Sum of numbers: " + sum);  // Output: 75

        // Calling the method with no arguments
        sum = sumOfNumbers();  
        System.out.println("Sum of numbers with no arguments: " + sum);  // Output: 0

        // Example 3: Generating Random Numbers using the Random class
        System.out.println("\nExample 3: Generating Random Numbers:");
        Random random = new Random();
        int randomNumber = random.nextInt(100);  // Random number between 0 and 99
        //randomNumber = (int)(Math.random()*10);
        System.out.println("Generated random number between 0 and 99: " + randomNumber);
    }

    // Method that accepts varargs and returns the sum of the numbers and acts as if they are it is an array.
    public static int sumOfNumbers(int... nums) {
        int sum = 0;  // Initialize sum to 0
        for (int num : nums) {
            sum += num;  // Add each number to sum
        }
        return sum;  // Return the sum of numbers
    }
}
