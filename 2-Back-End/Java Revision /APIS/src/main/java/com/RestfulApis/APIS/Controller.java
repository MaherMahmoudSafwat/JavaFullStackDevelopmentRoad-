/*
 * ===========================================================================
 * COMPREHENSIVE SPRING BOOT REST API EXPLANATION
 * ===========================================================================
 *
 * This code demonstrates a complete Spring Boot RESTful web service.
 * Let's break down every concept step by step:
 *
 * 1. WHAT IS HTTP?
 * HTTP (HyperText Transfer Protocol) is the foundation of web communication.
 * - It's a protocol that defines how messages are formatted and transmitted
 * - It works on a request-response model: client sends request, server sends response
 * - HTTP is stateless: each request is independent
 * - Uses port 80 by default
 *
 * 2. WHAT IS HTTPS?
 * HTTPS (HTTP Secure) is HTTP with encryption:
 * - Uses SSL/TLS encryption to secure data transmission
 * - Protects against eavesdropping and man-in-the-middle attacks
 * - Uses port 443 by default
 * - Required for sensitive data like passwords, credit cards
 *
 * 3. HTTP METHODS (VERBS):
 * - GET: Retrieve data from server (safe, idempotent)
 * - POST: Send data to server to create new resource
 * - PUT: Update/replace entire resource
 * - DELETE: Remove a resource
 * - PATCH: Partial update of a resource
 * - HEAD: Like GET but only returns headers
 * - OPTIONS: Get allowed methods for a resource
 *
 * 4. WHAT ARE RESTful APIs?
 * REST (Representational State Transfer) is an architectural style for web services:
 * - Uses standard HTTP methods (GET, POST, PUT, DELETE)
 * - Resources are identified by URLs
 * - Stateless: each request contains all necessary information
 * - Uses standard HTTP status codes
 * - Data exchange typically in JSON format
 * - Cacheable responses
 * - Uniform interface
 *
 * Example:
 * GET /api/orders/123 - Get order with ID 123
 * POST /api/orders - Create new order
 * PUT /api/orders/123 - Update entire order 123
 * DELETE /api/orders/123 - Delete order 123
 *
 * 5. HTTP STATUS CODES:
 * These codes tell the client what happened with their request:
 *
 * 1xx (Informational):
 * - 100 Continue - Server received headers, client can send body
 * - 101 Switching Protocols - Server switching to different protocol
 *
 * 2xx (Success):
 * - 200 OK - Request successful
 * - 201 Created - Resource successfully created
 * - 202 Accepted - Request accepted but not yet processed
 * - 204 No Content - Successful but no content to return
 *
 * 3xx (Redirection):
 * - 301 Moved Permanently - Resource permanently moved to new URL
 * - 302 Found - Resource temporarily moved
 * - 304 Not Modified - Resource hasn't changed (caching)
 *
 * 4xx (Client Error):
 * - 400 Bad Request - Invalid request syntax
 * - 401 Unauthorized - Authentication required
 * - 403 Forbidden - Server refuses to authorize
 * - 404 Not Found - Resource doesn't exist
 * - 405 Method Not Allowed - HTTP method not supported
 * - 422 Unprocessable Entity - Validation errors
 *
 * 5xx (Server Error):
 * - 500 Internal Server Error - Generic server error
 * - 502 Bad Gateway - Invalid response from upstream server
 * - 503 Service Unavailable - Server temporarily unavailable
 *
 * 6. WHAT IS SPRING MVC?
 * Spring MVC (Model-View-Controller) is a web framework within Spring:
 * - Model: Data and business logic (your Java objects like Orders)
 * - View: User interface (HTML, JSON response)
 * - Controller: Handles requests and coordinates Model and View
 * - DispatcherServlet: Front controller that routes requests
 * - Uses annotations to simplify configuration
 */

package com.RestfulApis.APIS;

// Spring Framework imports for web functionality
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// Additional useful imports you might need:
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.ArrayList;

/*
 * @RestController Annotation:
 * - Combines @Controller and @ResponseBody
 * - Tells Spring this class handles HTTP requests
 * - Automatically converts return values to JSON/XML
 * - This is where Spring MVC comes into play - this class IS the Controller
 * in the MVC pattern
 *
 * Alternative annotations:
 * @Controller - for traditional MVC with view templates
 * @Service - for business logic layer
 * @Repository - for data access layer
 * @Component - generic Spring component
 */
@RestController
// @RequestMapping("/api/v1") // Optional: adds base path to all endpoints
// @Validated // Enables method-level validation
public class Controller {

    // Simple in-memory storage for demonstration
    private List<Orders> ordersList = new ArrayList<>();

    /*
     * GET Endpoint - Retrieves data
     *
     * @GetMapping: Maps HTTP GET requests to this method
     * - Shortcut for @RequestMapping(method = RequestMethod.GET)
     * - GET requests should be safe (no side effects) and idempotent
     *
     * @ResponseStatus: Sets the HTTP status code for successful responses
     * - HttpStatus.ACCEPTED (202) means request accepted but processing not complete
     * - More commonly you'd use HttpStatus.OK (200) for simple GET requests
     *
     * Why GET? Used to retrieve/read data without modifying server state
     */
    @GetMapping // Maps to GET /
    @ResponseStatus(HttpStatus.ACCEPTED) // Returns 202 status code
    public String SayHello() {
        return "hello World"; // This string gets converted to JSON automatically
    }

    /*
     * Alternative GET endpoint showing best practices:
     */
    @GetMapping("/orders") // Maps to GET /orders
    @ResponseStatus(HttpStatus.OK) // 200 is more appropriate for successful retrieval
    public List<Orders> getAllOrders() {
        return ordersList; // Returns JSON array of orders
    }

    /*
     * POST Endpoint #1 - Sending data in Request Body
     *
     * @PostMapping: Maps HTTP POST requests to this method
     * - POST is used to create new resources or submit data
     * - Not idempotent (calling multiple times may have different effects)
     *
     * @RequestBody:
     * - Binds HTTP request body to method parameter
     * - Spring automatically deserializes JSON to Java object
     * - Content-Type header should be application/json
     * - The entire request body becomes the parameter value
     *
     * Example request:
     * POST /Post-Message
     * Content-Type: application/json
     * Body: "Hello from client"
     */
    @PostMapping("/Post-Message") // Maps to POST /Post-Message
    public String SendMessage(@RequestBody String Message) {
        return "Message has been Accepted and sent " + Message;
    }

    /*
     * POST Endpoint #2 - Path Variable
     *
     * @PathVariable:
     * - Extracts values from the URL path
     * - The value in {} becomes a method parameter
     * - More RESTful for resource identification
     * - URL becomes part of the resource identifier
     *
     * Example request:
     * POST /Post-Message/HelloWorld
     * (No request body needed, message is in URL)
     */
    @PostMapping("/Post-Message/{Message}") // {Message} is a placeholder
    public String SendMessageInURL(@PathVariable("Message") String Message) {
        // @PathVariable("Message") maps the {Message} from URL to the parameter
        return "Message has been Accepted and sent " + Message;
    }

    /*
     * POST Endpoint #3 - Query Parameters
     *
     * @RequestParam:
     * - Extracts query parameters from URL
     * - Parameters come after ? in URL
     * - Multiple parameters separated by &
     * - Good for optional parameters, filtering, pagination
     *
     * Example request:
     * POST /Post-Message-ByQuery?Ms=Hello&M111=World
     *
     * Optional parameters:
     * @RequestParam(value = "Ms", required = false, defaultValue = "default")
     */
    @PostMapping("/Post-Message-ByQuery")
    public String SendMessageInURLByQuery(
            @RequestParam("Ms") String Message,    // Gets ?Ms=value
            @RequestParam("M111") String M1        // Gets &M111=value
    ) {
        return "Message has been Accepted and sent " + Message + " and " + M1;
    }

    /*
     * POST Endpoint #4 - Complex Object in Request Body
     *
     * This demonstrates sending a complete Java object as JSON
     * Spring automatically converts JSON to Orders object
     *
     * Example request:
     * POST /Post-Orders
     * Content-Type: application/json
     * Body: {
     *   "O-I": 123,
     *   "O-N": "Laptop",
     *   "O-P": 999
     * }
     */
    @PostMapping("/Post-Orders")
    public String SendOrders(@RequestBody Orders Orders) {
        // @RequestBody automatically deserializes JSON to Orders object
        ordersList.add(Orders); // Add to our in-memory list
        return "Message has been Accepted and sent " + Orders.toString();
    }

    /*
     * Additional useful endpoints demonstrating best practices:
     */

    // PUT endpoint for updating entire resource
    @PutMapping("/orders/{id}")
    public ResponseEntity<Orders> updateOrder(
            @PathVariable("id") int orderId,
            @RequestBody  Orders updatedOrder) {

        // ResponseEntity gives you full control over the HTTP response
        updatedOrder.setOrderId(orderId);
        return ResponseEntity.ok(updatedOrder); // Returns 200 with the order
    }

    // DELETE endpoint
    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 - successful deletion, no content
    public void deleteOrder(@PathVariable("id") int orderId) {
        // Delete logic here
        ordersList.removeIf(order -> order.getOrderId() == orderId);
    }

    // GET with path variable - retrieve specific resource
    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable("id") int orderId) {
        // Find order logic here
        Orders found = ordersList.stream()
                .filter(order -> order.getOrderId() == orderId)
                .findFirst()
                .orElse(null);

        if (found != null) {
            return ResponseEntity.ok(found); // 200 + order data
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }
}