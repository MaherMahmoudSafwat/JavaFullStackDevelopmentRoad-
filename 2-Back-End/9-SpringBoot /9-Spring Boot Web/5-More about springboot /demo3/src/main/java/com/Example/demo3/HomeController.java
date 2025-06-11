package com.Example.demo3;

// Import necessary Spring Framework classes
import org.springframework.stereotype.Controller; // Marks this class as a Spring MVC Controller
import org.springframework.ui.Model; // Used to pass data to the view
import org.springframework.web.bind.annotation.ModelAttribute; // Binds a method return value to a named model attribute
import org.springframework.web.bind.annotation.RequestMapping; // Maps web requests to handler methods
import jakarta.servlet.http.HttpServletRequest; // Provides access to HTTP request information
import jakarta.servlet.http.HttpSession; // Provides a way to identify a user across requests
import org.springframework.web.bind.annotation.RequestParam; // Binds request parameters to method arguments
import org.springframework.web.servlet.ModelAndView; // Holder for both Model and View in web MVC

@Controller // Indicates this class is a Spring Controller that handles web requests
public class HomeController {

    /**
     * @ModelAttribute Method - Executes before every controller method
     * Adds an attribute named "Course" with value "Java" to every model
     * This data will be available to all views returned by this controller
     */
    @ModelAttribute("Course")
    public String CourseNAme() {
        return "Java"; // This value will be accessible in views as ${Course}
    }

    /**
     * Handles the root ("/") URL request
     * @return "index" - resolves to index.jsp (or index.html depending on configuration)
     */
    @RequestMapping("/")
    public String home() {
        return "index"; // Returns the view name (template) to render
    }

    /**
     * First version of add method - Using Model
     * @param X - First number from request parameter "num1"
     * @param Y - Second number from request parameter "num2"
     * @param model - Spring's Model interface to pass data to view
     * @return "results" - view name showing the calculation result
     */
    @RequestMapping("add")
    public String add1(@RequestParam("num1") int X, @RequestParam("num2") int Y, Model model) {
        int result = X + Y; // Calculate sum
        model.addAttribute("result", result); // Add result to model (accessible in view as ${result})
        return "results"; // Render results.jsp (or results.html)
    }

    /**
     * Second version of add method - Using ModelAndView
     * @param X - First number from request parameter "num1"
     * @param Y - Second number from request parameter "num2"
     * @param MV - ModelAndView object that combines model data and view name
     * @return MV - Contains both the result data and view name
     */
    @RequestMapping("add")
    public ModelAndView Add(@RequestParam("num1") int X, @RequestParam("num2") int Y, ModelAndView MV) {
        int result = X + Y;
        MV.addObject("result", result); // Add data to model
        MV.setViewName("results"); // Set view name
        return MV; // Return the combined ModelAndView object
    }

    /**
     * Handles Alien object submission
     * @param alien - Spring automatically binds form fields to Alien object properties
     * @return "results" - view name to show the submitted alien data
     */
    @RequestMapping("addAlien")
    public String addAlien(Alien alien) {
        // Spring automatically adds the alien object to the model
        // Accessible in view as ${alien.propertyName}
        return "results";
    }
}


