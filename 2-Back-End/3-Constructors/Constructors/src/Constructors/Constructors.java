// Package declaration - indicates that the class belongs to the "Constructors" package
package Constructors;

// Constructor in Java is a special method used to initialize objects
/* Encapsulation is one of the four fundamental OOP concepts, and it is the process of wrapping data (variables) and code (methods) together 
as a single unit.
 In Java, encapsulation is implemented by making the instance variables (attributes) of a class private and providing public getter and 
setter methods to access and modify these variables.
*/

class Car {

    // Instance variables (attributes) of the class - private to enforce encapsulation
    // These variables cannot be accessed directly outside this class, they can only be accessed through getter and setter methods.
    private String color;  // The color of the car
    private String model;  // The model name of the car
    private int year;      // The year the car was manufactured

    // Default Constructor (No-Argument Constructor)
    // This constructor is called when an object is created without passing any arguments.
    // It initializes the object with default values.
    public Car() {
        // Initializing default values for the car object
        this.color = "Unknown";  // Default color is "Unknown"
        this.model = "Unknown";  // Default model is "Unknown"
        this.year = 0;           // Default year is 0
    }

    // Parameterized Constructor
    // This constructor is called when an object is created with specified values for color, model, and year.
    // It allows us to initialize the object with specific values at the time of creation.
    public Car(String color, String model, int year) {
        // The constructor assigns the provided values to the instance variables
        this.color = color;  // Assigns the provided color to the 'color' attribute
        this.model = model;  // Assigns the provided model to the 'model' attribute
        this.year = year;    // Assigns the provided year to the 'year' attribute
    }

    // Copy Constructor
    // This constructor is used to create a new object by copying values from another existing object.
    // The copy constructor is called to create a new object that is a copy of another car object.
    public Car(Car otherCar) {
        // Here, `this` refers to the current object (the one we are creating).
        // The values of `otherCar` are copied into the current object's instance variables.
        // Calls the parameterized constructor to copy the values
        this(otherCar.color, otherCar.model, otherCar.year);  // Calls the parameterized constructor to copy the values
        // Note: constructor invoking with `this()` must be the first statement in the constructor body.
    }

    // Getter methods to access the private attributes (Implementing Encapsulation)
    // These methods allow controlled access to the private variables, following the encapsulation principle.
    public String getColor() {
        return color;  // Returns the color of the car
    }

    public String getModel() {
        return model;  // Returns the model of the car
    }

    public int getYear() {
        return year;  // Returns the year of the car
    }

    // Setter methods to modify the private attributes (Implementing Encapsulation)
    // These methods allow controlled modification of the private variables, following the encapsulation principle.
    public void setColor(String color) {
        this.color = color;  // Sets the color of the car
    }

    public void setModel(String model) {
        this.model = model;  // Sets the model of the car
    }

    public void setYear(int year) {
        this.year = year;  // Sets the year of the car
    }

    // Method to display information about the car
    public void displayInfo() {
        // Prints the details of the car to the console
        System.out.println("Car Details:");
        System.out.println("Model: " + model);  // Displays the model of the car
        System.out.println("Color: " + color);  // Displays the color of the car
        System.out.println("Year: " + year);    // Displays the year of the car
    }

    // Destructor in Java (Not a real destructor in Java, as Java uses garbage collection)
    // The finalize method is used to perform clean-up actions before the object is destroyed.
    // It is rarely used and not recommended to rely on it for managing resources in Java.
    // This method is called by the garbage collector when an object is about to be destroyed.
    @Override
    protected void finalize() throws Throwable {
        try {
            // Print a message when the object is about to be garbage collected
            System.out.println("Car object is being garbage collected and destroyed.");
        } finally {
            // Call the superclass finalize method to ensure proper clean-up
            super.finalize();  // Calls the finalize method of the superclass
        }
    }
}

// Main class to run the program
public class Constructors {
    public static void main(String[] args) {
        
        // Creating a Car object using the default constructor (no parameters passed)
        // This calls the default constructor of the Car class, which initializes the object with default values.
        Car car1 = new Car();  // Default constructor, no parameters
        car1.displayInfo();     // Prints the default values for the car

        // Creating a Car object using the parameterized constructor (passing specific values)
        // This constructor takes parameters for color, model, and year and creates an object with those values.
        Car car2 = new Car("Blue", "Honda Civic", 2021);  // Parameterized constructor, passing values
        car2.displayInfo();     // Prints the car details with the passed values

        // Modifying car attributes using setter methods
        // We use setter methods to modify the attributes of car2 object.
        car2.setColor("Red");   // Using setter to change color
        car2.setYear(2022);     // Using setter to change year
        car2.displayInfo();     // Prints the updated car details
        
        // Creating a new Car object using the copy constructor
        // The copy constructor creates a new Car object as a copy of car2.
        Car car3 = new Car(car2);  // Copy constructor creates a new object as a copy of car2
        car3.displayInfo();         // Prints the car details (same as car2)

        // Nullifying the object to suggest garbage collection (in reality, JVM decides when to collect)
        // Setting car2 to null suggests that the object may be garbage collected soon.
        car2 = null; 

        // Request garbage collection (it's not guaranteed to run immediately)
        // Calling System.gc() requests the garbage collector to run, but the actual timing is up to the JVM.
        System.gc();  // This will trigger the finalize method if the object is garbage collected
    }
}
