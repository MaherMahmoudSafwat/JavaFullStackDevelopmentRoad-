package JWT.example.JWT_Tutorials.SecurityConfigurations;

import JWT.example.JWT_Tutorials.JwtFilter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations
{
    // ⭐ THIS GOES IN YOUR SecurityConfig CLASS

    @Autowired
    private UserDetailsService userDetailsService;  // ⭐ Your MyUserDetailsService
    @Autowired
    private JwtFilter JwtFilter;

    @Bean  // ⭐ Spring: "Create and manage this Authentication Provider"
    public AuthenticationProvider authProvider()
    {
        // ⭐ CREATE DaoAuthenticationProvider (handles database authentication)
        // This is the "ID Checker" that verifies username/password against database
        DaoAuthenticationProvider Provider = new DaoAuthenticationProvider();

        // ⭐ CONNECT TO YOUR DATABASE LOOKUP SERVICE
        // This tells the provider: "When checking credentials, use my UserDetailsService"
        // UserDetailsService is YOUR code that loads users from database
        Provider.setUserDetailsService(userDetailsService);

        // ⭐ SET PASSWORD ENCODER (✅ SECURE - BCrypt for production!)
        // This tells the provider: "Use BCrypt to verify passwords"
        // BCryptPasswordEncoder(12) means:
        // - Cost factor 12 = 4096 iterations (very secure!)
        // - One-way hashing (passwords cannot be reversed)
        // - Automatic salt generation (each password hash is unique)
        Provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        // ✅ SECURE: BCrypt is industry standard for password storage
        // ❌ INSECURE ALTERNATIVE: NoOpPasswordEncoder (stores plain text)

        return Provider;
    }
//    @Bean
//    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception
//    {
//        // =========================================================================
//        // PART 1: CREATE CUSTOMIZER TO DISABLE CSRF PROTECTION
//        // =========================================================================
//        // We create a Customizer object specifically for CSRF configuration
//        // Customizer<T> is a functional interface that allows us to customize
//        // specific parts of Spring Security configuration
//        Customizer<CsrfConfigurer<HttpSecurity>> CustomizerCSRF = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                // This method is called when Spring Security is configuring CSRF
//                // httpSecurityCsrfConfigurer is the object that controls CSRF settings
//
//                // .disable() method turns off CSRF protection completely
//                // WHY WE DISABLE CSRF:
//                // - For API testing in Postman (no browser sessions)
//                // - For stateless REST APIs that use token authentication
//                // - During development to simplify testing
//                // - When you don't have browser-based clients
//                httpSecurityCsrfConfigurer.disable();
//
//                // WITHOUT disabling CSRF:
//                // - POST, PUT, DELETE requests would require CSRF tokens
//                // - Browser automatically includes tokens in forms
//                // - API clients like Postman get 403 Forbidden without tokens
//            }
//        };
//
//        // Apply the CSRF customizer to the HttpSecurity configuration
//        // This tells Spring Security to use our custom CSRF settings
//        http.csrf(CustomizerCSRF);
//
//        // =========================================================================
//        // PART 2: CREATE CUSTOMIZER FOR REQUEST AUTHORIZATION
//        // =========================================================================
//        // We create a Customizer object for authorization configuration
//        // This has a complex generic type because it configures which requests
//        // require authentication and which can be accessed publicly
//        Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>
//                CustomizerHttp = new Customizer<AuthorizeHttpRequestsConfigurer<org.springframework.security.config.annotation.web.builders.HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
//            @Override
//            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizationManagerRequestMatcherRegistry)
//            {
//                // This method is called when Spring Security is configuring request authorization
//                // authorizationManagerRequestMatcherRegistry is the object that controls
//                // which HTTP requests require authentication
//
//                // .anyRequest() means "apply this rule to ALL HTTP requests"
//                // This includes all URLs, all endpoints, all HTTP methods in your application
//                authorizationManagerRequestMatcherRegistry.anyRequest()
//
//                        // .authenticated() means "require authentication for all requests"
//                        // Users must be logged in to access any endpoint
//                        //
//                        // ALTERNATIVES you could use instead:
//                        // .permitAll() = allow all requests without authentication
//                        // .hasRole("ADMIN") = require specific role
//                        // .hasAuthority("READ") = require specific authority
//                        // .denyAll() = block all requests (most restrictive)
//                        .permitAll();
//            }
//        };
//
//        // Apply the authorization customizer to the HttpSecurity configuration
//        // This tells Spring Security to use our custom authorization rules
//        http.authorizeHttpRequests(CustomizerHttp);
//
//        // =========================================================================
//        // PART 3: BUILD AND RETURN THE SECURITY FILTER CHAIN
//        // =========================================================================
//        // .build() method finalizes the security configuration and creates
//        // a SecurityFilterChain bean that Spring will register and use
//        //
//        // What happens during build():
//        // 1. All custom configurations (CSRF, authorization, etc.) are compiled
//        // 2. A chain of security filters is created based on our configuration
//        // 3. This filter chain will intercept all incoming HTTP requests
//        // 4. The bean is registered in Spring application context
//        // 5. Spring Security starts using this configuration for all requests
//        return http.build();
//    }

    // 🔧 BEAN METHOD: authenticationManager()
    // 📝 PURPOSE: Creates and provides the AuthenticationManager bean to Spring container
    // 🎯 WHY: AuthenticationManager is the main interface that handles authentication requests
    // 🔧 HOW: Gets the manager from Spring's configuration
    // 📌 USAGE: Spring uses this bean whenever authentication is needed
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        // 🎯 ACTION: Retrieves the pre-built AuthenticationManager from Spring Security
        // 💡 DETAILS: This manager already knows about your UserDetailsService, PasswordEncoder, etc.
        // ⚡ RESULT: Returns a ready-to-use authentication manager
        return config.getAuthenticationManager();
    }
@Bean
public SecurityFilterChain SecurityFilterChains(HttpSecurity http) throws Exception
{
    // Lambda way to disable ALL security
    // 🛡️ CSRF (Cross-Site Request Forgery) Protection:
    // - CSRF protects against attacks where malicious sites trick users into submitting requests to your app
    // - Disabled because we're building STATELESS API (no sessions)
    // - For JWT/stateless APIs, CSRF can be disabled since we're not using session cookies
    // - ⚠️ WARNING: If you had sessions enabled, disabling CSRF would be DANGEROUS!
    http.csrf(csrf -> csrf.disable());                    // No CSRF protection

    http.authorizeHttpRequests(auth -> auth// Configure access rules
                    // ✅ "/Register" endpoint - PUBLIC ACCESS:
                    // - Anyone can access this endpoint without authentication
                    // - No login required, no JWT token needed
                    // - Typically used for user registration, public APIs
                    .requestMatchers("/Register","/login")
                    .permitAll()

                    // 🔒 ALL OTHER ENDPOINTS - PROTECTED:
                    // - Any request NOT matching "/Register" requires authentication
                    // - User must provide valid credentials (JWT, Basic Auth, etc.)
                    // - Without proper authentication, returns 401 Unauthorized
                    .anyRequest().authenticated()                        // Allow ALL requests without login
            )
            // ⭐ NEW: Enable HTTP Basic Authentication (but it's OPTIONAL due to permitAll())
            // - Provides username/password authentication via Authorization header
            // - Browser shows login popup, API clients send credentials in headers
            // - Since we have permitAll(), Basic Auth is available but NOT REQUIRED
            // - Usage: curl -u username:password http://yourapi.com
            .httpBasic(Customizer.withDefaults())

            // ⭐ NEW: Configure Session Management as STATELESS
            // - STATELESS means: NO HTTP sessions will be created
            // - No JSESSIONID cookies, no server-side session storage
            // - Every request is independent and must include credentials if needed
            // - Perfect for REST APIs, JWT tokens, and mobile app backends
            // - Alternatives: ALWAYS, IF_REQUIRED, NEVER
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 🎯 PURPOSE: Inserts our custom JWT filter into Spring Security's filter chain
// 📍 POSITION: Places JwtFilter BEFORE the default username/password authentication
// 🔄 FLOW: Request → JwtFilter → UsernamePasswordAuthenticationFilter → Your API
// 🎯 WHY BEFORE: So JWT tokens are checked BEFORE Spring tries username/password auth
// 💡 RESULT: If JWT is valid, user is authenticated without checking username/password
            .addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
    // ⭐ @Bean tells Spring: "Create this object and manage it for me"
//    // Spring will automatically call this method and store the result
//    @Bean
//    public UserDetailsService userDetailsService()
//    {
//        // =============================================
//        // WHY User.withDefaultPasswordEncoder()?
//        // =============================================
//        // This is a STATIC FACTORY METHOD that:
//        // 1. Creates a UserBuilder object
//        // 2. Sets up password encoding automatically
//        // 3. Returns the builder so we can configure the user
//
//        // ⭐ CREATE USER 1: "Samir"
//        UserDetails user1 = User.withDefaultPasswordEncoder()  // ← STATIC METHOD - no object needed!
//                .username("Samir")        // Set login username
//                .password("root")         // Set login password
//                .roles("User")            // Set security role/permissions
//                .build();                 // Finalize and create the User object
//
//        // ⭐ CREATE USER 2: "kamel"
//        UserDetails user2 = User.withDefaultPasswordEncoder()  // ← Same static method again
//                .username("kamel")        // Different username
//                .password("root")         // Same password (will be encrypted differently)
//                .roles("ADMIN")           // Different role (administrator)
//                .build();                 // Create second User object
//
//        // =============================================
//        // CREATE IN-MEMORY USER DATABASE
//        // =============================================
//        // InMemoryUserDetailsManager acts like a simple database in RAM
//        // It stores the users we created and allows Spring Security to look them up
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}