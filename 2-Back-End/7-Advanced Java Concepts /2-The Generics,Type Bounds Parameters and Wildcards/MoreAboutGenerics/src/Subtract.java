/**
 * A generic class that performs subtraction on two numbers of potentially different types.
 * Both types must extend Number (Integer, Double, Float, etc.).
 *
 * @param <T> The type of the first number (must extend Number)
 * @param <R> The type of the result (must extend Number)
 *
 * Example usage:
 * Subtract<Integer, Integer> intSubtract = new Subtract<>(5, 3);
 * Integer result = intSubtract.GetTheResult(); // Returns 2
 *
 * Subtract<Double, Float> mixedSubtract = new Subtract<>(5.5, 2.2);
 * Float result = mixedSubtract.GetTheResult(); // Returns 3.3f
 */
public class Subtract<T extends Number, R extends Number> {
    private T Number1;
    private T Number2;

    /**
     * Constructor that initializes both numbers
     * @param Number1 First number of type T
     * @param Number2 Second number of type T
     */
    public Subtract(T Number1, T Number2) {
        this.Number1 = Number1;
        this.Number2 = Number2;
    }

    /** Default constructor */
    public Subtract() {
        // Allows creating instance without initial numbers
    }

    /**
     * Sets the first number
     * @param Number1 The number to set as first operand
     */
    public void GetFirstNumber(T Number1) {
        this.Number1 = Number1;
    }

    /**
     * Sets the second number
     * @param Number2 The number to set as second operand
     */
    public void GetSecondNumber(T Number2) {
        this.Number2 = Number2;
    }

    /**
     * Performs subtraction and returns the result as type R
     * @return The result of subtraction (Number1 - Number2)
     *
     * @SuppressWarnings("unchecked") is used because we're doing safe casts
     * after instanceof checks, but Java can't verify this at compile time
     */
    @SuppressWarnings("unchecked")
    public R GetTheResult() {
        // Handle Double specifically to maintain precision
        if (Number1 instanceof Double && Number2 instanceof Double) {
            return (R) Double.valueOf(Number1.doubleValue() - Number2.doubleValue());
        }
        // Handle Float specifically
        else if (Number1 instanceof Float && Number2 instanceof Float) {
            return (R) Float.valueOf(Number1.floatValue() - Number2.floatValue());
        }
        // Default to Integer for other Number types (Long, Integer, Short, Byte)
        return (R) Integer.valueOf(Number1.intValue() - Number2.intValue());
    }
}



