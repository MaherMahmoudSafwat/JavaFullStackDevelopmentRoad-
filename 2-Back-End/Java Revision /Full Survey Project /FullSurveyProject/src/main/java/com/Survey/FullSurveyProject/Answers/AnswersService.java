package com.Survey.FullSurveyProject.Answers;

import com.Survey.FullSurveyProject.Models.Answers;
import com.Survey.FullSurveyProject.Models.Surveys;
import org.springframework.stereotype.Service;

@Service
public class AnswersService
{
    private final AnswersRepository AnswerRepository;
    public AnswersService (AnswersRepository AnswerRepository)
    {
        this.AnswerRepository = AnswerRepository;
    }
    public Answers AddNewAnswer(String Answer, Surveys survey) {
        Answers AnswerData = new Answers();
        AnswerData.setAnswerName(Answer);
        AnswerData.setAnswerPercentage(0);
        AnswerData.setSurvey(survey);
        AnswerRepository.save(AnswerData);
        return AnswerData;
    }
}
