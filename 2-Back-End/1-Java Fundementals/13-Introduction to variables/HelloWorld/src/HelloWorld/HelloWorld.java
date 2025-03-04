package HelloWorld;

public class HelloWorld {
	public static void main(String[] args)
	{
		// Primitive Data Types in Java
		// Primitive Data Types in Java:
        // Primitive data types are the basic building blocks of data manipulation in Java.
        // They represent raw values and are directly supported by the language.
		
        // byte: Represents a small integer value (8 bits).
        byte B1 = 5;  // B1 can store values between -128 and 127.
        System.out.println("Byte: " + B1);

        // short: Represents a larger integer value (16 bits).
        short S1 = 16000;  // S1 can store values between -32,768 and 32,767.
        System.out.println("Short: " + S1);

        // int: Represents a standard integer value (32 bits).
        int I1 = 3000000;  // I1 can store values between -2^31 and 2^31-1 (approximately -2 billion to +2 billion).
        System.out.println("Int: " + I1);

        // long: Represents a very large integer value (64 bits).
        //If you didnt but L at the end it will convert it to long then store it in Long and this is two steps so you better use L at the end by default.
        long L1 = 12345678905L;  // L1 can store values much larger than int.
        // The 'L' suffix is necessary because, by default, numbers are treated as int.
        // Without the 'L', the number would be treated as an int, causing overflow for large values.
        System.out.println("Long: " + L1);

        // float: Represents a single-precision floating-point number (32 bits).
        float F1 = 5.3f;  // F1 can store decimal numbers with less precision. The 'f' suffix indicates it's a float literal.
        // The 'f' is required because a decimal number is treated as a double by default.
        System.out.println("Float: " + F1);

        // double: Represents a double-precision floating-point number (64 bits).
        double D1 = 56789.12345667890;  // D1 can store decimal numbers with higher precision.
        // No suffix is needed for double, as it's the default type for decimal literals in Java.
        System.out.println("Double: " + D1);

        // boolean: Represents a true or false value.
        boolean b1 = true;  // b1 can hold only two values: true or false.
        System.out.println("Boolean: " + b1);

        // char: Represents a single 16-bit Unicode character.
        char C1 = 'A';  // C1 can hold a single character (e.g., 'A', '1', or '$').
        System.out.println("Char: " + C1);	
        
        //------------------------------------------------------------------------------------
        //By Default Values 
        //byte,short,int,long,float,double ---> 0
        //boolean ---> false
        //char ---> '\u0000\'
        //String ---> null
        int Number1;
        //System.out.println(Number1);It is not allowed because it Should be intialized.
        }
}
