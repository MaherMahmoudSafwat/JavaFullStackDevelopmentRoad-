package Final;

public class Final {
    public static void main(String[] args) {
        // Final Reference
        final A A1 = new A(4); // Creates a final reference A1 to an object of class A.
        // A1 = new A(5); // This is not allowed. A1 is a final reference and cannot be reassigned.
    }
}

class A {
    final int Num1 = 5; // Final instance variable Num1 is initialized at declaration, can't be changed after this.
    final int Num2; // Final instance variable Num2, but needs to be initialized in the constructor or an initializer block.
    final int Num3; // Final instance variable Num3, initialized in the constructor.
    final static int Num4; // Final static variable Num4, needs to be initialized in a static block or at declaration.
    final int Num5; // Final instance variable Num5, initialized in the constructor.
    final int Num6; // Final instance variable Num6, initialized in the constructor.
    int Num7; // Non-final instance variable, can be reassigned.

    {
        Num2 = 5; // Instance initializer block. Initializes Num2. Since Num2 is final, it can only be assigned once.
    }

    static {
        Num4 = 5; // Static initializer block. Initializes Num4. Static final variables can only be initialized here or at declaration.
    }

    A(int Num3) {
        this.Num3 = Num3; // Initializes Num3 with the constructor parameter. This is necessary for final variables.
        Num5 = 0; // Initializes Num5. It must be initialized inside the constructor because it's final.
        Num6 = 0; // Initializes Num6. Like Num5, must be initialized inside the constructor because it's final.
    }

    // Final parameter
    A(final int n1, int n2) {
        // n1 = 9; // This is not allowed. n1 is a final parameter and cannot be reassigned.
        this.Num3 = 0; // Initializes Num3. Final parameters can be used but cannot be changed.
        Num7 = n2; // Initializes Num7. Since Num7 is not final, it can be reassigned.
        Num7 = 0; // Reassigns Num7 (allowed, not final).
        Num5 = n1; // Initializes Num5 with the final parameter n1. Once assigned, it cannot be changed.
        Num6 = n2; // Initializes Num6 with the n2 parameter. Final variables need to be assigned only once.
    }

    // Final methods can't be overridden.
    final public void display() {
        System.out.println("This method can't be overridden"); // The method is marked as final, so subclasses can't override it.
    }
}

// Final class can't be inherited.
final class B extends A {
    B(int Num1) {
        super(Num1); // Calls the superclass (A) constructor to initialize Num3.
    }
    /*
     * @Override public void display() {
     *    // This is not allowed. Final methods cannot be overridden.
     * }
     */
}

// A class C extending B would be illegal because B is a final class and can't be inherited.
 /*
 * class C extends B {
 *    // This is not allowed as final class can't be inherited.
 * }
 */
