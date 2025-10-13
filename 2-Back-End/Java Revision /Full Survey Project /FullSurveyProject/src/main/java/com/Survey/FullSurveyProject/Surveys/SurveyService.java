package com.Survey.FullSurveyProject.Surveys;

import com.Survey.FullSurveyProject.Answers.AnswersService;
import com.Survey.FullSurveyProject.Models.Answers;
import com.Survey.FullSurveyProject.Models.Surveys;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersDto;
import com.Survey.FullSurveyProject.Users.UsersRepository;
import com.Survey.FullSurveyProject.Models.Users;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyService
{
    private final SurveyRepository SurveyRepository;
    private final UsersRepository UserRepository;
    private final AnswersService AnswerService;
    public SurveyService(SurveyRepository SurveyRepository,UsersRepository UserRepository,AnswersService AnswerService)
    {
        this.SurveyRepository = SurveyRepository;
        this.UserRepository = UserRepository;
        this.AnswerService = AnswerService;
    }
    public void AddNewSurvey(Integer UserId, SurveyAnswersDto SurveysAnswersDtos) {
        Users User = UserRepository.findById(UserId).orElse(null);

        Surveys Survey = new Surveys();
        Survey.setUser(User);
        Survey.setSurveyQuestionName(SurveysAnswersDtos.Question());
        SurveyRepository.save(Survey);

        List<Answers> NewAnswer = new ArrayList<>();
        for(String Answer : SurveysAnswersDtos.Answer()) {
            Answers AnswerData = AnswerService.AddNewAnswer(Answer, Survey);
            NewAnswer.add(AnswerData);
        }

        Survey.setAnswer(NewAnswer);
        SurveyRepository.save(Survey);
    }
    public List<Surveys>GetAllSurveysForUser(Integer Id)
    {
        return SurveyRepository.FindAllSurveysForSpecificUser(Id);
    }
}

