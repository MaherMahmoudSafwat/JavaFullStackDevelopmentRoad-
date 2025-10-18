package com.Survey.FullSurveyProject.Votes;

import com.Survey.FullSurveyProject.Answers.AnswersService;
import com.Survey.FullSurveyProject.Models.Votes;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersUpdatedDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteService
{
    private final VoteRepository VoteRepository;
    public VoteService(VoteRepository VoteRepository)
    {
        this.VoteRepository = VoteRepository;
    }
    
    @Transactional
    public Integer CheckUserVotes(Integer UserId, SurveyAnswersUpdatedDto SurveyAnswerUpdatedDtos)
    {
        Votes Vote = VoteRepository.GetUserVotes(UserId,SurveyAnswerUpdatedDtos.Question_Id());
        
        if(Vote == null)
        {
            Vote = new Votes();
            Vote.setUserId(UserId);
            Vote.setSurveyId(SurveyAnswerUpdatedDtos.Question_Id());
            Vote.setAnswerId(SurveyAnswerUpdatedDtos.NewAnswer_Id());
            VoteRepository.save(Vote);
            return 1; // First time voting
        }
        
        if(Vote.getAnswerId().equals(SurveyAnswerUpdatedDtos.NewAnswer_Id()))
        {
            return -1; // Same answer, no change
        }
        
        // Case 3: User is changing their vote to a different answer
        Vote.setAnswerId(SurveyAnswerUpdatedDtos.NewAnswer_Id());
        VoteRepository.save(Vote);
        return 3; // Changing vote
    }
    
    public Integer GetUserPreviousVote(Integer UserId, Integer SurveyId)
    {
        Votes Vote = VoteRepository.GetUserVotes(UserId, SurveyId);
        if(Vote != null) {
            return Vote.getAnswerId();
        }
        return null;
    }
}