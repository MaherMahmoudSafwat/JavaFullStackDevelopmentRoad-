package com.Survey.FullSurveyProject.SurveysAnswersDto;

public record AnswerDto(
        Integer answersId,
        String answerName,
        Double answerPercentage
) {}