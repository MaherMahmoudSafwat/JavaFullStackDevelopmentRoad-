package com.Survey.FullSurveyProject.Users;

import com.Survey.FullSurveyProject.Exceptions.EmailIsAlreadyFoundException;
import com.Survey.FullSurveyProject.Models.Users;
import com.Survey.FullSurveyProject.Models.UsersImages;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UsersService
{
    private UsersRepository UserRepository;
    private UsersImages UserImage;
    public UsersService(UsersRepository UserRepository, UsersImages UserImage)
    {
        this.UserRepository = UserRepository;
        this.UserImage = UserImage;
    }
    public void AddNewUser(Users UserData, MultipartFile UsersImage) throws IOException, EmailIsAlreadyFoundException {
        if(UserRepository.findByEmail(UserData.getUserEmail())!=null)
        {
            throw new EmailIsAlreadyFoundException("This Email is Already in use");
        }
        else
        {
            UserImage.setImageFile(UsersImage.getBytes());
            UserImage.setImageName(UserImage.getImageName());
            UserImage.setImageType(UsersImage.getContentType());
            UserImage.setImageSize(UsersImage.getSize());
            UserData.setUserImage(UserImage);
            UserRepository.save(UserData);
        }
    }
}
