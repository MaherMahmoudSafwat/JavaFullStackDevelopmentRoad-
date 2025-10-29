package com.example.Spring.Data.Rest.PersonsController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController
{
    @GetMapping("/ShowAllStrings")
    public String ShowALLStrings()
    {
        return "Test Customizations with the spring data rest";
    }
}
