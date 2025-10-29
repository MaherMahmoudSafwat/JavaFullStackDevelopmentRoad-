package com.Survey.FullSurveyProject.Surveys;

import com.Survey.FullSurveyProject.Models.Answers;
import com.Survey.FullSurveyProject.Models.Surveys;
import jakarta.transaction.Transactional;
import org.hibernate.boot.beanvalidation.IntegrationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Surveys,Integer>
{
    @Query("SELECT s FROM Surveys s WHERE s.user.userId = :Id")
    public List<Surveys> FindAllSurveysForSpecificUser(@Param(value = "Id")Integer Id);
    @Query
            (
                    value = "SELECT Question FROM Surveys_data WHERE user_id=:User_Id",
                    nativeQuery = true
            )
    public List<String>GetAllSurveyQuestions(@Param("User_Id")Integer User_Id);
    public Surveys findBySurveyQuestionName(String QuestionName);
}
