package ClassesAndObjectsInJava;

//This is the definition of a class called 'Car'
class Car {
 
 // Instance variables (attributes) of the class with different access modifiers
 
 private String color;  // 'private' - The color of the car is only accessible within the 'Car' class
 public String model;   // 'public' - The model of the car is accessible from anywhere
 protected int year;    // 'protected' - The year of the car is accessible within the same package or subclasses

 // Constructor method to initialize a new Car object
 // Advantage: Allows you to create and initialize the object at the time of creation
 public Car(String color, String model, int year) {
     this.color = color;  // 'this' refers to the current object being created
     this.model = model;  // Assign the model to the object's model attribute
     this.year = year;    // Assign the year to the object's year attribute
 }

	/*
	 * Default (No modifier):
	 * 
	 * Access level: Accessible only within the same package. Usage: When no access
	 * modifier is specified (i.e., the "default" access modifier), the class,
	 * method, or variable is accessible only within the same package. Example:
	 * String model; or void myMethod() without any access modifier.
	 */
 // Getter method for color attribute (private)
 // Advantage: Encapsulates the color attribute, provides controlled access to it
 public String getColor() {
     return color;  // Returns the color of the car
 }

 // Setter method for color attribute (private)
 // Advantage: Allows you to modify the color of the car while maintaining control
 public void setColor(String color) {
     this.color = color;  // Sets the color of the car
 }

 // Getter method for model attribute (public)
 // Advantage: Allows access to the model of the car from anywhere
 public String getModel() {
     return model;  // Returns the model of the car
 }

 // Setter method for model attribute (public)
 // Advantage: Allows modification of the model of the car from anywhere
 public void setModel(String model) {
     this.model = model;  // Sets the model of the car
 }

 // Getter method for year attribute (protected)
 // Advantage: Allows access to the year of the car within the same package or from subclasses
 protected int getYear() {
     return year;  // Returns the year of the car
 }

 // Setter method for year attribute (protected)
 // Advantage: Allows modification of the year of the car within the same package or from subclasses
 protected void setYear(int year) {
     this.year = year;  // Sets the year of the car
 }

 // Method to display information about the car
 // Advantage: Provides a way to display object information neatly
 public void displayInfo() {
     // Printing out the car's information using getters
     System.out.println("Car Details:");
     System.out.println("Model: " + getModel());  // Using the getter method for model
     System.out.println("Color: " + getColor());  // Using the getter method for color
     System.out.println("Year: " + getYear());    // Using the getter method for year
 }
}

//Main class where the program starts executing
public class ClassesAndObjectsInJava {
 public static void main(String[] args) {
     // Creating a Car object by calling the constructor of the 'Car' class
     Car myCar = new Car("Red", "Toyota Corolla", 2022);  // 'myCar' is an object of type Car

     // Calling the displayInfo method on the 'myCar' object to print its details
     myCar.displayInfo();  // This will output the car's details to the console

     // Changing the color and year of the car using setter methods
     myCar.setColor("Blue");  // Changing the color to Blue (using setter)
     myCar.setYear(2023);     // Changing the year to 2023 (using setter)

     // Display updated car details
     System.out.println("\nUpdated Car Details:");
     myCar.displayInfo();  // This will show the updated details after modification
 }
}
