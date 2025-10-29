package com.Survey.FullSurveyProject.Answers;

import com.Survey.FullSurveyProject.Models.Answers;
import com.Survey.FullSurveyProject.Models.Surveys;
import com.Survey.FullSurveyProject.SurveysAnswersDto.SurveyAnswersUpdatedDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AnswersService
{
    private final AnswersRepository AnswerRepository;
    private List<Answers>UpdateAnswersPercentage(int TotalVoteCount,List<Answers>AnswerData)
    {
        Double Percentage = 0.0;
        List<Answers>AnswersData = new ArrayList<>();
        for(Answers Answer: AnswerData)
        {
            Integer answerVoteCount = Answer.getAnswerVoteCount();
            if (answerVoteCount == null) {
                answerVoteCount = 0;
            }
            
            if (TotalVoteCount > 0) {
                Percentage = ((double) answerVoteCount / TotalVoteCount) * 100;
                // Round to 1 decimal place
                Percentage = Math.round(Percentage * 10.0) / 10.0;
            } else {
                Percentage = 0.0;
            }
            Answer.setAnswerPercentage(Percentage);
            AnswerRepository.save(Answer);
            AnswersData.add(Answer);
        }
        return AnswersData;
    }
    public AnswersService (AnswersRepository AnswerRepository)
    {
        this.AnswerRepository = AnswerRepository;
    }
    public Answers AddNewAnswer(String Answer, Surveys survey) {
        Answers AnswerData = new Answers();
        AnswerData.setAnswerName(Answer);
        AnswerData.setAnswerPercentage(0.0);
        AnswerData.setAnswerVoteCount(0);
        AnswerData.setSurvey(survey);
        AnswerRepository.save(AnswerData);
        return AnswerData;
    }
    public void EditAndUpdateAnswersName(HashMap<String,String>OldNewAnswers, Integer SurveyId)
    {
        // Get all answers for this survey
        List<Answers> surveyAnswers = AnswerRepository.findBySurveyId(SurveyId);
        
        // For each old->new mapping, find the answer by old name and update it
        OldNewAnswers.forEach((OldAnswerName, NewAnswerName)->
        {
            if(NewAnswerName != null && !NewAnswerName.trim().isEmpty())
            {
                // Find the answer with the old name
                for(Answers answer : surveyAnswers)
                {
                    if(answer.getAnswerName().equals(OldAnswerName))
                    {
                        // Update by ID to ensure we only update this specific answer
                        AnswerRepository.UpdateAnswerNameById(answer.getAnswersId(), NewAnswerName);
                        break; // Only update the first match
                    }
                }
            }
        });
    }
    
    @Transactional
    public List<Answers> UpdateAnswerVoteCountPercentage(SurveyAnswersUpdatedDto SurveyAnswersUpdatedDtos, List<Answers> AnswersData)
    {
        int TotalVoteCount = 0;
        for(Answers AnswerData : AnswersData)
        {
            if(AnswerData.getAnswersId().equals(SurveyAnswersUpdatedDtos.NewAnswer_Id()))
            {
                Integer currentVoteCount = AnswerData.getAnswerVoteCount();
                if (currentVoteCount == null) {
                    currentVoteCount = 0;
                }
                AnswerData.setAnswerVoteCount(currentVoteCount + 1);
                AnswerRepository.save(AnswerData);
            }
            Integer voteCount = AnswerData.getAnswerVoteCount();
            if (voteCount != null) {
                TotalVoteCount += voteCount;
            }
        }
        return UpdateAnswersPercentage(TotalVoteCount,AnswersData);
    }

    @Transactional
    public List<Answers> ChangeUserVote(Integer OldAnswerId,Integer NewAnswerId,List<Answers>AnswersData)
    {
        // Decrement old answer vote count
        Answers OldAnswer = AnswerRepository.findById(OldAnswerId).orElse(null);
        if(OldAnswer != null) {
            Integer oldVoteCount = OldAnswer.getAnswerVoteCount();
            if(oldVoteCount == null) oldVoteCount = 0;
            OldAnswer.setAnswerVoteCount(Math.max(0, oldVoteCount - 1));
            AnswerRepository.save(OldAnswer);
        }
        
        // Increment new answer vote count
        Answers NewAnswer = AnswerRepository.findById(NewAnswerId).orElse(null);
        if(NewAnswer != null) {
            Integer newVoteCount = NewAnswer.getAnswerVoteCount();
            if(newVoteCount == null) newVoteCount = 0;
            NewAnswer.setAnswerVoteCount(newVoteCount + 1);
            AnswerRepository.save(NewAnswer);
        }
        
        // Update the list with modified answers
        List<Answers>AnswerData = new ArrayList<>();
        for(Answers Answer : AnswersData)
        {
            if(Answer.getAnswersId().equals(OldAnswerId))
            {
                AnswerData.add(OldAnswer);
            }
            else if(Answer.getAnswersId().equals(NewAnswerId))
            {
                AnswerData.add(NewAnswer);
            }
            else
            {
                AnswerData.add(Answer);
            }
        }
        return AnswerData;
    }
    
    // Public method to update percentages only (no vote count changes)
    public List<Answers> UpdateAnswersPercentageOnly(int TotalVoteCount, List<Answers> AnswerData)
    {
        return UpdateAnswersPercentage(TotalVoteCount, AnswerData);
    }
}