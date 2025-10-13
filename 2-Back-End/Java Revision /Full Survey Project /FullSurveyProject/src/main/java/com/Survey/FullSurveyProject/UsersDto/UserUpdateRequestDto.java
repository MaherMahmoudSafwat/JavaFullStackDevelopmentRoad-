package com.Survey.FullSurveyProject.UsersDto;

import com.Survey.FullSurveyProject.Models.UsersImages;

public record UserUpdateRequestDto
        (
                String UserName,
                String UserPassword,
                UsersImages UserImages
        )
{
    public UserUpdateRequestDto(String UserName,String UserPassword,UsersImages UserImages)
    {
        this.UserName = UserName;
        this.UserPassword = UserPassword;
        this.UserImages = UserImages;
    }
}
