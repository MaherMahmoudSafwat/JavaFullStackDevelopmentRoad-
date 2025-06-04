package com.example.SpringBootFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// This annotation marks the class as a Spring "component"
// Spring will automatically create an object of this class (a "bean")
@Component
public class Programmer {

    // This is Dependency Injection (DI) in action!
    // @Autowired tells Spring: "Find a Laptop bean and insert it here automatically"
    // No need to do: Laptop L1 = new Laptop();
    @Autowired
    Laptop L1;

    public void code() {
        L1.coding(); // Using the injected Laptop
        System.out.println("Coding...");
    }
}