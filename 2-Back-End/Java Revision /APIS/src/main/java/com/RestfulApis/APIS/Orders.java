/*
 * ===========================================================================
 * ORDERS CLASS - Traditional Java Class with Getters/Setters
 * ===========================================================================
 */
package com.RestfulApis.APIS;

import com.fasterxml.jackson.annotation.JsonProperty;
// Additional useful Jackson annotations:
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * Traditional Java class representing an Order
 * This follows the JavaBean convention
 */
public class Orders {

    /*
     * @JsonProperty Annotation:
     * - Maps JSON field names to Java field names
     * - Useful when JSON uses different naming conventions
     * - "O-I" in JSON becomes OrderId in Java
     * - Without this, JSON field would need to be "orderId"
     *
     * Other useful validation annotations:
     * @NotNull - field cannot be null
     * @NotEmpty - field cannot be null or empty
     * @Min(1) - minimum value
     * @Max(1000) - maximum value
     * @Size(min=1, max=50) - string length constraints
     * @Email - validates email format
     * @Pattern(regexp="...") - custom regex validation
     */
    @JsonProperty("O-I")
    private int OrderId;

    @JsonProperty("O-N")
    private String Name;

    @JsonProperty("O-P")
    private int Price;

    // @JsonIgnore - excludes field from JSON serialization
    // @JsonFormat(pattern = "yyyy-MM-dd") - for date formatting

    /*
     * WHY DO WE NEED GETTERS AND SETTERS (ACCESSORS)?
     *
     * 1. ENCAPSULATION:
     * - Fields are private, controlled access through methods
     * - Can add validation, logging, or other logic in setters
     * - Can compute values in getters
     *
     * 2. FRAMEWORKS REQUIREMENT:
     * - Spring, Jackson (JSON), Hibernate all use reflection
     * - They look for getter/setter methods to access data
     * - JSON serialization: getters convert object to JSON
     * - JSON deserialization: setters convert JSON to object
     *
     * 3. POSTMAN/UI INTEGRATION:
     * - When you send JSON from Postman, Jackson uses setters to populate object
     * - When returning object to Postman, Jackson uses getters to create JSON
     * - Without getters: object won't be converted to JSON properly
     * - Without setters: JSON won't be converted to object properly
     *
     * 4. JAVABEANS STANDARD:
     * - Standard Java convention for data objects
     * - Tools and frameworks expect this pattern
     * - Enables reflection-based operations
     */

    // Default constructor (required by Jackson and Spring)
    public Orders() {}

    // Constructor with parameters
    public Orders(int orderId, String name, int price) {
        this.OrderId = orderId;
        this.Name = name;
        this.Price = price;
    }

    // Getter methods - allow reading private fields
    public int getOrderId() {
        return OrderId;
    }

    public String getName() {
        return Name;
    }

    public int getPrice() {
        return Price;
    }

    // Setter methods - allow writing to private fields
    public void setOrderId(int orderId) {
        // You can add validation here
        if (orderId <= 0) {
            throw new IllegalArgumentException("Order ID must be positive");
        }
        this.OrderId = orderId;
    }

    public void setName(String name) {
        // You can add validation here
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.Name = name;
    }

    public void setPrice(int price) {
        // You can add validation here
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.Price = price;
    }

    // toString() method for debugging and logging
    @Override
    public String toString() {
        return "Orders{" +
                "OrderId=" + OrderId +
                ", Name='" + Name + '\'' +
                ", Price=" + Price +
                '}';
    }

    // equals() and hashCode() methods (good practice)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Orders orders = (Orders) obj;
        return OrderId == orders.OrderId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(OrderId);
    }
}
