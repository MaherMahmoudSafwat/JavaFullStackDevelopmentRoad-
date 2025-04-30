import java.util.ArrayList;

/**
 * This main class demonstrates various Java generics concepts including:
 * - Basic generic classes
 * - Bounded type parameters
 * - Wildcard generics
 * - Generic methods
 */
public class Main {
    public static void main(String[] args) {
        // 1. Basic generic class demonstration
        Sum X = new Sum(1, 2);
        System.out.println(X.GetTheResult()); // Outputs sum of 1 + 2

        // 2. Single type parameter generic class
        Pairs<String> P1 = new Pairs<String>();
        P1.setP1("Java"); // Setting String value
        System.out.println(P1.getP1()); // Getting String value

        // 3. Multiple type parameters with same type
        Subtract<Integer, Integer> T = new Subtract<Integer, Integer>();
        T.GetFirstNumber(13); // Setting first Integer
        T.GetSecondNumber(19); // Setting second Integer
        System.out.println(T.GetTheResult()); // Outputs subtraction result

        // 4. Multiple type parameters with different types
        Subtract<Integer, Float> Z = new Subtract<Integer, Float>();
        Z.GetFirstNumber(13); // Setting Integer
        Z.GetSecondNumber(19); // Setting Float
        System.out.println(Z.GetTheResult()); // Outputs subtraction result

        /* 5. This would cause compile error - commented out
        Subtract<Integer, String> ABC = new Subtract<Integer, String>();
        ABC.GetFirstNumber(13);
        ABC.GetSecondNumber("19"); // String can't be used in arithmetic
        System.out.println(ABC.GetTheResult()); */

        // 6. Bounded type parameter demonstration (AnimalShelter requires Animal or subclass)
        AnimalShelter<Dog> dogShelter = new AnimalShelter<>(new Dog("Buddy"));
        dogShelter.careForAnimal(); // Calls Animal's care method

        // 7. Getting specific type back from generic
        Dog myDog = dogShelter.getResident();
        myDog.makeSound(); // Calls Dog's specific method

        // 8. This would cause compile error - String doesn't extend Animal
        // AnimalShelter<String> stringShelter;

        // 9. Generic method demonstrations
        PrintAnyThing(3);       // Works with Integer
        PrintAnyThing(5.7);     // Works with Double
        PrintAnyThing("Lists"); // Works with String

        // 10. Wildcard generic method demonstrations
        ArrayList<Integer> Lists = new ArrayList<Integer>();
        Lists.add(123);
        PrintArrayList(Lists); // Unbounded wildcard - accepts any ArrayList
    }

    /**
     * Generic method that can print any type
     * @param Thing - The item to be printed (any type)
     * @param <T> - Type parameter
     */
    public static <T> void PrintAnyThing(T Thing) {
        System.out.println(Thing);
    }

    /**
     * Accepts ArrayList of any unknown type (unbounded wildcard)
     * @param Lists - ArrayList with elements of unknown type
     */
    public static void PrintArrayList(ArrayList<?> Lists) {
        System.out.println(Lists);
    }

    /**
     * Accepts ArrayList of Numbers or subclasses (upper bounded wildcard)
     * @param Lists - ArrayList containing Numbers or subclasses (Integer, Double etc.)
     */
    public static void PrintArrayList2(ArrayList<? extends Number> Lists) {
        System.out.println(Lists);
    }

    /**
     * Accepts ArrayList of Number or superclasses (lower bounded wildcard)
     * Can add Numbers to the list
     * @param Lists - ArrayList containing Number or its superclasses (Object)
     */
    public static void PrintArrayList3(ArrayList<? super Number> Lists) {
        Lists.add(1); // Can safely add Number (Integer is a Number)
        System.out.println(Lists);
    }
}