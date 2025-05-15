// Base class
class A {
    public void Show() {
        System.out.println("In Show A");
    }
    public void Processing(A a)
    {
        System.out.println("A");
    }
    public void  Processing(B b)
    {
        System.out.println("B");
    }
}

// Derived class
class B extends A {
    // Method overriding - runtime polymorphism
    @Override
    public void Show() {
        System.out.println("In Show B");
    }

    // Special method only available in class B
    public void BMethod() {
        System.out.println("This is a BMethod");
    }
}

public class Main {
    public static void main(String[] args) {
        // ========== UPCASTING SCENARIO ==========
        // Implicit upcasting: B object treated as A reference
        // This is always safe because every B is an A (inheritance)
        A Obj = new B();  // Upcasting happens automatically

        Obj.Show();       // Output: "In Show B" (runtime polymorphism)
        Obj.Processing(Obj);
        Obj.Processing((B)Obj);

        // Obj.BMethod(); // COMPILE ERROR:
        // Can't access B-specific methods through A reference
        // Even though the actual object is B, the reference type is A

        // ========== DOWNCASTING SCENARIO ==========
        // Explicit downcasting: Converting A reference back to B
        // This is potentially unsafe - we must ensure the object is actually B
        ((B) Obj).BMethod(); // Safe downcasting - we know Obj is actually B
        // Output: "This is a BMethod"

        Obj = (B)Obj; // Another way to downcast (redundant in this case)
        // After this, Obj is still of reference type A due to Java's reference semantics

        // ========== NORMAL OBJECT CREATION ==========
        B Obj1 = new B(); // No casting involved
        Obj1.Show();      // Output: "In Show B"
        Obj1.BMethod();   // Output: "This is a BMethod"

        // ========== EXPLICIT UPCASTING ==========
        // Explicit upcasting (redundant - Java does this automatically)
        Obj = (A)new B(); // Same as A Obj = new B();
        Obj.Show();       // Output: "In Show B"

        // ========== UNSAFE DOWNCASTING SCENARIO ==========
        A aObj = new A();
        try {
            B bObj = (B)aObj; // ClassCastException at runtime!
            // This fails because aObj is actually A, not B
        } catch (ClassCastException e) {
            System.out.println("Downcasting failed: " + e.getMessage());
        }
    }
}

