import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // =================================================================
        // 1. Using Collection Interface (Most general type of collection)
        // =================================================================
        // Create a Collection of Integers (using ArrayList as implementation)
        Collection<Integer> numbers = new ArrayList<Integer>();

        // Add elements to the collection
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        // Check if collection contains a specific element
        boolean isFound = numbers.contains(3);

        // Iterate through the collection using enhanced for-loop
        // and print each number multiplied by 3
        for (int num : numbers) {
            System.out.println(num * 3);
        }

        // Print results
        System.out.println("Contains 3? " + isFound);
        System.out.println("All numbers: " + numbers);

        // =================================================================
        // 2. Using List Interface (Ordered collection with index access)
        // =================================================================
        // Create a List of Integers (more specific than Collection)
        List<Integer> numbers2 = new ArrayList<Integer>();

        // Add elements to the list
        numbers2.add(4);
        numbers2.add(5);
        numbers2.add(6);

        // Access element by index (feature specific to List)
        System.out.println("Element at index 2: " + numbers2.get(2));

        // =================================================================
        // 3. Using ArrayList Class (Concrete implementation)
        // =================================================================
        // Create an ArrayList directly (most specific type)
        ArrayList<Integer> numbers3 = new ArrayList<Integer>();

        // Add elements to the ArrayList
        numbers3.add(7);
        numbers3.add(8);
        numbers3.add(9);

        // Check if ArrayList equals to a value (will always be false)
        // Note: This compares the entire ArrayList with a single number
        System.out.println("Equals check: " + numbers3.equals(5));  // false
    }
}
