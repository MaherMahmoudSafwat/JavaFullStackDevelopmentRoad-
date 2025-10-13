package com.Survey.FullSurveyProject.Answers;

import com.Survey.FullSurveyProject.Models.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<Answers,Integer>
{
}
