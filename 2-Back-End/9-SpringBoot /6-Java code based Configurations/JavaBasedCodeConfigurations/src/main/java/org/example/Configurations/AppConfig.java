package org.example.Configurations;

import org.example.Computer;
import org.example.Device;
import org.example.Mobile;
import org.example.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

// This class is a Spring Configuration class that defines beans and their dependencies
@Configuration
public class AppConfig {

    // Defines a Student bean with multiple names and prototype scope
    // Prototype scope means a new instance is created each time the bean is requested
    @Bean(name = {"St1", "St2", "St3"})
    @Scope("prototype")
    public Student student() {
        // Creates and returns a new Student instance with default values
        return new Student();
    }

    // Defines another Student bean with prototype scope
    // This bean has custom properties set and depends on a Device implementation
    @Bean
    @Scope("prototype")
    // The commented line shows how to use @Qualifier to specify which Device implementation to inject
    //public Student Student1(@Qualifier("Computer1") Device Com)
    public Student Student1(Device Com) {
        // Creates a new Student instance
        Student St = new Student();
        // Sets the student's age to 23
        St.setStudentAge(23);
        // Injects the Device dependency (either Computer or Mobile)
        St.setCom(Com);
        return St;
    }

    // Defines a Computer bean (implements Device interface)
    // By default, this bean will have singleton scope (only one instance per Spring container)
    @Bean
    public Computer Computer1() {
        // Creates and returns a new Computer instance
        return new Computer();
    }

    // Defines a Mobile bean (implements Device interface) and marks it as primary
    // @Primary indicates this bean should be given preference when multiple candidates
    // are available for autowiring
    @Bean
    @Primary
    public Mobile Mobile1() {
        // Creates and returns a new Mobile instance
        return new Mobile();
    }
}
