package Arrays;

//2. Two-dimensional array (2D Array)
//A two-dimensional array is essentially an array of arrays. It is like a table with rows and columns.
public class Arrays2D {

 public static void main(String[] args) {
     // Declare and initialize a 2D array (3 rows, 2 columns)
     int[][] matrix = {
         {1, 2},
         {3, 4},
         {5, 6}
     };

     // Access and print elements from a 2D array
     System.out.println("Element at row 0, column 0: " + matrix[0][0]);  // Output: 1
     System.out.println("Element at row 1, column 1: " + matrix[1][1]);  // Output: 4

     // Loop through the 2D array using nested loops
     for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[i].length; j++) {
             System.out.print(matrix[i][j] + " ");  // Print each element in the matrix
         }
         System.out.println();  // New line after each row
     }
     System.out.println("Length of the 2D Array is " + matrix.length);
 }
}
