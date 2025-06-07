package org.example;

// Spring Framework annotations
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Mobile class representing a mobile device implementation of the Device interface.
 * This is a Spring-managed component with prototype scope and primary preference.
 */
@Component // Identifies this class as a Spring component (bean) to be automatically
// detected and registered in the Spring application context during component scanning
@Primary   // Indicates that this bean should be given preference when multiple candidates
// exist for dependency injection (when injecting the Device interface)
@Scope("prototype") // Specifies that a new instance should be created each time this bean is requested
// (as opposed to singleton scope where the same instance would be reused)
public class Mobile implements Device {

    /**
     * Constructor for Mobile class.
     * Prints a message to console when a new instance is created.
     * This helps demonstrate prototype scope behavior (multiple instances will be created).
     */
    public Mobile() {
        System.out.println("The mobile object has been created");
    }

    /**
     * Implementation of the Code() method from the Device interface.
     * Demonstrates mobile-specific behavior by simulating WhatsApp message sending.
     */
    public void Code() {
        System.out.println("Sending Whatsapp Messages...");
    }
}
