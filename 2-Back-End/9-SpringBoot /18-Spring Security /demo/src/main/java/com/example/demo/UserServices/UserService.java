package com.example.demo.UserServices;

import com.example.demo.Models.Users;
import com.example.demo.UserRepository.UserRepository;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    // Dependency: Repository for database operations
    private UserRepository userRepository;

    // ⭐ CRITICAL SECURITY COMPONENT: Password Encoder
    // BCryptPasswordEncoder is a one-way hashing algorithm specifically designed for passwords
    // The number '12' is the COST FACTOR - this controls how slow/secure the hashing is
    // Cost factor 12 means: 2^12 = 4096 iterations of the algorithm (very secure!)
    // Higher cost = more secure but slower performance
    private BCryptPasswordEncoder Encoder = new BCryptPasswordEncoder(12);

    // Constructor: Dependency Injection - Spring will provide the UserRepository
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    // Method to add and save a new user to the database
    // This is where password security magic happens!
    public void AddNewUserSaveUsers(Users user)
    {
        // ⭐ SECURITY STEP: Hash the user's plain text password before saving
        // user.getPassword() returns the plain text password like "mypassword123"
        // Encoder.encode() converts it to a secure hash like "$2a$12$XBBwYp8z8UZJZ..."
        // This hash is ONE-WAY - we can never get the original password back
        String encryptedPassword = Encoder.encode(user.getPassword());

        // Set the encrypted password back to the user object
        // Now instead of storing "mypassword123", we store the secure hash
        user.setPassword(encryptedPassword);

        // Save the user to database with the encrypted password
        // The actual plain text password never touches the database!
        userRepository.save(user);

        // ⭐ WHAT JUST HAPPENED BEHIND THE SCENES:
        // 1. User provided: "mypassword123" (plain text - INSECURE)
        // 2. BCrypt transformed it to: "$2a$12$XBBwYp8z8UZJZ8Z8Z8Z8Z.8z8UZJZ8Z8Z8Z8Z8Z8Z8Z8Z8Z8Z8Z8" (SECURE)
        // 3. Only the hash is stored in database
        // 4. Original password is immediately discarded from memory
    }
}
