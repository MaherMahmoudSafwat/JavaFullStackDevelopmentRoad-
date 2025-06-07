package org.example.Configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// This class is a Spring Configuration class that enables component scanning
@Configuration  // Indicates that this class contains Spring bean definitions and configurations
@ComponentScan("org.example")  // Specifies the base package to scan for Spring components
public class AppConfigurations {
    // This is a minimal configuration class that relies on component scanning

    /*
     * Key features of this configuration:
     * 1. @Configuration marks this as a Spring configuration class
     * 2. @ComponentScan tells Spring to look for components in "org.example" package and its sub-packages
     * 3. It will automatically detect classes annotated with:
     *    - @Component
     *    - @Service
     *    - @Repository
     *    - @Controller
     *    - etc.
     * 4. No explicit @Bean definitions are needed here as we're using component scanning
     */
}
