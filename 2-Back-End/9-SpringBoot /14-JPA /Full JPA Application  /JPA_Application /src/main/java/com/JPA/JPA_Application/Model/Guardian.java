package com.JPA.JPA_Application.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Guardian - An embeddable component representing guardian information.
 *
 * This value object will be embedded within parent entities (like Student)
 * and its fields will be stored as columns in the parent entity's table.
 *
 * Demonstrates JPA's @Embeddable functionality and column customization.
 */
@Data // Lombok: Generates getters, setters, toString(), equals(), hashCode()
@AllArgsConstructor // Lombok: Constructor with all fields
@NoArgsConstructor // Lombok: No-args constructor (required by JPA)
@Embeddable // JPA: Marks this as embeddable (not a standalone entity)
@AttributeOverrides({ // Overrides default column names for embedded fields
        @AttributeOverride(
                name = "Name", // Original field name in this class
                column = @Column(name = "GuardianName", length = 100) // Custom column definition
        ),
        @AttributeOverride(
                name = "Email",
                column = @Column(name = "GuardianEmail", length = 100, nullable = false)
        ),
        @AttributeOverride(
                name = "Mobile",
                column = @Column(name = "GuardianMobile", length = 20)
        )
})
public class Guardian {

    /**
     * Guardian's full name
     * Will be stored in parent table as 'GuardianName' due to @AttributeOverride
     */
    private String Name;

    /**
     * Guardian's email address
     * Has additional constraints through @Column in @AttributeOverride
     */
    private String Email;

    /**
     * Guardian's mobile number
     * Custom column name and length specified
     */
    private String Mobile;
}