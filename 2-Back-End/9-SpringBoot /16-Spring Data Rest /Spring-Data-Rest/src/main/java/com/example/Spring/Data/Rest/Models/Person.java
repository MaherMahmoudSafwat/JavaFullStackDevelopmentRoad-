package com.example.Spring.Data.Rest.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person
{
    @Id
    private Integer UserId;
    private String UserName;
    private String UserEmail;
    private String UserPassword;
}
