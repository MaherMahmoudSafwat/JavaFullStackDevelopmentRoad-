/**
 * Base class representing a generic Animal.
 * Demonstrates inheritance and polymorphism basics.
 */
class Animal {
    private String name;  // Encapsulated field for animal's name

    /**
     * Constructor to create an Animal with a name
     * @param name The name of the animal
     */
    public Animal(String name) {
        this.name = name;  // 'this' distinguishes parameter from field
    }

    /**
     * Makes a generic animal sound (default implementation)
     * This will be overridden by subclasses for specific sounds
     */
    public void makeSound() {
        System.out.println("Some generic animal sound");
    }

    /**
     * Gets the animal's name
     * @return The name of the animal
     */
    public String getName() {
        return name;
    }
}

/**
 * Dog class that extends Animal, demonstrating inheritance.
 * Shows method overriding for polymorphic behavior.
 */
class Dog extends Animal {
    /**
     * Creates a Dog with given name
     * @param name The dog's name
     */
    public Dog(String name) {
        super(name);  // Calls parent class (Animal) constructor
    }

    /**
     * Overrides makeSound() to provide dog-specific behavior
     * Demonstrates polymorphism - same method name, different implementation
     */
    @Override  // Annotation indicates we're overriding parent method
    public void makeSound() {
        System.out.println("Woof!");  // Dog-specific sound
    }

    /**
     * Additional dog-specific method not present in Animal class
     */
    public void wagTail() {
        System.out.println(getName() + " is wagging its tail!");
    }
}

/*
 * Key OOP Concepts Demonstrated:
 *
 * 1. Inheritance:
 *    - Dog inherits from Animal (all fields and methods)
 *    - Shown by 'extends' keyword
 *
 * 2. Polymorphism:
 *    - Overriding makeSound() provides different behavior
 *    - Animal myPet = new Dog("Buddy"); myPet.makeSound() → "Woof!"
 *
 * 3. Encapsulation:
 *    - Private name field with public getName() accessor
 *
 * 4. Constructor Chaining:
 *    - Dog constructor calls super(name) to initialize Animal part
 *
 * Example Usage:
 *
 * Animal genericAnimal = new Animal("Creature");
 * genericAnimal.makeSound(); → "Some generic animal sound"
 *
 * Dog myDog = new Dog("Buddy");
 * myDog.makeSound(); → "Woof!"
 * myDog.wagTail(); → "Buddy is wagging its tail!"
 *
 * // Polymorphism in action:
 * Animal myPet = new Dog("Max");
 * myPet.makeSound(); → "Woof!" (uses Dog's implementation)
 */




