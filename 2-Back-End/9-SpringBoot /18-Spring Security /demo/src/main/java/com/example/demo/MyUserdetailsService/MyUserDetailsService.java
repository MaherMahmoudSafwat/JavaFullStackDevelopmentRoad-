package com.example.demo.MyUserdetailsService;

import com.example.demo.Models.Users;
import com.example.demo.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service  // ⭐ Spring: "Manage this as a service component"
public class MyUserDetailsService implements UserDetailsService
{
    @Autowired  // ⭐ Spring: "Automatically inject UserRepository"
    private UserRepository repo;

    // ⭐ IMPLEMENT THE CRITICAL METHOD - Spring Security calls this!
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        // ⭐ DATABASE QUERY: Find user by username
        Users user = repo.findByUsername(username);

        // ⭐ USER NOT FOUND: Throw exception (Spring Security handles this)
        if(user==null)
        {
            throw new UsernameNotFoundException("User 404 Error, UserName is not found");
        }

        // ⭐ USER FOUND: Convert to Spring Security UserDetails
        return new UserPrincipal(user);
    }
}