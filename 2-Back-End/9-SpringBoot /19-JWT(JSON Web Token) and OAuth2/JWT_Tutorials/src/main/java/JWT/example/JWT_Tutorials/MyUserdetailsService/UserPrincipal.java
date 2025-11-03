package JWT.example.JWT_Tutorials.MyUserdetailsService;

import JWT.example.JWT_Tutorials.Models.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails
{
    private Users user;  // Your database User entity

    // ⭐ CONSTRUCTOR: Wrap your database user
    public UserPrincipal(Users user)
    {
        this.user = user;
    }

    // ⭐ ROLES/PERMISSIONS: Convert your roles to Spring Security format
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        // ⭐ HARDCODED ROLE - This should come from your database!
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    // ⭐ PASSWORD: Get from your database user
    @Override
    public String getPassword() {
        return user.getPassword();  // ⭐ Should return actual password from database!
    }

    // ⭐ USERNAME: Get from your database user
    @Override
    public String getUsername() {
        return user.getUsername();  // ⭐ Should return actual username from database!
    }

    // ⭐ OTHER SECURITY METHODS (usually return true for active users)
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
