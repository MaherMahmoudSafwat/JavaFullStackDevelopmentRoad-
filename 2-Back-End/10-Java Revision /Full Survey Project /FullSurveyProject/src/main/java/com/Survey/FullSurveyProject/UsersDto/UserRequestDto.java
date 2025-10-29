package com.Survey.FullSurveyProject.UsersDto;

public record UserRequestDto
        (
                String userEmail,
                String userPassword
        )
{
    public UserRequestDto(String userEmail, String userPassword)
    {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}

