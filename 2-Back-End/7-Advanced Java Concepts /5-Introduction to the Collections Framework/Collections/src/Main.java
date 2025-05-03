import java.util.*; // Import all utilities including Collections Framework classes

public class Main {
    public static void main(String[] args) {
        // =====================================================================
        // 1. DEMONSTRATING LIST (ArrayList)
        // - Allows duplicates
        // - Maintains insertion order
        // =====================================================================
        List<String> namesList = new ArrayList<String>(); // Create ArrayList implementation of List
        namesList.add("Mohammed");  // Add elements to the list
        namesList.add("Ahmed");
        namesList.add("Mostafa");
        namesList.add("Mostafa");    // Duplicate value - allowed in List
        System.out.println("List contents (allows duplicates, maintains order):");
        System.out.println(namesList); // Output: [Mohammed, Ahmed, Mostafa, Mostafa]

        // =====================================================================
        // 2. DEMONSTRATING SET (HashSet)
        // - No duplicates allowed
        // - No guaranteed order (HashSet uses hash table)
        // =====================================================================
        Set<String> namesSet = new HashSet<String>(); // Create HashSet implementation of Set
        namesSet.add("Mohammed");
        namesSet.add("Ahmed");
        namesSet.add("Mostafa");
        namesSet.add("Mostafa");     // Duplicate value - will not be added to Set
        System.out.println("\nSet contents (no duplicates, unordered):");
        System.out.println(namesSet); // Output: [Ahmed, Mohammed, Mostafa] (order may vary)

        // =====================================================================
        // 3. DEMONSTRATING MAP (HashMap)
        // - Stores key-value pairs
        // - Keys must be unique
        // =====================================================================
        Map<Integer, String> namesMap = new HashMap<Integer, String>(); // Create HashMap
        namesMap.put(1, "Mohammed");  // Add key-value pairs
        namesMap.put(2, "Ahmed");
        namesMap.put(3, "Mostafa");
        namesMap.put(4, "Mostafa");   // Duplicate values allowed (keys must be unique)
        System.out.println("\nMap contents (key-value pairs):");
        System.out.println(namesMap); // Output: {1=Mohammed, 2=Ahmed, 3=Mostafa, 4=Mostafa}

        // =====================================================================
        // 4. DEMONSTRATING COLLECTIONS UTILITY CLASS
        // - Sorting a List using Collections.sort()
        // =====================================================================
        List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(5, 2, 1, 3));
        System.out.println("\nOriginal unsorted list:");
        System.out.println(numbers); // Output: [5, 2, 1, 3]

        Collections.sort(numbers);   // Sort the list in natural order (ascending)
        System.out.println("Sorted list:");
        System.out.println(numbers); // Output: [1, 2, 3, 5]
    }
}



