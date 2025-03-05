package Operators;

public class InstanceOf {
	public static void main(String[] args) {

		// `instanceof` is a keyword used to check if an object is an instance of a specific class or interface.
		// In this case, `M` is a String object, and we are checking if `M` is an instance of `java.lang.String`.

		String M = "Muhammed";  // M is initialized with a String literal "Muhammed".

		// `instanceof` checks whether the object `M` is an instance of the class `java.lang.String`.
		// Since `M` is a String object, the condition `M instanceof java.lang.String` will evaluate to true.

		if (M instanceof java.lang.String)
		{  // Since `M` is an instance of `String`, this condition will be true.

		    // If the condition is true, the code inside this block will execute.
		    System.out.println("M is a String");  // This line will print "M is a String" to the console.
		}	
	}
}
