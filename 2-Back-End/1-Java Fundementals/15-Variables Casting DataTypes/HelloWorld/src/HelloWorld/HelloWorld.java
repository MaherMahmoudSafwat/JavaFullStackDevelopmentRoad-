package HelloWorld;

public class HelloWorld 
{
	public static void main(String [] args)
	{
		  // Declare a primitive variable 'num1' of type 'int' and initialize it with value 5.
        // 'int' is a primitive data type used to store whole numbers.
        int num1 = 5;

        // Declare a primitive variable 'num2' of type 'long' and initialize it with value 120.
        // 'long' is a primitive data type used to store larger whole numbers (64 bits compared to 'int' which is 32 bits).
        long num2 = 120;

        // Declare a primitive variable 'num3' of type 'double' and initialize it with value 379.
        // 'double' is a primitive data type used to store decimal numbers (64-bit floating point values).
        double num3 = 379;

        // **Explicit Casting**: Here, the value of 'num1' (which is of type 'int') is assigned to 'num2' (which is of type 'long').
        // This is allowed because 'long' can hold larger values than 'int'. 
        // Java automatically converts the smaller 'int' value to the larger 'long' type in this case.
        num2 = num1; // This is **allowed** because 'long' can hold all values of 'int', so no data loss occurs.

        /*Implicit Casting**: In this case, we cannot automatically assign a 'double' (num3) to an 'int' (num1), because 'double' has a larger range and 
       	  can contain fractional parts, while 'int' only holds whole numbers.*/
        // The code below will not work and will throw a compilation error:
        // num1 = num3;  // This is **NOT allowed** because a 'double' contains fractional values and cannot be implicitly converted to 'int' without losing information.

        // **Explicit Casting**: To assign the 'double' value of 'num3' to the 'int' variable 'num1', we need to explicitly cast the 'double' value to 'int'.
        // This will truncate (remove) the decimal part of the 'double' and only store the whole number part in 'num1'.
        // In this case, 'num3' is 379 (a whole number), so 'num1' will be assigned 379.
        num1 = (int) num3; // Explicitly casting the 'double' to 'int'. The fractional part (if any) would be discarded.

        // Finally, print the value of 'num1'.
        // The value of 'num1' is now 379 after the explicit casting from 'double' to 'int'.
        System.out.println(num1); // This will print: 379
	}
}
