/**
 * A generic class that holds a single element of any type.
 * This demonstrates basic generic class usage in Java.
 *
 * @param <T> The type of element this class will hold
 *            (can be any reference type - String, Integer, custom class, etc.)
 *
 * Example usage:
 * Pairs<String> stringPair = new Pairs<>();
 * stringPair.setP1("Hello");
 * String value = stringPair.getP1();
 */
public class Pairs<T> {
    /**
     * Field to store the element of type T.
     * The actual type will be determined when an instance is created.
     */
    public T P1;  // Public field (though typically we'd make this private in real code)

    /**
     * Sets the value of P1.
     * @param p1 The value to store, must be of type T
     */
    public void setP1(T p1) {
        this.P1 = p1;  // Assigns the parameter value to the field
    }

    /**
     * Gets the stored value.
     * @return The stored value of type T
     */
    public T getP1() {
        return P1;  // Returns whatever value was stored
    }
}