package com.Survey.FullSurveyProject.Users;

import com.Survey.FullSurveyProject.Exceptions.EmailIsAlreadyFoundException;
import com.Survey.FullSurveyProject.Exceptions.EmailIsInvalidException;
import com.Survey.FullSurveyProject.Exceptions.PasswordIsNotValidException;
import com.Survey.FullSurveyProject.Models.Users;
import com.Survey.FullSurveyProject.Models.UsersImages;
import com.Survey.FullSurveyProject.UsersDto.UserRequestDto;
import com.Survey.FullSurveyProject.UsersDto.UserResponseWithoutPasswordDto;
import com.Survey.FullSurveyProject.UsersDto.UserUpdateRequestDto;
import com.Survey.FullSurveyProject.UsersMapper.UserWithoutPasswordMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsersService
{
    private UsersRepository UserRepository;
    private UserWithoutPasswordMapper UserAccountResponseWithoutPasswordMapper;
    public UsersService(UsersRepository UserRepository,UserWithoutPasswordMapper
            UserAccountResponseWithoutPasswordMapper)
    {
        this.UserRepository = UserRepository;
        this.UserAccountResponseWithoutPasswordMapper = UserAccountResponseWithoutPasswordMapper;
    }
    @Transactional
    public void AddNewUser(Users UserData, MultipartFile UsersImage) throws IOException, EmailIsAlreadyFoundException
    {
        Optional<Users> existingUser = UserRepository.findByUserEmail(UserData.getUserEmail());

        if(existingUser.isPresent())
        {
            throw new EmailIsAlreadyFoundException("This Email is Already in use");
        }
        else
        {
            UsersImages UserImage = new UsersImages();

            if(UsersImage != null)
            {
                UserImage.setImageFile(UsersImage.getBytes());
                UserImage.setImageName(UsersImage.getOriginalFilename());
                UserImage.setImageType(UsersImage.getContentType());
                UserImage.setImageSize(UsersImage.getSize());

            }

            UserData.setUserImage(UserImage);
            UserRepository.save(UserData);

        }
    }

    @Transactional
    public UserResponseWithoutPasswordDto GetUserAccount(UserRequestDto UserRequestDto) throws EmailIsInvalidException, PasswordIsNotValidException
    {
        Users UserData = UserRepository.findByUserEmail(UserRequestDto.userEmail()).orElse(null);

        if(UserData == null)
        {
            throw new EmailIsInvalidException("Email is invalid please try again.");
        }

        if(!UserData.getUserPassword().equals(UserRequestDto.userPassword()))
        {
            throw new PasswordIsNotValidException("Invalid Password please try again.");
        }
        return UserAccountResponseWithoutPasswordMapper.UserMappingWithoutPasswords(UserData);
    }

    @Transactional
    public Users ViewUserAccountProfile(String Email)
    {
        return UserRepository.findByUserEmail(Email).orElse(null);
    }
    @Transactional
    public Users UpdateAndEditUserData(String Email, UserUpdateRequestDto UserUpdateRequestDataDto, MultipartFile userImage) throws IOException
    {
        Users UserUpdateData = UserRepository.findByUserEmail(Email).orElse(null);

        if(UserUpdateData == null)
        {
            return null;
        }

        if(UserUpdateRequestDataDto.UserName() != null && !UserUpdateRequestDataDto.UserName().trim().isEmpty())
        {
            UserUpdateData.setUserName(UserUpdateRequestDataDto.UserName().trim());
        }

        if(UserUpdateRequestDataDto.UserPassword() != null && !UserUpdateRequestDataDto.UserPassword().trim().isEmpty())
        {
            UserUpdateData.setUserPassword(UserUpdateRequestDataDto.UserPassword().trim());
        }

        // Handle image update
        if(userImage != null && !userImage.isEmpty())
        {
            UsersImages UserImage = new UsersImages();
            UserImage.setImageFile(userImage.getBytes());
            UserImage.setImageName(userImage.getOriginalFilename());
            UserImage.setImageType(userImage.getContentType());
            UserImage.setImageSize(userImage.getSize());
            UserUpdateData.setUserImage(UserImage);
        }

        return UserRepository.save(UserUpdateData);
    }
}