import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Create an immutable list of numbers 1 through 9
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        /* ========== STREAM CREATION AND SINGLE-USE NATURE ========== */
        // Create a stream from the list - this is the starting point
        Stream<Integer> stream1 = numbers.stream();

        // Terminal operation: forEach consumes the stream to print elements
        // Uncomment to see it work (but then stream1 can't be used again)
        // stream1.forEach(n -> System.out.println("Original: " + n));

        // WARNING: Streams are single-use pipelines. Once consumed (by any terminal operation),
        // they cannot be reused. The line below would throw IllegalStateException
        // because stream1 was already consumed by the previous forEach:
        // stream1.forEach(n -> System.out.println("Try again: " + n));

        /* ========== STREAM OPERATIONS CHAIN ========== */
        // Intermediate operation: filter creates new stream with only even numbers
        // (n % 2 == 0 means "keep numbers divisible by 2 with no remainder")
        Stream<Integer> evenNumbersStream = stream1.filter(n -> n % 2 == 0);

        // Intermediate operation: map transforms each element (doubles it here)
        // Takes current value (n), returns new value (n * 2)
        Stream<Integer> doubledNumbersStream = evenNumbersStream.map(n -> n * 2);

        // Terminal operation: reduce combines all elements into a single result
        // Parameters:
        // 0 - initial/identity value (starting sum)
        // (sum, element) -> sum + element - accumulator function that:
        //   - Takes current sum and next element
        //   - Returns new sum (current sum + element)
        int result = doubledNumbersStream.reduce(0, (sum, element) -> sum + element);
        System.out.println("Sum of doubled even numbers: " + result);

        /* ========== STREAM REUSE DEMONSTRATION ========== */
        // To "reuse" operations, we must create a brand new stream from the source
        Stream<Integer> newStream = numbers.stream();
        // Terminal operation: print each number with prefix
        newStream.forEach(n -> System.out.println("New stream number: " + n));

        /* ========== CHAINED OPERATIONS (MOST COMMON PATTERN) ========== */
        // Preferred style: chain operations in one expression
        int chainedResult = numbers.stream()          // 1. Create fresh stream
                .filter(n -> n % 2 == 0)  // 2. Filter even numbers
                .map(n -> n * 2)          // 3. Double each number
                .reduce(0, (sum, n) -> sum + n); // 4. Sum all numbers

        System.out.println("Chained operations result: " + chainedResult);

        /* KEY STREAM CONCEPTS DEMONSTRATED:
           1. Streams are created from collections (like lists) using .stream()
           2. Operations are either:
              - Intermediate (filter, map) - transform the stream
              - Terminal (reduce, forEach) - produce result/consumes stream
           3. Streams are single-use pipelines - once consumed, they're closed
           4. Chaining is preferred over separate variables for each step
           5. reduce() is powerful for aggregations (sum, min, max, etc.)
        */
    }
}
