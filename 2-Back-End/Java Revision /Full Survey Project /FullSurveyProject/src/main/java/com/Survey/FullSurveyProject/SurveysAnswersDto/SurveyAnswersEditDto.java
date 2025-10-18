package com.Survey.FullSurveyProject.SurveysAnswersDto;

import com.Survey.FullSurveyProject.Models.Answers;

import java.util.HashMap;
import java.util.List;

public record SurveyAnswersEditDto
        (
                String SurveyQuestion,
                HashMap<String,String> OldNewAnswers
        )
{
    public SurveyAnswersEditDto(String SurveyQuestion,HashMap<String,String>OldNewAnswers)
    {
        this.SurveyQuestion = SurveyQuestion;
        this.OldNewAnswers = OldNewAnswers;
    }
}
