import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // =====================================================================
        // 1. CREATING STREAMS FROM DIFFERENT SOURCES
        // =====================================================================

        // Create a Stream from a List
        List<String> namesList = Arrays.asList("Alice", "Bob", "Charlie");
        Stream<String> listStream = namesList.stream(); // Creates sequential stream

        // Create a Stream from an Array
        String[] namesArray = {"Alice", "Bob", "Charlie"};
        Stream<String> arrayStream = Arrays.stream(namesArray);

        // Create Stream directly using Stream.of()
        Stream<String> directStream = Stream.of("Alice", "Bob", "Charlie");

        // =====================================================================
        // 2. STREAM OPERATIONS (INTERMEDIATE + TERMINAL)
        // =====================================================================

        // (A) FILTER - Keep names with length > 4
        List<String> filteredNames = namesList.stream()
                .filter(name -> name.length() > 4)  // Intermediate operation
                .collect(Collectors.toList());       // Terminal operation
        System.out.println("Filtered names: " + filteredNames); // [Alice, Charlie]

        // (B) MAP - Transform each name to its length
        List<Integer> nameLengths = namesList.stream()
                .map(name -> name.length())          // Convert String to Integer
                .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths); // [5, 3, 7]

        // (C) SORT - Sort numbers in natural order
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1);
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()                           // Natural sorting
                .collect(Collectors.toList());
        System.out.println("Sorted numbers: " + sortedNumbers); // [1, 2, 5, 9]

        // (D) DISTINCT - Remove duplicates
        List<Integer> duplicateNumbers = Arrays.asList(1, 2, 2, 3, 3, 3);
        List<Integer> uniqueNumbers = duplicateNumbers.stream()
                .distinct()                        // Remove duplicates
                .collect(Collectors.toList());
        System.out.println("Unique numbers: " + uniqueNumbers); // [1, 2, 3]

        // (E) COLLECT TO SET - Automatically removes duplicates
        List<String> duplicateNames = Arrays.asList("Alice", "Bob", "Alice");
        Set<String> nameSet = duplicateNames.stream()
                .collect(Collectors.toSet());       // Converts to Set
        System.out.println("Name set: " + nameSet); // [Alice, Bob] (order may vary)

        // (F) ANYMATCH - Check if any element matches condition
        List<Integer> evenCheckNumbers = Arrays.asList(1, 2, 3, 4);
        boolean hasEvenNumber = evenCheckNumbers.stream()
                .anyMatch(n -> n % 2 == 0);        // Returns boolean
        System.out.println("Has even number? " + hasEvenNumber); // true

        // (G) REDUCE - Sum all numbers (with parallel processing)
        List<Integer> numbersToSum = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int totalSum = numbersToSum.parallelStream() // Uses multiple threads
                .reduce(0, (a, b) -> a + b);       // Sums all elements
        System.out.println("Total sum: " + totalSum); // 55
    }
}


