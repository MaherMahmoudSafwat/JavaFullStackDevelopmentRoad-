package com.Survey.FullSurveyProject.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
        (
                name = "Surveys_data",
                indexes =
                        {
                                @Index(name = "SurveyId_Idx",columnList = "Id")
                        }
        )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Surveys
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Survey_Id")
    @SequenceGenerator
            (
                    name = "Survey_Id",
                    sequenceName = "Survey_Id",
                    allocationSize = 50
            )
    @Column(name = "Id")
    private Integer SurveyId;
    @Column(name = "Question")
    private String SurveyQuestionName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
    @OneToMany(mappedBy = "Survey", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Answers> Answer;
}

