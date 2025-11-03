package OAuth2.example.OAuth2Tutorials.OAuth2Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuth2Controller
{
    @GetMapping("/Hello")
    public String GreetingUsers()
    {
        return "Hello to all users";
    }
}

