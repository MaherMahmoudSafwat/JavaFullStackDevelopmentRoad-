package JWT.example.JWT_Tutorials.Controller;


import JWT.example.JWT_Tutorials.JWTTokens.JwtService;
import JWT.example.JWT_Tutorials.Models.PersonStudents;
import JWT.example.JWT_Tutorials.Models.Users;
import JWT.example.JWT_Tutorials.UserServices.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService JwtServices;
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

    // 🔐 METHOD: authenticateUser()
    // 📝 PURPOSE: Manually authenticates a user with username and password
    // 🎯 WHY: Used in login endpoints to verify user credentials
    // 🔧 HOW: Uses AuthenticationManager to validate credentials
    // 📌 USAGE: Called when user tries to log in
    @PostMapping("/login")
    public String Login(@RequestBody Users user) {
        // 🎯 ACTION: Attempts to authenticate user credentials
        Authentication authentication = authenticationManager.authenticate
                (
                // 🔑 CREATES: Authentication token with user credentials
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),  // 📧 INPUT: Username provided by user
                        user.getPassword()   // 🔒 INPUT: Password provided by user (in plain text)
                )
                );
        // 💡 AFTER SUCCESS:
        // ✅ Credentials are valid
        // ✅ User details and authorities are loaded
        // ✅ Authentication object contains user identity and roles
        // ❌ If fails: Throws AuthenticationException (BadCredentialsException, etc.)
        if(authentication.isAuthenticated())
        {
            return JwtServices.GenerateToken(user.getUsername());
        }
        else
        {
            return "Login failed";
        }
    }
}
