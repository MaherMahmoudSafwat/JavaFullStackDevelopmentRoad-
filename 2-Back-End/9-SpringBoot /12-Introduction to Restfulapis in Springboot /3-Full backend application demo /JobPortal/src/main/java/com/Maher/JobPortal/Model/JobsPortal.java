package com.Maher.JobPortal.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class JobsPortal
{
    private int ID;
    private String JobName;
    private String JobDescription;
    private int RequiredExperience;
    private List<String> TechStacks;
}
