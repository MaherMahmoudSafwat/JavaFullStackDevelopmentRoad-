package com.Survey.FullSurveyProject.Surveys;

import com.Survey.FullSurveyProject.Models.Answers;
import com.Survey.FullSurveyProject.Models.Surveys;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersDto;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersEditDto;
import com.Survey.FullSurveyProject.SurveysAnswersDto.QuestionDetailsDto;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersUpdatedDto;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersUpdatedDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Surveys")
@CrossOrigin(origins = "http://localhost:4200")
public class SurveyController
{
    private  final SurveyService SurveysService;
    public SurveyController (SurveyService SurveysService)
    {
        this.SurveysService = SurveysService;
    }
    @PostMapping("/AddSurvey/{UserId}")
    public String AddSurveys
            (
                    @PathVariable(name = "UserId") Integer UserId,
                    @RequestBody SurveyAnswersDto SurveyAnswers
            )
    {
        SurveysService.AddNewSurvey(UserId,SurveyAnswers);
        return "The Survey has been added Successfully";
    }
    @GetMapping("/{Id}")
    public List<Surveys> GetAllSurveysForA_SpecificUser(@PathVariable(name = "Id") Integer Id)
    {
        return SurveysService.GetAllSurveysForUser(Id);
    }
    @GetMapping("/ShowAllQuestions/{Id}")
    public List<String> GetAllUsersQuestions(@PathVariable(name = "Id")Integer Id)
    {
        return SurveysService.GetAllUserSurveyQuestions(Id);
    }
    
    @GetMapping("/QuestionDetails/{userId}/{questionName}")
    public QuestionDetailsDto GetQuestionDetails(
            @PathVariable(name = "userId") Integer userId,
            @PathVariable(name = "questionName") String questionName
    )
    {
        return SurveysService.GetQuestionDetails(userId, questionName);
    }
    
    @PutMapping("/EditSurveysQuestionAndAnswers/{Id}/{QuestionName}")
    public String EditSurveyOfUser
            (
                    @PathVariable(name = "QuestionName")String QuestionName,
                    @RequestBody SurveyAnswersEditDto SurveyAnswersEditDtos
            )
    {
        SurveysService.EditUserSurvey(QuestionName,SurveyAnswersEditDtos);
        return "The Surveys data has been edited and updated successfully";
    }
    @DeleteMapping("/DeleteSurvey/{userId}/{questionName}")
    public String DeleteQuestionSurvey
            (
                    @PathVariable(name = "userId") Integer userId,
                    @PathVariable(name = "questionName") String questionName
            )
    {
        SurveysService.DeleteSurveyFromTheDashBoardUser(questionName);
        return "Survey deleted successfully";
    }
    @GetMapping("/ShowAllSurveys")
    public List<Surveys> ShowAllSurveys()
    {
        return SurveysService.GetAllSurveys();
    }
    @PutMapping("/ShowAllSurveys/{UserId}")
    public List<Answers> UpdateAnswersVoteCountPercentage
            (
                    @PathVariable(name = "UserId") Integer UserId,
                    @RequestBody SurveyAnswersUpdatedDto SurveyAnswersUpdatedDtos
            )
    {
        return SurveysService.GetUserAnswerVoteCountPercentage(UserId,SurveyAnswersUpdatedDtos);
    }
}
