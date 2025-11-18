package CloudProject.example.CloudProjects.HelloController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @GetMapping("/")
    public String GreetingHelloWorld()
    {
        return "Hello World";
    }
    @GetMapping("/Info")
    public String GetApplicationProperties()
    {
        String Os = System.getProperty("os.name");
        String Version = System.getProperty("os.version");
        String Architecture = System.getProperty("os.arch");
        String JavaVersion = System.getProperty("java.version");
        String User = System.getProperty("user.name");

        return "OS: " + Os + ". Version: " +Version+", Architecture: "+Architecture
        +", Java Version: "+JavaVersion+", User: "+User;
    }
}
