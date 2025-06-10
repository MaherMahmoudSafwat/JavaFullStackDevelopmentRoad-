package com.example.demo;

// Import necessary Spring and Jakarta EE classes
import org.springframework.stereotype.Controller;  // Annotation to mark this as a Controller class
import org.springframework.web.bind.annotation.RequestMapping;  // For mapping web requests
import jakarta.servlet.http.HttpServletRequest;  // For accessing HTTP request information
import jakarta.servlet.http.HttpSession;  // For working with user sessions

@Controller  // This tells Spring this class handles web requests
public class HomeController {

    // This method handles requests to the root URL ("/")
    @RequestMapping("/")
    public String home() {
        // When someone visits the homepage, return the index.jsp view
        // System.out.println("Home method called");  // (Commented out debug statement)
        return "index.jsp";  // Shows the index.jsp page to the user
    }

    // This method handles requests to "/add" URL
    @RequestMapping("add")
    public String add(HttpServletRequest req, HttpSession session) {
        // This method adds two numbers from the request and stores the result in session

        // Step 1: Get the two numbers from the request parameters
        // - req.getParameter() gets values sent from a form or URL
        // - Integer.parseInt() converts the String input to an integer
        int num1 = Integer.parseInt(req.getParameter("num1"));
        int num2 = Integer.parseInt(req.getParameter("num2"));

        // Step 2: Calculate the sum of the two numbers
        int result = num1 + num2;

        // Step 3: Store the result in the session
        // - Sessions allow data to persist between multiple requests from the same user
        // - "result" is the name we'll use to retrieve this value later
        session.setAttribute("result", result);

        // Step 4: Show the result.jsp page to display the calculation
        return "result.jsp";
    }
}
