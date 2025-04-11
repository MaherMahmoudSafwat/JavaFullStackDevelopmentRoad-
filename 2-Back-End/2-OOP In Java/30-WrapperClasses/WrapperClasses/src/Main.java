//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        // Primitive int initialization
        int X = 5;

        // Wrapper class examples with auto-boxing
        // Integer wrapper class
        Integer I = X;
        System.out.println(Integer.MIN_VALUE);  // Minimum value an Integer can hold
        System.out.println(Integer.MAX_VALUE);  // Maximum value an Integer can hold

        // Byte wrapper class (requires explicit casting)
        Byte B = (byte) X;
        System.out.println(Byte.MIN_VALUE);  // Minimum value a Byte can hold
        System.out.println(Byte.MAX_VALUE);  // Maximum value a Byte can hold

        // Short wrapper class (requires explicit casting)
        Short S = (short) X;
        System.out.println(Short.MIN_VALUE);  // Minimum value a Short can hold
        System.out.println(Short.MAX_VALUE);  // Maximum value a Short can hold

        // Long wrapper class
        Long L = (long) X;
        System.out.println(Long.MIN_VALUE);  // Minimum value a Long can hold
        System.out.println(Long.MAX_VALUE);  // Maximum value a Long can hold

        // Floating-point wrapper classes
        System.out.println(Float.MIN_VALUE);  // Minimum positive value a Float can hold
        System.out.println(Float.MAX_VALUE);  // Maximum value a Float can hold
        System.out.println(Double.MIN_VALUE); // Minimum positive value a Double can hold
        System.out.println(Double.MAX_VALUE); // Maximum value a Double can hold

        // Character wrapper class
        System.out.println(Character.MIN_VALUE);  // Minimum value (0) a Character can hold
        System.out.println(Character.MAX_VALUE);  // Maximum value (65535) a Character can hold

        // Boolean wrapper class
        System.out.println(Boolean.TRUE);   // Boolean object representing true
        System.out.println(Boolean.FALSE);  // Boolean object representing false
        System.out.println(Boolean.TYPE);   // The Class object representing primitive boolean

        // Object demonstration
        Object z = 5;  // Auto-boxing: int to Integer, then upcast to Object
        System.out.println(X == (int)z);  // Unboxing and comparison: true

        // Boxing and unboxing demonstration
        int Y = 10;          // Primitive int
        Integer A = Y;       // Auto-boxing: primitive int to Integer object
        Y = A;               // Auto-unboxing: Integer object back to primitive int

    }
}