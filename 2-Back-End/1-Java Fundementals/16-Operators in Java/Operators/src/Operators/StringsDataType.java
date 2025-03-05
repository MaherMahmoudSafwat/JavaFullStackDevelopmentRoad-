package Operators;

public class StringsDataType 
{
	 public static void main(String[] args) {

	        // 1. String literal assignment
	        String s1 = "Hello";  // String literal "Hello" is assigned to s1
	        // When "Hello" is encountered, Java checks if it already exists in the string pool.
	        // Since it's a string literal, "Hello" will be added to the string pool (if it doesn't exist).
	        // `s1` holds a reference (memory address) to the "Hello" object in the string pool.

	        // 2. Another reference to the same string literal
	        String s2 = "Hello";  // Another reference to the same string literal "Hello"
	        // Since the string literal "Hello" already exists in the string pool, `s2` will point to the same memory location.
	        // Both s1 and s2 point to the same object in the string pool.

	        // 3. Creating a new string using the new keyword
	        String s3 = new String("Hello");  // Creates a new String object on the heap (not in the string pool)
	        // The string literal "Hello" is checked in the string pool first. If it exists, it's reused.
	        // However, since `new String("Hello")` uses the `new` keyword, a new object is created on the heap.
	        // `s3` holds a reference to this new string object on the heap, not the string pool.

	        // 4. Creating another new string with the new keyword
	        String s4 = new String("Hello");  // Creates another new String object on the heap
	        // Similar to s3, a new string object is created in the heap, even though the value is the same ("Hello").
	        // `s4` points to a different memory location in the heap.

	        // Now comparing s3 and s4 using the equals method
	        if (s3.equals(s4)) {
	            System.out.println("They match!");  // This will be printed because equals() compares the content of the strings
	        } else {
	            System.out.println("No match!");
	        }

	        // Output:
	        // They match!

	        // Explanation of what happens in memory:

	        // String Pool (in the heap):
	        //  - "Hello" is stored in the string pool (interned).
	        //  - s1 and s2 point to the same memory address where "Hello" is stored in the pool.
	        
	        // Stack (Local Variables):
	        //  - s1 -> reference to the string object in the string pool ("Hello").
	        //  - s2 -> reference to the same string object in the string pool ("Hello").
	        
	        // Heap:
	        //  - s3 -> reference to a new string object created by new String("Hello").
	        //  - s4 -> reference to another new string object created by new String("Hello").

	        // The key points:
	        // - `s1` and `s2` point to the same object in the string pool because they are referencing the same literal string.
	        // - `s3` and `s4` point to different objects in the heap, created by the `new` keyword.
	        // - String literals are stored in the string pool to save memory by reusing common strings.
	        // - Objects created with `new` are distinct and are stored separately in the heap, even if the content is the same.
	        String firstString = "This is";
	        String secondString = " a concatenated string.";
	        String thirdString = firstString+secondString;
	        System.out.println(thirdString);
	 }
}
