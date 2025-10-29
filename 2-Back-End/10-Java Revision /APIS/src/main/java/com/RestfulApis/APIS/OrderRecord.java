/*
 * ===========================================================================
 * RECORD CLASS - Modern Java Alternative
 * ===========================================================================
 */
package com.RestfulApis.APIS;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * RECORDS vs TRADITIONAL CLASSES:
 *
 * RECORDS (Java 14+):
 * - Immutable by default (final fields)
 * - Automatic getters, toString(), equals(), hashCode()
 * - Concise syntax - one line declaration
 * - Cannot extend other classes
 * - Perfect for data transfer objects (DTOs)
 * - Thread-safe due to immutability
 *
 * TRADITIONAL CLASSES:
 * - Mutable fields (can be changed after creation)
 * - Manual getters/setters required
 * - More verbose but more flexible
 * - Can extend other classes
 * - Can add custom logic in setters
 * - Better for entities that need to be modified
 *
 * WHEN TO USE WHICH?
 * - Use Records for: DTOs, value objects, API responses, immutable data
 * - Use Classes for: Entities, objects that need modification, complex business logic
 */

/*
 * This record automatically provides:
 * - private final fields
 * - public accessor methods: orderId(), name(), price()
 * - toString(), equals(), hashCode() methods
 * - public constructor with all parameters
 */

public record OrderRecord(
        @JsonProperty("O-I") int orderId,    // Note: accessor is orderId(), not getOrderId()
        @JsonProperty("O-N") String name,    // Note: accessor is name(), not getName()
        @JsonProperty("O-P") int price       // Note: accessor is price(), not getPrice()
) {

        /*
         * You can add validation in records using compact constructor:
         */
        public OrderRecord {
                if (orderId <= 0) {
                        throw new IllegalArgumentException("Order ID must be positive");
                }
                if (name == null || name.trim().isEmpty()) {
                        throw new IllegalArgumentException("Name cannot be null or empty");
                }
                if (price < 0) {
                        throw new IllegalArgumentException("Price cannot be negative");
                }
        }

        /*
         * You can add additional methods to records:
         */
        public String getDisplayName() {
                return name.toUpperCase();
        }

        public double getPriceWithTax() {
                return price * 1.1; // 10% tax
        }
}

/*
 * ===========================================================================
 * USAGE EXAMPLE IN CONTROLLER
 * ===========================================================================
 */

/*
 * Using Record in Controller:
 *
 * @PostMapping("/record-orders")
 * public OrderRecord createOrderRecord(@RequestBody OrderRecord order) {
 *     // Records are immutable, so you'd typically store and return as-is
 *     // or create a new record with different values
 *     return order;
 * }
 *
 * JSON Request/Response with Record:
 * {
 *   "O-I": 123,
 *   "O-N": "Laptop",
 *   "O-P": 999
 * }
 *
 * Accessing record data: order.orderId(), order.name(), order.price()
 * vs Class data: order.getOrderId(), order.getName(), order.getPrice()
 */

/*
 * ===========================================================================
 * ADDITIONAL IMPORTANT SPRING ANNOTATIONS
 * ===========================================================================
 *
 * VALIDATION ANNOTATIONS:
 * @Valid - enables validation on method parameters
 * @Validated - enables validation on class level
 * @NotNull, @NotEmpty, @NotBlank - null/empty checks
 * @Min, @Max - numeric range validation
 * @Size - string/collection size validation
 * @Email - email format validation
 * @Pattern - regex validation
 *
 * SPRING MVC ANNOTATIONS:
 * @RequestHeader - extract HTTP headers
 * @CookieValue - extract cookie values
 * @SessionAttribute - access session attributes
 * @ModelAttribute - bind form data to objects
 * @CrossOrigin - enable CORS for frontend integration
 *
 * EXCEPTION HANDLING:
 * @ExceptionHandler - handle specific exceptions
 * @ControllerAdvice - global exception handling
 * @ResponseStatus - set status code for exceptions
 *
 * CONFIGURATION:
 * @Configuration - configuration class
 * @Bean - define Spring beans
 * @Value - inject property values
 * @Autowired - dependency injection
 * @Qualifier - specify which bean to inject
 *
 * EXAMPLE GLOBAL EXCEPTION HANDLER:
 *
 * @ControllerAdvice
 * public class GlobalExceptionHandler {
 *
 *     @ExceptionHandler(IllegalArgumentException.class)
 *     @ResponseStatus(HttpStatus.BAD_REQUEST)
 *     public String handleBadRequest(IllegalArgumentException ex) {
 *         return "Bad request: " + ex.getMessage();
 *     }
 *
 *     @ExceptionHandler(Exception.class)
 *     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
 *     public String handleGeneral(Exception ex) {
 *         return "Server error: " + ex.getMessage();
 *     }
 * }
 */