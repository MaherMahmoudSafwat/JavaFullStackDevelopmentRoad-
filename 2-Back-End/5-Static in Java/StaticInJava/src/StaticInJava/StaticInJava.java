package StaticInJava;

//Java class demonstrating static methods, static variables, static blocks, and static classes

public class StaticInJava {

 // Static variable: shared by all instances of the class
 static int staticVariable = 10;  // This variable belongs to the class, not the instance

 // Instance variable: Each object will have its own copy of this variable
 int instanceVariable = 5;

 // Static method: Can be called without creating an object of the class
 // It can only directly access static variables and other static methods
 public static void staticMethod() {
     // Static method can access static variables directly
     System.out.println("Static Method Accessing Static Variable: " + staticVariable);

     // Cannot directly access instance variables in static methods
     // Uncommenting the following line will give an error:
     // System.out.println("Static Method Accessing Instance Variable: " + instanceVariable);
 }

 // Instance method: Can access both static and instance variables
 public void instanceMethod() {
     // Instance method can access both static and instance variables
     System.out.println("Instance Method Accessing Static Variable: " + staticVariable);
     System.out.println("Instance Method Accessing Instance Variable: " + instanceVariable);
 }

 // Static block: Executes once when the class is first loaded into memory
 // It is used for static initialization that is needed before any static methods or variables are accessed
 static {
     System.out.println("Static Block Executed: Initializing static variables.");
     // Static block can modify static variables
     staticVariable = 20;  // Initializing static variable here
 }

 // Static inner class: A nested class that can be accessed without an instance of the outer class
 // Static inner class can only access static members of the outer class
 static class StaticInnerClass {
     public void display() {
         // Static inner class can access static members of the outer class
         System.out.println("Static Inner Class Accessing Static Variable: " + staticVariable);
     }
 }
 //Static classes can be Inner Classes only.

 // Main method: Entry point of the program
 public static void main(String[] args) {

     // Calling static method without creating an object of StaticExample
     StaticInJava.staticMethod();

     // Creating an object of StaticExample class to call the instance method
     StaticInJava example = new StaticInJava();
     example.instanceMethod();  // Accesses both static and instance variables

     // Creating an object of StaticInnerClass, a static inner class
     StaticInJava.StaticInnerClass innerClass = new StaticInJava.StaticInnerClass();
     innerClass.display();  // Accesses static variable of the outer class
 }
}
