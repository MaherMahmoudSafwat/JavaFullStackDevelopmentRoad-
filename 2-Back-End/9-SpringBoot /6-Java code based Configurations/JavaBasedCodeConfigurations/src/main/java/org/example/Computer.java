package org.example;

// Spring Framework annotations
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Computer class representing a computer device implementation of the Device interface.
 * This is a Spring-managed component with a custom bean name.
 */
@Component("Com2")  // Registers this class as a Spring bean with the explicit name "Com2"
// (If no name was specified, the default would be "computer")
public class Computer implements Device {

    /**
     * Constructor for Computer class.
     * Prints a message to console when a new instance is created.
     * This helps demonstrate when the bean is instantiated by Spring.
     */
    public Computer() {
        System.out.println("Computer object has been created");
    }

    /**
     * Implementation of the Code() method from the Device interface.
     * Demonstrates computer-specific behavior by showing coding activity.
     */
    public void Code() {
        System.out.println("Student is coding right now...");
    }
}


