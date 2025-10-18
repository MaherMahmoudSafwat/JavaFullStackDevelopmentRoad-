package com.Survey.FullSurveyProject.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answers
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Answer_Id")
    @SequenceGenerator
            (
                    name = "Answer_Id",
                    sequenceName = "Answer_Id",
                    allocationSize = 50
            )
    @Column(name = "Id")
    private Integer answersId;
    @Column(name = "Name")
    private String answerName;
    @Column(name = "Percentage")
    private Double answerPercentage;
    @Column(name = "VoteCount")
    private Integer AnswerVoteCount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Survey_Id")
    @JsonBackReference
    private Surveys survey;
}
