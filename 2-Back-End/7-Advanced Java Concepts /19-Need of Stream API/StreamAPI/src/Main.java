import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
/**
 * 🧒 STREAMS ARE LIKE TOY SORTING MACHINES! 🚂
 *
 * Imagine you have a big box of toys (your data).
 *
 * WITHOUT STREAMS:
 * You pick up each toy one by one (loops)
 * It takes forever! 😫
 *
 * WITH STREAMS:
 * You dump all toys into a magic machine! ✨
 * The machine does everything at once!
 *
 * HOW IT WORKS:
 * 1. Put toys in the machine:  .stream()
 * 2. Tell it what to do:
 *    - Keep only red toys:     .filter(toy -> toy.isRed())
 *    - Sort by size:           .sorted()
 *    - Count them:             .count()
 *
 * WHY IT'S COOL:
 * 🌈 Does many things in one line
 * 🚀 Can work super fast with helpers (parallel streams)
 * 😊 Easy to read like a story!
 *
 * EXAMPLE:
 * long redToys = toyBox.stream()
 *                   .filter(toy -> toy.isRed())
 *                   .count();
 *
 * Now you know streams! 🎉
 */

        /*
         * 🧒 STREAMS ARE LIKE TOY SORTING MACHINES! 🚂
         * (This is a general explanation about Streams API)
         */

        // Create a list of numbers
        List<Integer> Array1 = Arrays.asList(5, 1, 2, 3, 8, 9, 6, 7);

        // FIRST WAY: Traditional for-loop
        // - Using index (i) to access each element
        // - Prints each number one by one
        for (int i = 0; i < Array1.size(); i++) {
            System.out.println(Array1.get(i));
        }
        System.out.println("-----");

        // SECOND WAY: Enhanced for-loop
        // - Simpler syntax, no index needed
        // - Still prints each number one by one
        for (int n : Array1) {
            System.out.println(n);
        }
        System.out.println("-----");

        // THIRD WAY: Using forEach with lambda
        // - This is using Stream-like syntax
        // - The shortest way to print all elements
        Array1.forEach(n -> System.out.println(n));
    }
}
