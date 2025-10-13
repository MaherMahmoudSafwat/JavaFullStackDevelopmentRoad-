package com.Survey.FullSurveyProject.Surveys;

import com.Survey.FullSurveyProject.Models.Surveys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Surveys,Integer>
{
    @Query
            (
                    value = "SELECT * FROM surveys_data WHERE user_Id=:Id",
                    nativeQuery = true
            )
    public List<Surveys> FindAllSurveysForSpecificUser(@Param(value = "Id")Integer Id);
}
