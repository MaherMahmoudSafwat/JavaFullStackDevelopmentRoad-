package com.Survey.FullSurveyProject.Users;

import com.Survey.FullSurveyProject.Exceptions.EmailIsInvalidException;
import com.Survey.FullSurveyProject.Exceptions.PasswordIsNotValidException;
import com.Survey.FullSurveyProject.Models.Users;
import com.Survey.FullSurveyProject.UsersDto.UserRequestDto;
import com.Survey.FullSurveyProject.UsersDto.UserResponseWithoutPasswordDto;
import com.Survey.FullSurveyProject.UsersDto.UserUpdateRequestDto;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/Users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping("/AddNewUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddNewUser(
            @RequestPart("user") @Valid Users user,
            @RequestPart("image") MultipartFile userImage) throws Exception {
        userService.AddNewUser(user, userImage);
        return "User has been added successfully";
    }

    @PostMapping("/SignInToMyAccount")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponseWithoutPasswordDto GetUserAccount(@RequestBody UserRequestDto UserRequestDto) throws EmailIsInvalidException, PasswordIsNotValidException {
        return userService.GetUserAccount(UserRequestDto);
    }

    @GetMapping("/ViewAllUserAccountProfile/{Email}")
    public Users ShowUserAccountProfile(@PathVariable(name = "Email") String Email)
    {
        return userService.ViewUserAccountProfile(Email);
    }
    @PutMapping("/EditAndUpdateUserAccount/{Email}")
    public String UpdateUserAccount(
            @PathVariable String Email,
            @RequestPart("user") @Valid UserUpdateRequestDto UserUpdateDataRequestDto,
            @RequestPart(value = "image", required = false) MultipartFile userImage) throws Exception
    {
        if(UserUpdateDataRequestDto == null)
        {
            return "Invalid update data provided";
        }
        userService.UpdateAndEditUserData(Email, UserUpdateDataRequestDto, userImage);
        return "The Account Has Been Updated Successfully";
    }
}