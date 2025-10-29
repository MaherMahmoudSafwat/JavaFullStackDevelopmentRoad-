package com.example.demo.Controller;

import com.example.demo.Models.PersonStudents;
import com.example.demo.Models.Users;
import com.example.demo.UserServices.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ControllerSecurityTest
{
    private UserService userService;

    public ControllerSecurityTest(UserService userService)
    {
        this.userService = userService;
    }
    List<PersonStudents> ListOfStudents = new ArrayList<>();
    PersonStudents Students = new PersonStudents
            (
                    123,
                    "Samir",
                    "1234",
                    "Samira@gmail.com"
            );
    public void AddNewStudents()
    {
        ListOfStudents.add(Students);
    }
    @GetMapping("/Login")
    public String SendSayHelloWorld(HttpServletRequest Request)
    {
        return "Hello" + Request.getSession().getId();
    }
    @GetMapping("/_CSRF")
    public CsrfToken GetCSRFTokens(HttpServletRequest Request)
    {
        return (CsrfToken) Request.getAttribute("_csrf");
    }
    @GetMapping("/ShowAllStudents")
    public List<PersonStudents>ShowAllPersonStudents()
    {
        AddNewStudents();
        return ListOfStudents;
    }
    @PostMapping("/update")
    public void AddNewPersonStudents(@RequestBody PersonStudents PersonStudents)
    {
        ListOfStudents.add(PersonStudents);
    }
    @PostMapping("/Register")
    public void AddNewUser(@RequestBody Users user)
    {
        userService.AddNewUserSaveUsers(user);
    }
}
