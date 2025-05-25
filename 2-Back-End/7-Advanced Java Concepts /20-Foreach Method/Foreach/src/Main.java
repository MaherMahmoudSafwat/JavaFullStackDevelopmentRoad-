import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args)
    {
        List<Integer>Numbers = Arrays.asList(1,2,3,4,5,6,7);

        /*   Consumer <Integer> Con = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };*/

        /*
         * LAMBDA EXPRESSION EXPLANATION:
         * This is a simplified version of the Consumer using lambda
         * - 'Con' is a Consumer that takes an Integer 'n'
         * - The arrow '->' separates parameter from implementation
         * - It's equivalent to the anonymous class above but shorter
         */
        Consumer <Integer> Con = (n) -> System.out.println(n);

        /*
         * forEach METHOD EXPLANATION:
         * - forEach takes a Consumer as parameter
         * - It applies the Consumer to each element in the list
         * - Here we're using a lambda directly as the Consumer
         * - The lambda (n) -> System.out.println(n) means:
         *   "For each element 'n', print it"
         */
        Numbers.forEach((n) -> System.out.println(n));

        /*
         * ALTERNATIVE WAY USING OUR CONSUMER:
         * We could also write:
         * Numbers.forEach(Con);
         * Which would use our predefined Consumer
         */
        Numbers.stream().reduce()
    }
}



