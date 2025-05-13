/*
 * ===== JVM CLASS LOADING DEMONSTRATION =====
 *
 * This program demonstrates:
 * 1. How classes are loaded by the JVM
 * 2. Memory allocation for classes and objects
 * 3. The difference between static and instance members
 */

/*
 * ===== CLASS LOADER MECHANISM =====
 *
 * When JVM needs to use a class:
 * 1. LOADING PHASE:
 *    - ClassLoader locates and reads Person.class bytecode
 *    - Application ClassLoader (for user classes) searches CLASSPATH
 *    - Bytes are verified and stored in Metaspace (native memory)
 *
 * 2. LINKING PHASE:
 *    - Verification: Checks bytecode validity
 *    - Preparation: Allocates memory for static variables
 *    - Resolution: Converts symbolic references to direct references
 *
 * 3. INITIALIZATION PHASE:
 *    - Static variables are assigned values
 *    - Static blocks are executed (in order of appearance)
 *    - Class is now ready for use
 */

class Person {
    // Instance fields - stored in Heap memory per object
    String Name;            // Each Person object gets its own copy
    int PhoneNumber;        // Each Person object gets its own copy

    // Static field - stored in Metaspace (shared by all instances)
    static String Address;

    /*
     * STATIC INITIALIZATION BLOCK
     * - Executed exactly once: during class initialization
     * - Runs when class is first loaded (before any instances created)
     * - Can initialize static fields
     */
    static {
        System.out.println("[Metaspace] Static block executing - class being initialized");
        Address = "Giza";   // Static initialization happens here
    }

    /*
     * CONSTRUCTOR
     * - Runs each time 'new Person()' is called
     * - Initializes instance fields in Heap memory
     */
    Person() {
        Name = "";
        PhoneNumber = 0;
        System.out.println("[Heap] Constructor creating new Person instance");
    }

    // Instance method - operates on object data in Heap
    public void Show() {
        System.out.println("Person Details: " + Name + " " + PhoneNumber + " " + Address);
    }
}

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        /*
         * EXPLICIT CLASS LOADING
         * Using Class.forName() forces:
         * 1. Class loading if not already loaded
         * 2. Static initialization (static blocks run)
         * 3. Creates Class object in Heap for reflection
         */
        System.out.println("=== Triggering class loading ===");
        Class.forName("Person"); // Loads class, runs static block

        /*
         * OBJECT CREATION PHASE
         * Now that class is loaded, we can create instances
         * Each 'new' allocates Heap memory for instance fields
         */
        System.out.println("\n=== Creating first instance ===");
        Person p1 = new Person();
        p1.Name = "Ahmed";
        p1.PhoneNumber = 012345; // Note: leading 0 makes this octal (5349 decimal)

        System.out.println("\n=== Creating second instance ===");
        Person p2 = new Person();
        p2.Name = "Mohamed";
        p2.PhoneNumber = 1234567;

        /*
         * DEMONSTRATING MEMORY USAGE
         * - Both instances share static Address from Metaspace
         * - Each has its own Name/PhoneNumber in Heap
         */
        System.out.println("\n=== Displaying results ===");
        p1.Show(); // Output: Ahmed 5349 Giza
        p2.Show(); // Output: Mohamed 1234567 Giza

        /*
         * MEMORY LAYOUT SUMMARY:
         *
         * NATIVE MEMORY (JVM Internal)
         * - Metaspace:
         *   • Person class metadata
         *   • Static String Address = "Giza"
         *   • Method bytecode (Show(), constructor)
         *
         * HEAP MEMORY (Object Storage)
         * - Class<Person> object (for reflection)
         * - p1 instance: {Name="Ahmed", PhoneNumber=5349}
         * - p2 instance: {Name="Mohamed", PhoneNumber=1234567}
         *
         * STACK MEMORY (Thread Execution)
         * - main() method frame
         * - p1 and p2 reference variables
         */
    }
}


