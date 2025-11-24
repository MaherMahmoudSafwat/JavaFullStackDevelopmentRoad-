package QuizServices.example.QuizServices.Repositories;

import QuizServices.example.QuizServices.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>
{

}
