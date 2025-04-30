/**
 * This is an interface named 'Calculator'.
 * Interfaces in Java define a contract (methods) that implementing classes must follow.
 * This interface declares a single method for performing addition.
 */
public interface Calculator {

    /**
     * This method calculates the sum of two integers.
     *
     * @param N1 The first integer to be added.
     * @param N2 The second integer to be added.
     * @return The sum of N1 and N2 as an integer.
     */
    public int Sum(int N1, int N2);
}