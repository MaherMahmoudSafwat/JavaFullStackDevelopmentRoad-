package JWT.example.JWT_Tutorials.UserRepository;

import JWT.example.JWT_Tutorials.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer>
{
    public Users findByUsername(String Username);
}
