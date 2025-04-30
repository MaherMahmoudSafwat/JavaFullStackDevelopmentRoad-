/**
 * A generic class that can shelter any specific type of Animal.
 * Demonstrates bounded type parameters in generics.
 *
 * @param <T> The type of animal this shelter holds (must extend Animal)
 *
 * Example Usage:
 * AnimalShelter<Dog> dogShelter = new AnimalShelter<>(new Dog("Buddy"));
 * dogShelter.careForAnimal();  // Outputs care message and "Woof!"
 * Dog myDog = dogShelter.getResident();
 */
class AnimalShelter<T extends Animal> {  // T can be Animal or any subclass
    private T resident;  // The animal being sheltered

    /**
     * Creates an animal shelter with the given resident
     * @param animal The animal to shelter (must be of type T)
     */
    public AnimalShelter(T animal) {
        this.resident = animal;
    }

    /**
     * Performs standard care procedures for the sheltered animal.
     * Demonstrates how generic type can use Animal class methods.
     */
    public void careForAnimal() {
        // Can call any Animal methods on resident
        System.out.println("Caring for " + resident.getName());
        resident.makeSound();  // Will use specific implementation (polymorphism)
    }

    /**
     * Returns the sheltered animal with its specific type preserved.
     * @return The resident animal (of type T)
     */
    public T getResident() {
        return resident;  // Returns the exact type that was put in
    }
}

/*
 * Key Concepts Demonstrated:
 *
 * 1. Bounded Generics:
 *    - <T extends Animal> ensures only Animal subclasses can be used
 *    - Compiler prevents: new AnimalShelter<String>("NotAnAnimal")
 *
 * 2. Type Safety:
 *    - getResident() returns T, so no casting needed:
 *      Dog d = new AnimalShelter<Dog>(...).getResident();
 *
 * 3. Polymorphism in Generics:
 *    - resident.makeSound() calls the appropriate subclass implementation
 *
 * 4. Generic Field:
 *    - private T resident maintains type information
 *
 * Advanced Usage Example:
 *
 * // Works with any Animal subclass
 * AnimalShelter<Cat> catShelter = new AnimalShelter<>(new Cat("Whiskers"));
 * catShelter.careForAnimal();  // Would use Cat's makeSound()
 *
 * // Return type remains specific
 * Cat myCat = catShelter.getResident();  // No casting needed
 *
 * // Compile-time type safety
 * AnimalShelter<String> invalid;  // Won't compile - String not an Animal
 */


