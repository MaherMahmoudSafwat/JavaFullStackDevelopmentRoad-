package com.example.SpringBootFirstProject;

import org.springframework.stereotype.Component;

// Another Spring component
// When the application starts, Spring will:
// 1. Create an instance of this class
// 2. Store it in the "application context" (like a container of all beans)
// 3. Make it available for @Autowired injection
@Component
public class Laptop {

    public void coding() {
        System.out.println("Start coding on laptop.");
    }
}