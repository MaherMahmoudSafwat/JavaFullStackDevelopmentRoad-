package com.Survey.FullSurveyProject.UsersMapper;

import com.Survey.FullSurveyProject.Models.Users;
import com.Survey.FullSurveyProject.UsersDto.UserResponseWithoutPasswordDto;
import org.springframework.stereotype.Service;

@Service
public class UserWithoutPasswordMapper
{
    public UserResponseWithoutPasswordDto UserMappingWithoutPasswords(Users UserAccount)
    {
    return new UserResponseWithoutPasswordDto
        (
            UserAccount.getUserId(),
            UserAccount.getUserName(),
            UserAccount.getUserEmail(),
            UserAccount.getUserImage()
        );
    }
}
