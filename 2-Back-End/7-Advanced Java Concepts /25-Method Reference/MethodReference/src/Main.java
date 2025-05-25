import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Create an immutable list of names
        List<String> names = Arrays.asList(
                "Mohammed", "Ahmed", "Mostafa", "Hussein", "Samir", "Osama", "Maher"
        );

        // 2. Stream processing with method reference
        /*
         * Pipeline breakdown:
         *   - names.stream(): Creates a stream from the list
         *   - .map(String::toUpperCase): Transforms each string to uppercase
         *     - String::toUpperCase is a METHOD REFERENCE:
         *       * Equivalent to lambda: (str) -> str.toUpperCase()
         *       * Shorthand for calling an existing method
         *       * Works when method takes no arguments and operates on the stream element
         *   - .toList(): Terminal operation that collects results into a new List
         */
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .toList();

        // 3. Print the results using method reference
        /*
         * Names.forEach(System.out::println):
         *   - System.out::println is another METHOD REFERENCE
         *   - Equivalent to: (name) -> System.out.println(name)
         *   - For each element in the list, calls System.out.println()
         */
        upperCaseNames.forEach(System.out::println);
    }
}
