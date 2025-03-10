package PrivateConstructorsInJava;

//Class definition for a Singleton example
class Singleton {

 // Private static instance of the class (used for Singleton pattern)
 private static Singleton instance;

 // Private constructor to prevent direct instantiation from outside
 private Singleton() {
     // Constructor body (can be empty or contain some initialization logic)
     System.out.println("Singleton object created!");
 }

 // Public method to provide access to the Singleton instance
 public static Singleton getInstance() {
     if (instance == null) {
         instance = new Singleton();  // Only create the object once
     }
     return instance;  // Return the single instance of the class
 }
}

//Main class to test the Singleton class
public class PrivateConstructorsInJava {
 public static void main(String[] args) {
     // We can only get the instance through the 'getInstance()' method
     Singleton singleton1 = Singleton.getInstance();
     Singleton singleton2 = Singleton.getInstance();

     // Checking if both instances are the same
     System.out.println(singleton1 == singleton2);  // Prints 'true', meaning both references point to the same object
 }
}

