package Arrays;

//1. One-dimensional array
//A one-dimensional array is a simple list of elements of the same type.
public class Arrays1D {

 public static void main(String[] args) {
     // Declare and initialize an array of integers
     int[] numbers = {1, 2, 3, 4, 5};  // Array of 5 integers

     // Access elements by their index
     System.out.println("First element: " + numbers[0]);  // Output: 1
     System.out.println("Last element: " + numbers[4]);   // Output: 5

     // Loop through the array to print all elements
     for (int i = 0; i < numbers.length; i++) {
         System.out.println("Element " + i + ": " + numbers[i]);
     }
 }
}
