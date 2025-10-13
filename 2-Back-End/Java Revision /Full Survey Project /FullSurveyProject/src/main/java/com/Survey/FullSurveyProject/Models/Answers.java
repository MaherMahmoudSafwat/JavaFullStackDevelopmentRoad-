package com.Survey.FullSurveyProject.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer AnswersId;
    @Column(name = "Name")
    private String AnswerName;
    @Column(name = "Percentage")
    private Integer AnswerPercentage;
    @ManyToOne
    @JoinColumn(name = "Survey_Id")
    @JsonIgnore
    private Surveys Survey;
}

