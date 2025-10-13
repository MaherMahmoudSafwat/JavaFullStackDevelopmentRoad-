package com.Survey.FullSurveyProject.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "users_data",
        indexes = {
                @Index(name = "idx_id", columnList = "id"),
                @Index(name = "idx_username", columnList = "name"),
                @Index(name = "idx_email", columnList = "email")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "users_sequence",
            allocationSize = 1
    )
    @Column(name = "Id")
    private Integer userId;

    // FIXED: Added + to match one or more characters
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "UserName is not valid")
    @Column(name = "Name", unique = false, nullable = false)
    private String userName;

    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Email is not valid")
    @Column(name = "Email", nullable = false, unique = true) // Changed to unique = true
    private String userEmail;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$%^&*()_]).{8,}$",
            message = "Password must be at least 8 characters with uppercase, lowercase, digit, and special character")
    @Column(name = "Password", unique = false, nullable = false)
    private String userPassword;

    @Embedded
    private UsersImages userImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Surveys> surveys = new ArrayList<>();
}