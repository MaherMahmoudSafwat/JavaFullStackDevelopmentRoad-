package com.Survey.FullSurveyProject.UsersDto;

import com.Survey.FullSurveyProject.Models.UsersImages;

public record UserResponseWithoutPasswordDto
        (
                Integer UserId,
                String UserName,
                String Email,
                UsersImages UserImage
        )
{
    public UserResponseWithoutPasswordDto(Integer UserId, String UserName, String Email, UsersImages UserImage)
    {
        this.UserId = UserId;
        this.UserName = UserName;
        this.Email = Email;
        this.UserImage = UserImage;
    }
}
