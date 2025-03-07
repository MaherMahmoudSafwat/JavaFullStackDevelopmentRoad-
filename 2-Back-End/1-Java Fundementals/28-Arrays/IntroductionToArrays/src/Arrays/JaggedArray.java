package Arrays;

public class JaggedArray {
	// 4. Jagged Array (Array of arrays)
	// A jagged array is an array where the inner arrays can have different lengths. It's an array of arrays of different sizes.
	public class JaggedArrayExample {

	    public static void main(String[] args) {
	        // Declare and initialize a jagged array
	        int[][] jaggedArray = new int[3][];  // 3 rows with variable column sizes

	        // Initialize each row with different sizes
	        jaggedArray[0] = new int[]{1, 2};     // Row 0 has 2 columns
	        jaggedArray[1] = new int[]{3, 4, 5};  // Row 1 has 3 columns
	        jaggedArray[2] = new int[]{6};        // Row 2 has 1 column

	        // Access and print elements from the jagged array
	        System.out.println("Element at row 0, column 1: " + jaggedArray[0][1]);  // Output: 2
	        System.out.println("Element at row 1, column 2: " + jaggedArray[1][2]);  // Output: 5

	        // Loop through the jagged array
	        for (int i = 0; i < jaggedArray.length; i++) {
	            for (int j = 0; j < jaggedArray[i].length; j++) {
	                System.out.print(jaggedArray[i][j] + " ");  // Print each element
	            }
	            System.out.println();  // New line after each row
	        }
	    }
	}

}
