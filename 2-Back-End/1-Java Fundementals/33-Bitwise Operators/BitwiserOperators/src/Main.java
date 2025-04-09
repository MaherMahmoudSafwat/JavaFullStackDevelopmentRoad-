//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        int x = 5; // Binary: 0101
        int y = 3; // Binary: 0011

        // 1. Bitwise AND (&)
        // Performs AND operation on each bit
        // 0101 (5)
        // & 0011 (3)
        // = 0001 (1)
        int Result = x & y; //0001 ---> Output: 1
        System.out.println(Result);

        // 2. Bitwise OR (|)
        // Performs OR operation on each bit
        // 0101 (5)
        // | 0011 (3)
        // = 0111 (7)
        Result = x | y; //0111 ---> Output: 7
        System.out.println(Result);

        // 3. Bitwise XOR (^)
        // Performs XOR operation (1 if bits are different)
        // 0101 (5)
        // ^ 0011 (3)
        // = 0110 (6)
        Result = x ^ y;//0110 ---> Output: 6
        System.out.println(Result);

        // Inverts all bits (unary operator)
        // ~0101 (5)
        // = 1010 (in 32-bit: 11111111111111111111111111111010) = -6
        Result = ~x; //1010 ---> Output: -6
        System.out.println(Result);

        //To print 10 not -6 and to ignore the sign.
        Result = ~x & 0b1111;
        System.out.println(Result);

        // 5. Left Shift (<<)
        // Shifts bits left, filling with 0
        // 0101 (5) << 1 = 1010 (10)
        //x << n is equivalent to multiplying x by 2ⁿ (if no overflow occurs).
        Result = x << 1;  //x * 2^1;
        System.out.println(Result);

        Result = x << 2;  //x * 2^2;
        System.out.println(Result);

        Result = x << 3;  //x * 2^3;
        System.out.println(Result);

        Result = (x << 2) & 0b1111;
        System.out.println(Result); //Result: ---> 4;

        // 6. Right Shift (>>) - Signed right shift
        // Shifts bits right, preserving sign bit
        // 0101 (5) >> 1 = 0010 (2)
        // For negative numbers: preserves sign bit
        Result = x >> 1; //x / (2^n)
        System.out.println(Result);//0010 ---> Output: 2;

        // 7. Unsigned Right Shift (>>>)
        // Shifts bits right, always filling with 0
        // For positive numbers, same as >>
        // For negative numbers, doesn't preserve sign
        x = -5;
        Result = x >>> 1;
        System.out.println(Result);

        Result = x << 1;
        System.out.println(Result);

        /*
        Full List of Compound Bitwise Operators
        Operator	Example	Equivalent To	Description
        &=	x &= y	x = x & y	Bitwise AND, then assign.
        `	=`	`x	= y`	`x = x	y`	Bitwise OR, then assign.
        ^=	x ^= y	x = x ^ y	Bitwise XOR, then assign.
        <<=	x <<= n	x = x << n	Left shift, then assign.
        >>=	x >>= n	x = x >> n	Signed right shift, then assign.
        >>>=	x >>>= n	x = x >>> n	Unsigned right shift, then assign.
         */
    }
}
