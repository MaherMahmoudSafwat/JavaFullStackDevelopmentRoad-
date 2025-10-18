package com.Survey.FullSurveyProject.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
        (
                name = "Votes",
                indexes =
                        {
                                @Index(name = "VoteId_Index",columnList = "VoteId"),
                                @Index(name = "UserId_Index",columnList = "UserId"),
                                @Index(name = "SurveyId_Index",columnList = "SurveyId"),
                                @Index(name = "AnswerId_Index",columnList = "AnswerId")
                        }
        )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Votes
{
    @Id
    @GeneratedValue
            (
                    strategy = GenerationType.SEQUENCE,
                    generator = "Vote_Id"
            )
    @SequenceGenerator
            (
                    name = "Vote_Id",
                    sequenceName = "Vote_Id",
                    allocationSize = 100
            )
    private Integer VoteId;
    private Integer UserId;
    private Integer SurveyId;
    private Integer AnswerId;
}
