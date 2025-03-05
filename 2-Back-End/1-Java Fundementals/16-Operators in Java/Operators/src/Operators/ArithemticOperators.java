package Operators;

public class ArithemticOperators {
	public static void main(String [] args)
	{
			int result = 1 + 2;
	        // result is now 3
	        System.out.println("1 + 2 = " + result);
	        //Assignment Operators
	        int original_result = result;

	        result = result - 1;
	        // result is now 2
	        System.out.println(original_result + " - 1 = " + result);
	        original_result = result;

	        result = result * 2;
	        // result is now 4
	        System.out.println(original_result + " * 2 = " + result);
	        original_result = result;

	        result = result / 2;
	        // result is now 2
	        System.out.println(original_result + " / 2 = " + result);
	        result /=2; // result = result / 2;
	        original_result = result;

	        result = result + 8;
	        // result is now 10
	        System.out.println(original_result + " + 8 = " + result);
	        original_result = result;

	        result = result % 7;
	        // result is now 3
	        System.out.println(original_result + " % 7 = " + result);
	        int i = 3;
	        i++;
	        // prints 4
	        System.out.println(i);
	        ++i;
	        // prints 5
	        System.out.println(i);
	        // prints 6
	        System.out.println(++i);
	        // prints 6
	        System.out.println(i++);
	        // prints 7
	        System.out.println(i);
	        i--;
	        // prints 4
	        System.out.println(i);
	        --i;
	        // prints 5
	        System.out.println(i);
	        // prints 6
	        System.out.println(--i);
	        // prints 6
	        System.out.println(i--);
	        // prints 7
	        System.out.println(i);
	        int Result = +1;
	        // Result is now 1
	        System.out.println(Result);

	        Result--;
	        // Result is now 0
	        System.out.println(Result);

	        Result++;
	        // Result is now 1
	        System.out.println(Result);

	        Result = -Result;
	        // Result is now -1
	        System.out.println(Result);

	        boolean success = false;
	        // false
	        System.out.println(success);
	        // true
	        System.out.println(!success);
	}
}
