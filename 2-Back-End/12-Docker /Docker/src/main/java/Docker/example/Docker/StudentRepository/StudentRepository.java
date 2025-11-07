package Docker.example.Docker.StudentRepository;

import Docker.example.Docker.Models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Students,Integer>
{
}