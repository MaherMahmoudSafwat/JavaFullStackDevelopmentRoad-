import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // 1. Create an immutable list of names
        List<String> names = Arrays.asList(
                "Mohammed", "Ahmed", "Mostafa", "Hussein", "Samir", "Osama", "Maher"
        );

        // 2. Stream Processing - Approach 1 (Explicit Optional Handling)
        /*
         * Pipeline breakdown:
         *   - .stream(): Convert list to stream
         *   - .filter(n -> n.contains("x")): Keep only names containing "x"
         *   - .findFirst(): Returns first match as Optional<String>
         *
         * Key points:
         *   - Returns Optional<String> (safe for null/no-match cases)
         *   - Processing stops at first match (short-circuiting)
         *   - If no match, Optional will be empty
         */
        Optional<String> nameOptional = names.stream()
                .filter(n -> n.contains("u"))
                .findFirst();

        // 3. Handling the Optional result
        /*
         * .orElse("Not found"):
         *   - Returns value if present
         *   - Returns "Not found" if Optional is empty
         */
        System.out.println(nameOptional.orElse("Not found"));

        // 4. Stream Processing - Approach 2 (Direct Value Extraction)
        /*
         * Combined pipeline:
         *   - Same filtering as above
         *   - .orElse() immediately unwraps the Optional
         *
         * Differences from Approach 1:
         *   - More concise but less explicit about potential absence
         *   - Still safe due to .orElse()
         */
        String nameResult = names.stream()
                .filter(n -> n.contains("x"))
                .findFirst()
                .orElse("Not found");

        System.out.println(nameResult);
    }
}