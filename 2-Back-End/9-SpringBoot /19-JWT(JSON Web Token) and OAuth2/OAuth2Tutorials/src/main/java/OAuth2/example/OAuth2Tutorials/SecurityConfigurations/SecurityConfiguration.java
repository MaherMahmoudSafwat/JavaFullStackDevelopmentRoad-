//package OAuth2.example.OAuth2Tutorials.SecurityConfigurations;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration
//{
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests
//                        (
//                                // 🎯 AUTHORIZATION RULES: Who can access what?
//                                auth -> auth.anyRequest()        // 🎯 ALL requests to your app
//                                        .authenticated()         // 🎯 REQUIRE login (no public access)
//                        )
//                // 🎯 OAUTH2 LOGIN CONFIGURATION:
//                // "Enable social login (Google, Facebook, etc.)"
//                .oauth2Login(Customizer.withDefaults())
//                // 🎯 What this does:
//                // - Automatically creates "/login" page with OAuth2 buttons
//                // - Handles redirect to Google/Facebook/Github
//                // - Processes the callback from OAuth2 provider
//                // - Creates user session after successful login
//
//                .build();
//    }
//}
