package JWT.example.JWT_Tutorials.JwtFilter;

import JWT.example.JWT_Tutorials.JWTTokens.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext ApplicationContext;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
// 🎯 PURPOSE: The core method that processes EVERY incoming HTTP request
// 📍 WHEN RUNS: Called for EVERY API request to your application
// 🔧 PARAMETERS:
//    - HttpServletRequest request: The incoming HTTP request (headers, body, etc.)
//    - HttpServletResponse response: The HTTP response we can modify
//    - FilterChain filterChain: The next filters in the security chain
// 🎯 TYPICAL WORKFLOW INSIDE THIS METHOD:
//    1. Extract JWT token from Authorization header
//    2. Validate JWT token (signature, expiration)
//    3. If valid, set user authentication in SecurityContext
//    4. Call filterChain.doFilter() to continue to next filter/controller
//    5. If invalid, return 401 Unauthorized error
// 💡 IMPORTANCE: This is where JWT authentication actually happens for each request
// 🎯 STEP 1: Extract Authorization header from the HTTP request
// This header should contain the JWT token in format: "Bearer <actual-token>"
        String AuthHeader = request.getHeader("Authorization");

// 🎯 STEP 2: Initialize variables to store token and username
        String Token = null;
        String UserName = null;

// 🎯 STEP 3: Check if Authorization header exists and follows Bearer token format
        if (AuthHeader != null && AuthHeader.startsWith("Bearer ")) {
            // 🎯 Extract the actual JWT token by removing "Bearer " prefix (7 characters)
            Token = AuthHeader.substring(7);

            // 🎯 Extract username from the JWT token payload
            UserName = JwtService.extractUserName(Token);
        }

// 🎯 STEP 4: Initialize variable to store user details
        UserDetails userDetails = null;

// 🎯 STEP 5: Check if we have a username AND user is not already authenticated
        if (UserName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 🎯 Load user details from database using the username from JWT token
            userDetails = ApplicationContext.getBean(UserDetailsService.class)
                    .loadUserByUsername(UserName); // 🛠️ FIXED: Removed extra parentheses
        }

// 🎯 STEP 6: Validate the JWT token against the user details
        if (Token != null && userDetails != null && JwtService.validateToken(Token, userDetails)) {
            // 🎯 STEP 7: Create authentication token for Spring Security
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,           // 🎯 The authenticated user principal
                            null,                  // 🎯 Credentials (null because JWT doesn't need password)
                            userDetails.getAuthorities() // 🎯 User roles/permissions 🛠️ FIXED: Correct method call
                    );

            // 🎯 STEP 8: Add request details to the authentication object
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 🎯 STEP 9: Set the authentication in Spring Security context
            // This tells Spring Security that this user is now authenticated
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

// 🎯 STEP 10: Continue with the next filters in the chain
// This allows the request to proceed to the actual API endpoint
        filterChain.doFilter(request, response);
    }
}
