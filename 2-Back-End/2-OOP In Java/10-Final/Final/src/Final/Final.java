package Final;

public class Final {
    public static void main(String[] args) {
        // Final Reference
        final A A1 = new A(4); // Creates a final reference A1 to an object of class A.
        // A1 = new A(5); // This is not allowed. A1 is a final reference and cannot be reassigned.
    }
}

class A {
    final int NUM1 = 5; // Final instance variable NUM1 is initialized at declaration, can't be changed after this.
    final int NUM2; // Final instance variable NUM2, but needs to be initialized in the constructor or an initializer block.
    final int NUM3; // Final instance variable NUM3, initialized in the constructor.
    final static int NUM4; // Final static variable NUM4, needs to be initialized in a static block or at declaration.
    final int NUM5; // Final instance variable NUM5, initialized in the constructor.
    final int NUM6; // Final instance variable NUM6, initialized in the constructor.
    int NUM7; // Non-final instance variable, can be reassigned.

    {
        NUM2 = 5; // Instance initializer block. Initializes NUM2. Since NUM2 is final, it can only be assigned once.
    }

    static {
        NUM4 = 5; // Static initializer block. Initializes NUM4. Static final variables can only be initialized here or at declaration.
    }

    A(int NUM3) {
        this.NUM3 = NUM3; // Initializes NUM3 with the constructor parameter. This is necessary for final variables.
        NUM5 = 0; // Initializes NUM5. It must be initialized inside the constructor because it's final.
        NUM6 = 0; // Initializes NUM6. Like NUM5, must be initialized inside the constructor because it's final.
    }

    // Final parameter
    A(final int n1, int n2) {
        // n1 = 9; // This is not allowed. n1 is a final parameter and cannot be reassigned.
        this.NUM3 = 0; // Initializes NUM3. Final parameters can be used but cannot be changed.
        NUM7 = n2; // Initializes NUM7. Since NUM7 is not final, it can be reassigned.
        NUM7 = 0; // Reassigns NUM7 (allowed, not final).
        NUM5 = n1; // Initializes NUM5 with the final parameter n1. Once assigned, it cannot be changed.
        NUM6 = n2; // Initializes NUM6 with the n2 parameter. Final variables need to be assigned only once.
    }

    // Final methods can't be overridden.
    final public void display() {
        System.out.println("This method can't be overridden"); // The method is marked as final, so subclasses can't override it.
    }
}

// Final class can't be inherited.
final class B extends A {
    B(int NUM1) {
        super(NUM1); // Calls the superclass (A) constructor to initialize NUM3.
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
