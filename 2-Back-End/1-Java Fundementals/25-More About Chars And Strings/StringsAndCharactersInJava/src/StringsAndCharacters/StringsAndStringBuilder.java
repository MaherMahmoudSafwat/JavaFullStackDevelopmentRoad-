package StringsAndCharacters;

public class StringsAndStringBuilder 
{
	public class Main {
	    public static void main(String[] args) {

	        // Create a String object 's1' and assign it a value "Muhammed Essa"
	        // Strings are immutable in Java, meaning their value cannot be changed once created.
	        String s1 = "Muhammed Essa";  // The string "Muhammed Essa" will be stored in the String Pool.
	        //char i = s1[0];This is Not Allowed.
	        
	        // Create a StringBuilder object 'mn' initialized with the value of 's1'
	        // StringBuilder is mutable, meaning its value can be changed (e.g., appending).
	        StringBuilder mn = new StringBuilder(s1);  // The StringBuilder internal buffer will now hold "Muhammed Essa".
	        
	        // Append " Hameed" to the StringBuilder object 'mn'
	        // StringBuilder is more efficient for modifications (like appending) compared to String,
	        // as it doesn't create new objects with each modification.
	        mn.append(" Hameed");  // The internal buffer of the StringBuilder is updated to "Muhammed Essa Hameed".
	        
	        // Print the value of the StringBuilder object 'mn'
	        // The StringBuilder's content is converted to a String using its toString() method before printing.
	        // The result will be "Muhammed Essa Hameed".
	        System.out.println(mn);  // Outputs: Muhammed Essa Hameed

	        // Create a new String object 'S2' with the default constructor (empty string)
	        // This creates a new String object in heap memory with an empty string ("").
	        String S2 = new String();  // This creates a new String object with value "" (empty string).

	        // To confirm the content of S2, we can print it
	        System.out.println(S2);  // Outputs: (an empty line because S2 contains an empty string).
	    }
	}
}
