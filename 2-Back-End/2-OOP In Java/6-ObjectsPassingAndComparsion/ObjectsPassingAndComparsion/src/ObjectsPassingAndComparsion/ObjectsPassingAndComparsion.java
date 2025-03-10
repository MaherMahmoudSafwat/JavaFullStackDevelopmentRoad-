package ObjectsPassingAndComparsion;

public class ObjectsPassingAndComparsion {

    // This is a class to demonstrate passing objects, comparing objects, and returning objects.
    
    // Define a class `Car` with some attributes to demonstrate object passing and comparison
    static class Car {
        String model;
        int year;
        
        // Constructor to initialize Car object
        public Car(String model, int year) {
            this.model = model;
            this.year = year;
        }

        // Overriding the `equals` method to compare two Car objects by value (model and year)
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {  // Check if both references point to the same object
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {  // Check if object is null or not the same class
                return false;
            }
            Car car = (Car) obj;
            return year == car.year && model.equals(car.model);  // Compare model and year of both cars
        }

        // Overriding the `hashCode` method to make `equals` work correctly with hash-based collections like HashSet
        @Override
        public int hashCode() {
            return 31 * model.hashCode() + year;  // Combining model's hashCode and year
        }

        // Method to display car details
        public void displayInfo() {
            System.out.println("Car Model: " + model);
            System.out.println("Car Year: " + year);
        }
    }

    public static void main(String[] args) {
        
        // 1. Passing Objects by Reference
        Car car1 = new Car("Honda Civic", 2020); // Creating car1 object
        System.out.println("Before passing by reference:");
        car1.displayInfo();
        
        // Passing car1 object to modifyCar method (By Reference)
        modifyCar(car1);  // car1 is passed by reference, so it will be modified
        System.out.println("After passing by reference:");
        car1.displayInfo();  // car1 object is modified in the method
        
        
        // 2. Passing Objects by Value
        Car car2 = new Car("Toyota Corolla", 2021);  // Creating car2 object
        System.out.println("Before passing by value:");
        car2.displayInfo();
        
        // Passing car2 object to modifyCarValue method (By Value)
        modifyCarValue(car2);  // car2 is passed by value (a copy of the reference is passed)
        System.out.println("After passing by value:");
        car2.displayInfo();  // car2 object remains unchanged outside the method

        
        // 3. Comparing Objects by Value (Using `equals` method)
        Car car4 = new Car("Honda Civic", 2020);  // Create a new Car object with the same values as car1
        if (car1.equals(car4)) {  // Comparing car1 and car4 objects by value using the `equals` method
            System.out.println("car1 and car4 are equal by value (same model and year).");
        } else {
            System.out.println("car1 and car4 are not equal.");
        }
        
        // 4. Comparing Objects by Reference (Using `==` operator)
        if (car1 == car4) {  // Comparing references (memory locations) of car1 and car4
            System.out.println("car1 and car4 are the same object by reference.");
        } else {
            System.out.println("car1 and car4 are not the same object by reference.");
        }
    }

    // Method to modify the car object (passed by reference)
    // Since the reference of the car is passed, changes to the car will affect the original object.
    public static void modifyCar(Car car) {
        car.model = "Honda Accord";  // Modifying the model of the car
        car.year = 2023;  // Modifying the year of the car
    }

    // Method to modify the car object (passed by value)
    // Even though the reference is passed, it does not modify the original object
    public static void modifyCarValue(Car car) {
        car = new Car("Nissan Altima", 2024);  // This change affects only the local copy of the reference
        System.out.println("Inside modifyCarValue method:");
        car.displayInfo();  // Displaying the modified car inside the method
    }
}
