package com.example.demo.Models;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonStudents
{
    private Integer Id;
    private String Name;
    private String Password;
    private String Email;
}
