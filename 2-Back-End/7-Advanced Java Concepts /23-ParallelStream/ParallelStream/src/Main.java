import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // 1. Create a large list of random numbers
        int size = 10000000;  // 100 million elements
        List<Integer> numbers = new ArrayList<Integer>(size);
        Random rdn = new Random();

        // Populate the list with random values (0-99)
        for(int i = 1; i <= size; i++) {
            numbers.add(rdn.nextInt(100));
        }

        // 2. Sequential Stream Processing
        long start = System.currentTimeMillis();
        // Pipeline: Double each number → Keep evens → Sum all
        int sum1 = numbers.stream()
                .map(n -> n * 2)        // Stateless: Stack processing
                .filter(n -> n % 2 == 0) // Stateless: Stack processing
                .reduce(0, (c, e) -> c + e); // Terminal operation
        long end = System.currentTimeMillis();
        System.out.println("Sequential time: " + (end - start) + "ms");

        // 3. Parallel Stream Processing
        start = System.currentTimeMillis();
        // Similar pipeline but optimized for parallel processing
        int sum2 = numbers.parallelStream()
                .map(n -> n * 2)        // Concurrent processing
                .filter(n -> n % 2 == 0) // Concurrent filtering
                .mapToInt(i -> i)       // Convert to primitive stream
                .sum();                 // Specialized terminal op
        end = System.currentTimeMillis();
        System.out.println("Parallel time: " + (end - start) + "ms");

        // 4. The "Ignored" Sorted Operation
        /*
         * Why this appears to do nothing:
         * 1. The sorted() operation IS executed, but:
         *    - It processes all 100 million elements
         *    - Creates a temporary sorted copy in heap memory
         *    - Then sends them to forEach()
         * 2. The output appears "ignored" because:
         *    - System.out.println is synchronized and slow
         *    - The console can't display 100M lines
         *    - You only see the last few lines that flush before program end
         * 3. This is actually an ANTI-PATTERN because:
         *    - It wastes time sorting
         *    - The output is meaningless (too much data)
         *    - Better: .limit(100).sorted().forEach(...)
         */
        numbers.parallelStream()
                .sorted()  // Expensive full sort of 100M elements
                .forEach(n -> System.out.println(n));  // Bottlenecked output
    }
}