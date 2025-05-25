import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // 1. Create an immutable list of integers in memory
        // This list is stored in RAM as a regular Java collection
        List<Integer> numbers = Arrays.asList(3, 2, 1, 5, 4);

        /*
         * 2. Create a stream pipeline (lazy evaluation)
         *
         * Key Question: Does numbers.stream() create a new copy of data in RAM?
         * Answer: NO. The stream is just a "view" of the original data with processing instructions.
         *
         * Memory behavior:
         * - numbers.stream() - Creates a stream REFERENCE to the original list (no new data in RAM)
         * - map() - Adds a transformation instruction (no processing yet)
         * - sorted() - Adds a sorting instruction (this will need memory later during processing)
         *
         * At this point (S1 declaration):
         * - No new collection is created in memory
         * - Only the processing pipeline is set up
         */
        Stream<Integer> s1 = numbers.stream()  // Reference to original data
                .map(n -> n * 2)    // Transformation instruction (double each element)
                .sorted();           // Sorting instruction (will need temp memory later)

        /*
         * 3. Terminal operation (triggers actual processing)
         *
         * Memory behavior during forEach:
         * 1. The stream pulls elements from original list one by one
         * 2. map() transforms each element (3→6, 2→4, etc.)
         * 3. sorted() requires ALL elements to be collected in temporary memory
         *    - This is when new memory is actually allocated
         *    - The entire transformed collection [6,4,2,10,8] is stored temporarily
         * 4. After sorting, elements are printed one by one
         */
        s1.forEach(n -> System.out.println(n));

        // Final output will be:
        // 2 (1×2)
        // 4 (2×2)
        // 6 (3×2)
        // 8 (4×2)
        // 10 (5×2)
    }
}