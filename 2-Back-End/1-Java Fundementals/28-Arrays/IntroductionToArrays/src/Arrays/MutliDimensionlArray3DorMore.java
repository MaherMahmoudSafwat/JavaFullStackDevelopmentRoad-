package Arrays;

public class MutliDimensionlArray3DorMore {

	// 3. Multidimensional Array
	// A multidimensional array is an array with more than two dimensions. You can create 3D, 4D, etc., arrays.
	public static void main(String[] args) {
	        // Declare and initialize a 3D array (2 layers, 3 rows, 2 columns)
	        int[][][] cube = {
	            {
	                {1, 2},
	                {3, 4},
	                {5, 6}
	            },
	            {
	                {7, 8},
	                {9, 10},
	                {11, 12}
	            }
	        };

	        // Access and print a specific element in a 3D array
	        System.out.println("Element at layer 0, row 2, column 1: " + cube[0][2][1]);  // Output: 6
	        System.out.println("Element at layer 1, row 1, column 0: " + cube[1][1][0]);  // Output: 9

	        // Loop through the 3D array using nested loops
	        for (int i = 0; i < cube.length; i++) {
	            for (int j = 0; j < cube[i].length; j++) {
	                for (int k = 0; k < cube[i][j].length; k++) {
	                    System.out.print(cube[i][j][k] + " ");  // Print each element
	                }
	                System.out.println();  // New line after each row
	            }
	            System.out.println();  // New line after each layer
	        }
	}
}
