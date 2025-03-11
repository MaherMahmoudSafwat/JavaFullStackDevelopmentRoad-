package Abstraction;

// ========================================================
// Abstract Classes vs Interfaces in Java (Extended with Marker Interfaces)
// ========================================================

/* 
   In this extended explanation, we'll dive deeper into abstract classes and interfaces,
   including the concept of **Marker Interfaces**, **Functional Interfaces**, and the possible members in both.

   - **Marker Interface**: A marker interface is an interface that has no methods or fields.
     It is used to indicate that a class has some special property or behavior.
     A common use case is for identifying classes that can be serialized or cloned.
   - **Functional Interface**: A functional interface is an interface that has exactly one abstract method. 
     It can have multiple default or static methods. These interfaces are used primarily with lambda expressions.
   - We'll also explore how abstract classes and interfaces can have static members, constants, and methods.

   The following code demonstrates both interfaces and abstract classes with different features.
*/

// ========================================================
// Marker Interface Definition: Serializable (Example of Marker Interface)
// ========================================================

/*
   A marker interface typically has no methods or fields.
   It's used for tagging purposes only, to signify that a class has a specific property.
   Example: The `Serializable` interface in Java is used to mark a class as capable of being serialized.
*/

interface Serializable {}

// ========================================================
// Functional Interface Definition: Animal (Extended with Functional Interface Example)
// ========================================================

interface Animal {

    // Abstract method (must be implemented by classes that implement this interface)
    void sound();

    // Default method (with body), can be inherited and optionally overridden
    default void sleep() {
        System.out.println("This animal is sleeping.");
    }

    // Static method (can be called directly on the interface itself)
    static void breathe() {
        System.out.println("This animal is breathing.");
    }

    // Constant (public, static, and final by default)
    final static int NUMBER_OF_LEGS = 4;  // All animals in this interface are assumed to have 4 legs

    // Abstract method (can be overridden by implementing classes)
    void eat();
}
//========================================================
//Functional Interface Definition: Animal (Extended with Functional Interface Example)
//========================================================

/* 
A functional interface is an interface with exactly one abstract method.
- It can have any number of default or static methods.
- It is often used as a target type for lambda expressions.

This Animal interface is a functional interface because it has only one abstract method.
*/
@FunctionalInterface
interface EatableFoodByAnimals 
{
    // Abstract method (can be overridden by implementing classes)
    void eat();
}

// ========================================================
// Abstract Class Definition: AnimalBase (Extended)
// ========================================================

/* 
   The AnimalBase abstract class provides more detailed features:
   - Abstract methods (`sound()`).
   - Concrete methods (`eat()`).
   - Constructors.
   - Member variables.
*/

abstract class AnimalBase {
    String name;  // Field to store the name of the animal

    // Abstract method (no body), must be implemented by any subclass
    abstract void sound();

    // Concrete method (with body), can be inherited by subclasses
    public void eat() {
        System.out.println("This animal eats food.");
    }

    // Constructor (can be called by subclasses)
    public AnimalBase(String name) {
        this.name = name;
    }

    // Concrete method (with body)
    void displayInfo() {
        System.out.println("This is a " + name);
    }
}

// ========================================================
// Concrete Class Definition: Dog (Implemented Interface + Extended Abstract Class)
// ========================================================

/* 
   The Dog class implements the Animal interface and extends the AnimalBase abstract class.
   - It must implement the abstract method `sound()` from the Animal interface.
   - It can override the default method `sleep()` from the interface.
   - It inherits the concrete method `eat()` and `displayInfo()` from the abstract class.
*/
class Dog extends AnimalBase implements Animal, Serializable {

    // Constructor that passes the name to the superclass constructor
    public Dog(String name) {
        super(name);  // Calling constructor of AnimalBase to initialize name
    }

    // Implementation of abstract method sound() from both AnimalBase and Animal interface
    @Override
    public void sound() {
        System.out.println("Woof!");
        int x = Animal.NUMBER_OF_LEGS; // Accessing constant from Animal interface
    }

    // Optionally, override default method sleep() from Animal interface
    @Override
    public void sleep() {
        System.out.println("The dog is sleeping peacefully.");
    }

    // Calling inherited method displayInfo() from AnimalBase
    public void displayDetails() {
        displayInfo();  // Displays name of the animal
        sound();        // Displays sound of the dog
    }

    // Adding a method that has the same name as the interface method
    @Override
    public void eat() {
        System.out.println("The dog is eating its food.");
    }

    // Static method cannot be overridden in a class implementing an interface
    /*
     * @Override
     * public static void breathe() {
     *     System.out.println("Dog breathes in its own way");
     * } 
     * This will cause a compile-time error because static methods cannot be overridden in Java.
     */
}

// ========================================================
// Main Method to Test the Code
// ========================================================

/* 
   This main method demonstrates how the Dog class works, showing both inherited and implemented behaviors.
   It also shows how static methods and constants from interfaces can be accessed.
*/
public class Abstraction {
    public static void main(String[] args) {
        // Create an object of the Dog class with the name "Buddy"
        Dog dog = new Dog("Buddy");

        // Call methods implemented in Dog class
        dog.sound();   // Outputs: Woof!
        dog.eat();     // Outputs: The dog is eating its food.
        dog.sleep();   // Outputs: The dog is sleeping peacefully.
        dog.displayDetails();  // Outputs: This is a Buddy / Woof!

        // Call static method from the interface Animal
        Animal.breathe();  // Outputs: This animal is breathing.

        // Access constant from the interface Animal
        System.out.println("Number of legs: " + Animal.NUMBER_OF_LEGS); // Outputs: Number of legs: 4

        // Check if dog object is instance of Serializable (marker interface)
        if (dog instanceof Serializable) {
            System.out.println("Yes, the class is Serializable.");
        }
    }
}

/*
   Output of the above code:
   Woof!
   The dog is eating its food.
   The dog is sleeping peacefully.
   This is a Buddy
   Woof!
   This animal is breathing.
   Number of legs: 4
   Yes, the class is Serializable.
*/

// ========================================================
// Summary
// ========================================================

/* 
   Key Points:

   **Marker Interface**:
   - A marker interface is an interface with no methods or fields, used purely for tagging classes.
   - In this example, `Serializable` is a marker interface that can be used to mark a class as serializable.
   
   **Functional Interface**:
   - A functional interface is an interface that has exactly one abstract method.
   - The `EatableFoodByAnimals` interface is annotated with `@FunctionalInterface`, indicating that it is a functional interface.
   - It can have multiple default or static methods but must have exactly one abstract method. Functional interfaces are commonly used with lambda expressions.
   - Example usage of functional interfaces: Lambda expressions can be used to instantiate functional interfaces.

   **Interface Features**:
   - **Abstract Methods**: Must be implemented by any class that implements the interface.
   - **Default Methods**: Can have a body and are inherited by implementing classes, can be overridden.
   - **Static Methods**: Can be called directly on the interface itself (without needing an instance).
   - **Constants**: All fields in an interface are implicitly `public static final`.

   **Abstract Class Features**:
   - **Abstract Methods**: Must be implemented by any subclass.
   - **Concrete Methods**: Can have a body and can be inherited by subclasses.
   - **Constructors**: Can be used to initialize fields when the class is extended.
   - **Member Variables**: Can have instance variables to store data.

   **Abstraction**:
   - Abstract classes and interfaces help implement abstraction by defining a contract that must be followed by subclasses and implementing classes.
   - Abstract classes allow some behavior to be shared, while interfaces define a set of methods that must be implemented.

   The above code demonstrates the features of both interfaces and abstract classes, along with the usage of marker interfaces, 
   static methods, constants, constructors, and inheritance.
*/


