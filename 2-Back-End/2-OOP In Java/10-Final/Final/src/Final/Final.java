package Final;

public class Final {
    public static void main(String[] args) {
        // Final Reference
        final A A1 = new A(4); // Creates a final reference A1 to an object of class A.
        // A1 = new A(5); // This is not allowed. A1 is a final reference and cannot be reassigned.
    }
}

class A {
    final int Num1 = 5; // Final instance variable initialized at declaration.
    final int Num2; // Final instance variable initialized in an instance initializer block.
    final int Num3; // Final instance variable initialized in a constructor.
    final static int Num4; // Final static variable initialized in a static block.
    final int Num5; // Final instance variable initialized in a constructor.
    final int Num6; // Final instance variable initialized in a constructor.
    int Num7; // Regular instance variable.

    {
        Num2 = 5; // Instance initializer block. Initializes Num2.
    }

    static {
        Num4 = 5; // Static initializer block. Initializes Num4.
    }

    A(int Num3) {
        this.Num3 = Num3; // Initializes Num3 with the constructor parameter.
        Num5 = 0; // Initializes Num5.
        Num6 = 0; // Initializes Num6.
    }

    // final parameter
    A(final int n1, int n2) {
        // n1 = 9; // This is not allowed. n1 is a final parameter and cannot be reassigned.
        this.Num3 = 0; // Initializes Num3.
        Num7 = n2; // Initializes Num7.
        Num7 = 0; // Reassigns Num7 (allowed, not final).
        Num5 = n1; // Initializes Num5 with the final parameter n1.
        Num6 = n2; // Initializes Num6 with the n2 parameter.
    }

    // Final variables can be initialized at the same line or inside block or in constructor.
    // Final static variables can be initialized only on the same line or in a static block.
    // Final Methods can't be overridden.
    final public void display() {
        System.out.println("This method can't be overridden");
    }
}

// Final class can't be inherited.
final class B extends A {
    B(int Num1) {
        super(Num1); // Calls the superclass (A) constructor.
    }
    /*
     * @Override public void display() {
     * * }
     */// This is not allowed. Final methods cannot be overridden.
}

/*
 * class C extends B {
 * * }
 */// This is not allowed as final class can't be inherited.