package Inheritance;

//Inheritance in Java is a mechanism where one class (child/subclass) can inherit the fields and methods of another class (parent/superclass).
//It allows for the reusability of code and establishing a relationship between parent and child classes.
//The "is-a" relationship is key to understanding inheritance. A subclass "is a" type of the superclass.

//The 'extends' keyword is used to create a subclass, which inherits from a superclass.
class Animal {
 // Superclass (Parent class)
 String name;  // Default access modifier (package-private)

 // Constructor for Animal
 public Animal(String name) {
     this.name = name;
 }

 // Method in the superclass with 'public' access modifier
 public void sound() {
     System.out.println("Animal makes a sound");
 }

 // Method with 'protected' access modifier
 protected void display() {
     System.out.println("Animal name is: " + name);
 }

 // Method with 'private' access modifier, not accessible in subclasses
 private void privateMethod() {
     System.out.println("Private method in Animal");
 }
}

//Dog class inherits from Animal class (subclass inherits superclass)
class Dog extends Animal {
 // Dog class is a subclass (child class)
 public Dog(String name) {
     // Call the superclass constructor using 'super' keyword
     super(name);  // This invokes the Animal class constructor
 }

 // Overriding the 'sound' method from the superclass
 @Override
 public void sound() {
	 super.display();//This is Allowed.
     System.out.println("Dog barks");
 }

 // Method to demonstrate access to protected method from superclass
 public void displayInfo() {
     display();  // Can access 'display' because it's protected in Animal class
 }
}

//Demonstrating inheritance, superclass and subclass relationship, and method overriding
public class Inheritance {
 public static void main(String[] args) {
     // Creating an object of Dog, which is a subclass of Animal
     Dog dog = new Dog("Buddy");

     // Calling method from Animal (superclass)
     dog.displayInfo(); // Displays: Animal name is: Buddy

     // Calling overridden method from Dog (subclass)
     dog.sound(); // Displays: Dog barks

     // The following line will cause a compile-time error, as private methods are not accessible in subclass
     // dog.privateMethod(); // Error: privateMethod() has private access in Animal
 }
}

//The 'super' keyword is used in the subclass to access the constructor, methods, and fields of the superclass.
//Here, 'super(name)' calls the Animal constructor to initialize the 'name' attribute of the Animal class.
//'super' can also be used to call the superclass method if we want to override a method but still retain the parent class behavior.

//"is-a" Relationship:
//In the above example, the Dog "is a" type of Animal. This means Dog inherits the properties and behaviors of Animal.
//Inheritance establishes a relationship where a subclass (child class) "is a" subclass of its superclass (parent class).

/*
 * Specialization vs Generalization: Specialization refers to when a subclass
 * adds more specific behaviors or attributes to those inherited from the
 * superclass. For example, Dog is a specialized form of Animal, adding specific
 * behaviors like barking. Generalization is when a superclass defines general
 * behaviors that can be shared by all subclasses. Animal is a generalized
 * concept of all animals, and Dog is a specialized type of Animal.
 */

//Object class in Java:
//All classes in Java implicitly inherit from the Object class, which is the root class of the class hierarchy.
//It contains some common methods like 'toString()', 'equals()', and 'hashCode()' which are inherited by all Java classes.

class Cat extends Animal {
 public Cat(String name) {
     super(name);
 }

 @Override
 public void sound() {
     System.out.println("Cat meows");
 }
}

class Main {
 public static void main(String[] args) {
     // Creating objects of Dog and Cat
     Animal dog = new Dog("Buddy");
     Animal cat = new Cat("Kitty");

     // Calling methods on Dog and Cat objects
     dog.display();  // Animal name is: Buddy
     dog.sound();    // Dog barks

     cat.display();  // Animal name is: Kitty
     cat.sound();    // Cat meows
 }
}

//Differences in Java's access modifiers:

//1. public: Accessible from any other class.
//2. protected: Accessible within the same package and subclasses (even in different packages).
//3. default (package-private): Accessible only within the same package.
//4. private: Accessible only within the same class, not visible to subclasses or other classes.

//In this example:
//- 'name' is default (package-private), accessible only within the same package.
//- 'sound()' is public, accessible from any other class.
//- 'display()' is protected, accessible in subclasses and within the same package.
//- 'privateMethod()' is private, accessible only within the Animal class, not in subclasses.

//Java supports **single inheritance**, which means a class can inherit from only one superclass at a time. 
//Java does not support multiple inheritance through classes but allows it through interfaces. 
//This avoids ambiguity and simplifies the class hierarchy.
