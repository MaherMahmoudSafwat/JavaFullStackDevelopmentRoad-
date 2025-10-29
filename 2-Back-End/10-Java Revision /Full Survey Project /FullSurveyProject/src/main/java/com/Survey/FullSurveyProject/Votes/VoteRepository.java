package com.Survey.FullSurveyProject.Votes;

import com.Survey.FullSurveyProject.Models.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Votes,Integer>
{
    @Query
            (
                    value = "SELECT * FROM Votes WHERE UserId=:UserId AND SurveyId=:SurveyId",
                    nativeQuery = true
            )
    public Votes GetUserVotes
            (
                    @Param("UserId")Integer UserId,
                    @Param("SurveyId")Integer SurveyId
            );
}
