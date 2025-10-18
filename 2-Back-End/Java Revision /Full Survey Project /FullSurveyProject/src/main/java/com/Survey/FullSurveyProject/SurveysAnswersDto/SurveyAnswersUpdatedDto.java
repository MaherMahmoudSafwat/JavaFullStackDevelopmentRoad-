package com.Survey.FullSurveyProject.SurveysAnswersDto;

public record SurveyAnswersUpdatedDto
        (
                Integer Question_Id,
                Integer OldAnswer_Id,
                Integer NewAnswer_Id
        )
{
    public SurveyAnswersUpdatedDto
            (
                    Integer Question_Id,
                    Integer OldAnswer_Id,
                    Integer NewAnswer_Id
            )
    {
        this.Question_Id = Question_Id;
        this.OldAnswer_Id = OldAnswer_Id;
        this.NewAnswer_Id = NewAnswer_Id;
    }
}
