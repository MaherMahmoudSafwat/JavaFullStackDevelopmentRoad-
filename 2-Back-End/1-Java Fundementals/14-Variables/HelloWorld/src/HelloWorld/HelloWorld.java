package HelloWorld;
import java.lang.Integer;
public class HelloWorld 
{
	public static void main(String [] args)
	{
		// Primitive Data Types:
        // - They are the basic building blocks of data in Java.
        // - They directly represent values and are not objects.
        // - They are predefined by the language and are stored directly in memory.
        // - Examples: byte, short, int, long, float, double, boolean, char.

        // Example of a primitive data type:
        int num = 42;  // Primitive type (int), used for storing whole numbers.
        System.out.println("Primitive Type (int): " + num);

        // Complex (Non-Primitive) Data Types:
        // - Complex types (also known as reference types) are more advanced types.
        // - They refer to objects that are instances of classes.
        // - They are stored as references (pointers) in memory, meaning they point to the memory location of the data.
        // - Examples: String, arrays, classes, interfaces, etc.
        
        // Example of complex data types:
        String S1 = "Maher Mahmoud Safwat";  // String is a reference type (non-primitive).
        // In Java, String is a class, and when we use a String literal, it creates an object in memory.
        // The value "Maher Mahmoud Safwat" is stored as a reference to a memory location.
        System.out.println("Complex Type (String): " + S1);

        String S2 = new String("Java");  // String created using the new keyword, explicitly creating an object.
        // This is an example of how a complex data type (String) can also be instantiated using 'new'.
        System.out.println("Complex Type (String with 'new'): " + S2);
        //-----------------------------------------------------------------
        // int num1 = 5;
        // Declare a primitive data type variable 'num1' of type int.
        // The value 5 is assigned to the variable 'num1'. 'int' is a primitive data type that stores whole numbers.
        int num1 = 5;

        // Integer IntNumber = 135;
        // Declare an Integer object named 'IntNumber' and initialize it with the value 135.
        // 'Integer' is a wrapper class for the primitive type 'int'. 
        // This means that 'IntNumber' holds an object that represents the value 135.
        // The 'Integer' class is part of Java's wrapper classes, which wrap the primitive types into objects.
        Integer IntNumber = 135;

        // num1 = IntNumber;
        // **Autoboxing**: Java automatically converts the Integer object 'IntNumber' into the primitive int type.
        // This process is called autoboxing, where the Integer object is automatically "unboxed" to its primitive int value.
        // So, 'IntNumber' (which is an Integer object) is converted to 135 (the primitive int value) and assigned to 'num1'.
        num1 = IntNumber;  // Autounboxing is performed here.

        // num1 = IntNumber.intValue();
        // **Unboxing**: This is manual unboxing. The intValue() method of the Integer class is used to explicitly retrieve the primitive int value from the Integer object.
        // 'IntNumber.intValue()' returns the primitive int value (135) that the Integer object represents.
        // This is manual unboxing, where we explicitly call a method to get the primitive value from an object.
        num1 = IntNumber.intValue();  // Manually unboxing the Integer object to its primitive int value.

        // System.out.println(num1);
        // Output the value of num1.
        // After the above operations, num1 holds the value 135. 
        // Therefore, it will print: 135 to the console.
        System.out.println(num1); // Output: 135

        // double a = new double(123);
        // **Error**: You cannot instantiate primitive data types like double using the 'new' keyword.
        // Primitive types like 'int' and 'double' are not objects, and therefore cannot be created using 'new'.
        // The correct way to assign a value to a primitive 'double' is simply by directly assigning a value:
        // double a = 123;  // Assigns the primitive double value 123 to the variable 'a'.
        
        // Fixing the error:
        double a = 123;  // Correct: 'a' is a primitive double variable, assigned the value 123 directly.
        System.out.println(a); 
	}
}
