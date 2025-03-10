package Polymorphism;

import java.util.ArrayList;

public class Polymorphism {

    public static void main(String[] args) {

        // POLYMORPHISM OVERLOADING (Compile-Time Polymorphism)
        // Method Overloading occurs when multiple methods have the same name but differ in the number or type of parameters.
        // The decision of which method to call is made during compile-time. This is why it's also called "compile-time polymorphism."

        class Calculator {
            // Overloaded methods with different parameters
            public int add(int a, int b) {
                return a + b;
            }

            public int add(int a, int b, int c) {
                return a + b + c;
            }

            public double add(double a, double b) {
                return a + b;
            }
        }

        // Calls the overloaded methods
        Calculator calc = new Calculator();
        System.out.println("Addition of two integers: " + calc.add(10, 20));  // Output: 30
        System.out.println("Addition of three integers: " + calc.add(10, 20, 30));  // Output: 60
        System.out.println("Addition of two doubles: " + calc.add(10.5, 20.5));  // Output: 31.0


        // POLYMORPHISM OVERRIDING (Runtime Polymorphism)
        // Method Overriding occurs when a subclass provides its own implementation of a method that is already defined in the superclass.
        // The decision of which method to call is made at runtime, based on the object's actual class type (i.e., which object calls the method).

        class Animal {
            // Method in the superclass
            public void sound() {
                System.out.println("Animal makes a sound");
            }
        }

        class Dog extends Animal {
            // Overriding the sound method in the subclass
            @Override
            public void sound() {
                System.out.println("Dog barks");
            }
        }

        class Cat extends Animal {
            // Overriding the sound method in the subclass
            @Override
            public void sound() {
                System.out.println("Cat meows");
            }
        }

        // Creating objects and calling the overridden methods
        Animal animal1 = new Dog();
        animal1.sound();  // Output: Dog barks

        Animal animal2 = new Cat();
        animal2.sound();  // Output: Cat meows


        // POLYMORPHIC GENERIC ARRAY LIST
        // In Java, we can create polymorphic behavior using generics with ArrayList.
        // An ArrayList can hold elements of different types if we use a common parent type (superclass).

        class AnimalList {
            public void makeSound() {
                System.out.println("Animal makes a sound");
            }
        }

        class DogList extends AnimalList {
            @Override
            public void makeSound() {
                System.out.println("Dog barks");
            }
        }

        class CatList extends AnimalList {
            @Override
            public void makeSound() {
                System.out.println("Cat meows");
            }
        }

        // Using ArrayList to hold different Animal objects
        ArrayList<AnimalList> animals = new ArrayList<>();
        animals.add(new DogList());
        animals.add(new CatList());

        // Looping through the list and calling makeSound method polymorphically
        for (AnimalList animal : animals) {
            animal.makeSound();  // Dog barks, Cat meows
        }


        // EARLY BINDING (Compile-Time Binding)
        // Early binding refers to the method call being resolved at compile-time. It happens when:
        // 1. Methods are overloaded (compile-time polymorphism).
        // 2. Static methods or fields are accessed. Static members are bound at compile-time.

        class EarlyBinding {
            // Static method (binds early)
            public static void staticMethod() {
                System.out.println("Static method is called");
            }

            // Instance method (binds late if overridden)
            public void instanceMethod() {
                System.out.println("Instance method is called");
            }
        }

        // Calling the static method
        EarlyBinding.staticMethod();  // Output: Static method is called


        // LATE BINDING (Runtime Binding)
        // Late binding happens when:
        // 1. Methods are overridden (runtime polymorphism).
        // 2. The method to call is resolved during the execution of the program (runtime), based on the object's actual class type.

        class Parent {
            // Static method in Parent class
            public static void staticMethod() {
                System.out.println("Static method in Parent class");
            }
        }

        class Child extends Parent {
            // Static method in Child class (hides the method in Parent class)
            public static void staticMethod() {
                System.out.println("Static method in Child class");
            }
        }

        // Demonstrating hiding of static methods
        Parent.staticMethod();  // Output: Static method in Parent class
        Child.staticMethod();   // Output: Static method in Child class


        // STATIC METHOD OVERLOADING EXAMPLE (Hiding not overriding)
        // Static methods belong to the class, not instances of the class, and cannot be overridden.
        // They can be hidden by defining a static method with the same signature in the subclass.

        System.out.println("\nStatic Method Overloading Example:");
        Parent.staticMethod();  // Output: Static method in Parent class
        Child.staticMethod();   // Output: Static method in Child class
        
        Parent Children = new Child();
        Children.staticMethod();
        
        Child ChildSubClass = new Child();
        ChildSubClass.staticMethod();
        
        // Final Notes:
		/*- Method overloading is a type of compile-time polymorphism where the method signature changes (number or type of parameters).
		- Method overriding is a type of runtime polymorphism where the subclass provides its specific implementation of a method already 
		defined in the superclass.
		- Polymorphism helps to design flexible and reusable code. In collections and APIs, polymorphism allows different types of objects 
		to be treated uniformly.
		- Static methods belong to the class, not objects, and cannot be overridden, but they can be hidden by defining a static method with 
		the same name in the subclass.
		- Early binding happens when decisions are made at compile-time (like overloaded or static methods), and late binding happens at runtime 
		(like overridden methods).*/
    }
}
