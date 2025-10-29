package com.Survey.FullSurveyProject.SurveysAnswersDto;

import java.util.List;

public record QuestionDetailsDto(
        String questionName,
        List<AnswerDto> answers
) {
    public QuestionDetailsDto(String questionName, List<AnswerDto> answers) {
        this.questionName = questionName;
        this.answers = answers;
    }
}