package com.Survey.FullSurveyProject.Answers;

import com.Survey.FullSurveyProject.Models.Answers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<Answers,Integer>
{
    @Modifying
    @Transactional
    @Query
            (
                    value = "UPDATE answers SET name = :NewName WHERE id = :AnswerId",
                    nativeQuery = true
            )
    public void UpdateAnswerNameById
            (
                    @Param("AnswerId")Integer AnswerId,
                    @Param("NewName")String NewName
            );
    
    @Query(value = "SELECT * FROM Answers WHERE Survey_Id = :surveyId", nativeQuery = true)
    List<Answers> findBySurveyId(@Param("surveyId") Integer surveyId);
}
