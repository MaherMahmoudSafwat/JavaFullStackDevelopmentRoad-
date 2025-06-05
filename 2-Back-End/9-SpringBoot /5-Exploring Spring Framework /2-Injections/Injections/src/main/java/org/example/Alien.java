package org.example;

import java.beans.ConstructorProperties;

/**
 * Alien class - Demonstrates Spring dependency injection concepts
 * This class shows both constructor AND setter injection examples
 */
public class Alien {

    // Field declarations
    private int age;        // Primitive field for age
    private Computer com;   // Reference field for Computer dependency

    // ===== CONSTRUCTORS =====

    /**
     * No-arg constructor - Used when Spring creates beans with setter injection
     * (Spring can call this and then use setters to configure the object)
     */
    public Alien() {
        // System.out.println("Object Created"); // Debugging line
    }

    /**
     * Parameterized constructor - Used for constructor injection
     * @ConstructorProperties helps Spring match parameter names when using XML config
     *
     * @param age The age value to set
     * @param lap The Computer implementation to inject (named 'lap' in XML)
     */
    @ConstructorProperties({"age", "lap"})
    public Alien(int age, Computer lap) {
        System.out.println("Parameterized Constructor Called");
        this.age = age;
        this.com = lap;
    }

    // ===== GETTERS AND SETTERS =====

    /**
     * Getter for age - Standard getter method
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter for age - Used for setter injection
     * Spring will call this when using <property name="age"> in XML
     */
    public void setAge(int age) {
        // System.out.println("Setter called"); // Debugging line
        this.age = age;
    }

    /**
     * Getter for Computer - Standard getter
     */
    public Computer getCom() {
        return com;
    }

    /**
     * Setter for Computer - Used for setter injection
     * Spring will call this when using <property name="com"> in XML
     * Note: The property name is "com" (field name), not "lap"
     */
    public void setCom(Computer com) {
        this.com = com;
    }

    // ===== BUSINESS METHOD =====

    /**
     * Example method showing usage of the injected dependency
     */
    public void code() {
        System.out.println("Coding");
        com.compile();  // Using the injected Computer implementation
    }
}




