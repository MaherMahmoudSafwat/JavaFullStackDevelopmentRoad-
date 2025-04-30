/**
 * A simple class that performs addition of two integers.
 * Demonstrates basic class structure with constructors, methods, and encapsulation.
 *
 * Example usage:
 * Sum calculator = new Sum(5, 3);
 * int result = calculator.GetTheResult(); // Returns 8
 *
 * Or alternatively:
 * Sum calculator = new Sum();
 * calculator.GetFirstNumber(10);
 * calculator.GetSecondNumber(20);
 * int result = calculator.GetTheResult(); // Returns 30
 */
public class Sum {
    // Private fields to store the numbers (encapsulation)
    private int Number1;
    private int Number2;

    /**
     * Parameterized constructor to initialize both numbers at creation time.
     * @param Number1 The first number to add
     * @param Number2 The second number to add
     */
    public Sum(int Number1, int Number2) {
        // 'this' keyword distinguishes instance variables from parameters
        this.Number1 = Number1;
        this.Number2 = Number2;
    }

    /**
     * Default constructor (no-args) that allows delayed number assignment.
     * Numbers can be set later using GetFirstNumber() and GetSecondNumber().
     */
    public Sum() {
        // Numbers will be initialized to 0 (default int value)
        // Can be set later via setter methods
    }

    /**
     * Sets/updates the first number.
     * @param Number1 The value to set as the first number
     */
    public void GetFirstNumber(int Number1) {
        this.Number1 = Number1;
    }

    /**
     * Sets/updates the second number.
     * @param Number2 The value to set as the second number
     */
    public void GetSecondNumber(int Number2) {
        this.Number2 = Number2;
    }

    /**
     * Calculates and returns the sum of the two numbers.
     * @return The result of adding Number1 and Number2
     */
    public int GetTheResult() {
        return Number1 + Number2;  // Simple arithmetic addition
    }
}


