package com.Survey.FullSurveyProject.SurveysAnswersDto;

import com.Survey.FullSurveyProject.Models.Answers;

import java.util.ArrayList;
import java.util.List;

public record SurveyAnswersDto
        (
                String Question,
                List<String>Answer
        )
{
    public SurveyAnswersDto(String Question,List<String> Answer)
    {
        this.Question = Question;
        this.Answer = Answer;
    }
}
