package com.Survey.FullSurveyProject.Surveys;

import com.Survey.FullSurveyProject.Answers.AnswersRepository;
import com.Survey.FullSurveyProject.Answers.AnswersService;
import com.Survey.FullSurveyProject.Models.Answers;
import com.Survey.FullSurveyProject.Models.Surveys;
import com.Survey.FullSurveyProject.SurveysAnswersDto.*;
//import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyEditRequestDto;
import com.Survey.FullSurveyProject.Users.UsersRepository;
import com.Survey.FullSurveyProject.Models.Users;
import com.Survey.FullSurveyProject.Votes.VoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyService
{
    private final SurveyRepository SurveyRepository;
    private final UsersRepository UserRepository;
    private final AnswersService AnswerService;
    private final AnswersRepository AnswersRepository;
    private final VoteService VoteService;
    public SurveyService
            (
                    SurveyRepository SurveyRepository,
                    UsersRepository UserRepository,
                    AnswersService AnswerService,
                    AnswersRepository AnswersRepository,
                    VoteService VoteService)
    {
        this.SurveyRepository = SurveyRepository;
        this.UserRepository = UserRepository;
        this.AnswerService = AnswerService;
        this.AnswersRepository = AnswersRepository;
        this.VoteService = VoteService;
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
    @Transactional
    public List<Surveys>GetAllSurveysForUser(Integer Id)
    {
        return SurveyRepository.FindAllSurveysForSpecificUser(Id);
    }
    
    @Transactional
    public List<String> GetAllUserSurveyQuestions(Integer User_Id)
    {
        return SurveyRepository.GetAllSurveyQuestions(User_Id);
    }
    
    @Transactional
    public QuestionDetailsDto GetQuestionDetails(Integer userId, String questionName)
    {
        Surveys survey = SurveyRepository.findBySurveyQuestionName(questionName);
        
        if (survey == null || !survey.getUser().getUserId().equals(userId)) {
            throw new RuntimeException("Survey not found or unauthorized access");
        }
        
        List<AnswerDto> answerDtos = survey.getAnswer().stream()
                .map(answer -> new AnswerDto(
                        answer.getAnswersId(),
                        answer.getAnswerName(),
                        answer.getAnswerPercentage()
                ))
                .collect(Collectors.toList());
        
        return new QuestionDetailsDto(survey.getSurveyQuestionName(), answerDtos);
    }
    
    @Transactional
    public void EditUserSurvey
                (
                        String QuestionName,
                        SurveyAnswersEditDto SurveyAnswersEditDtos
                ) {

        Surveys Survey = SurveyRepository.findBySurveyQuestionName(QuestionName);
        if(SurveyAnswersEditDtos.SurveyQuestion() != null && !SurveyAnswersEditDtos.SurveyQuestion().trim().isEmpty())
        {
            Survey.setSurveyQuestionName(SurveyAnswersEditDtos.SurveyQuestion());
        }
        SurveyRepository.save(Survey);
        
        if(SurveyAnswersEditDtos.OldNewAnswers() != null && !SurveyAnswersEditDtos.OldNewAnswers().isEmpty())
        {
            AnswerService.EditAndUpdateAnswersName(SurveyAnswersEditDtos.OldNewAnswers(), Survey.getSurveyId());
        }
    }

    @Transactional
    public void DeleteSurveyFromTheDashBoardUser(String Question)
    {
        Surveys survey = SurveyRepository.findBySurveyQuestionName(Question);
        if (survey == null) {
            throw new RuntimeException("Survey not found: " + Question);
        }
        SurveyRepository.delete(survey);
    }

    @Transactional
    public List<Surveys> GetAllSurveys()
    {
        return SurveyRepository.findAll();
    }

    @Transactional
    public List<Answers> GetUserAnswerVoteCountPercentage(Integer UserId,SurveyAnswersUpdatedDto SurveyAnswersUpdatedDtos)
    {
        List<Answers>TheQuestionAnswers = AnswersRepository.findBySurveyId(SurveyAnswersUpdatedDtos.Question_Id());
        if (TheQuestionAnswers == null || TheQuestionAnswers.isEmpty()) {
            throw new RuntimeException("No answers found for survey ID: " + SurveyAnswersUpdatedDtos.Question_Id());
        }
        
        // Get user's previous vote before checking/updating
        Integer OldAnswerId = VoteService.GetUserPreviousVote(UserId, SurveyAnswersUpdatedDtos.Question_Id());
        
        Integer VoteCases = VoteService.CheckUserVotes(UserId,SurveyAnswersUpdatedDtos);
        
        // Case -1: User clicked same answer - no change
        if(VoteCases == -1)
        {
            return TheQuestionAnswers;
        }
        // Case 1: First time voting
        else if(VoteCases == 1)
        {
            return AnswerService.UpdateAnswerVoteCountPercentage(SurveyAnswersUpdatedDtos,TheQuestionAnswers);
        }
        // Case 3: Changing vote
        TheQuestionAnswers = AnswerService.ChangeUserVote
                (
                        OldAnswerId,
                        SurveyAnswersUpdatedDtos.NewAnswer_Id(),
                        TheQuestionAnswers
                );
        
        // Recalculate percentages only (don't increment again!)
        int TotalVoteCount = 0;
        for(Answers answer : TheQuestionAnswers) {
            Integer voteCount = answer.getAnswerVoteCount();
            if (voteCount != null) {
                TotalVoteCount += voteCount;
            }
        }
        return AnswerService.UpdateAnswersPercentageOnly(TotalVoteCount, TheQuestionAnswers);
    }

}