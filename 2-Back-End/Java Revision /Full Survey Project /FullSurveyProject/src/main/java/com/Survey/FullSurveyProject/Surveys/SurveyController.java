package com.Survey.FullSurveyProject.Surveys;

import com.Survey.FullSurveyProject.Models.Surveys;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersDto;
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
}
