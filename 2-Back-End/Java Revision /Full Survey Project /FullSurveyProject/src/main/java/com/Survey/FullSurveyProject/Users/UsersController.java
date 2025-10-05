package com.Survey.FullSurveyProject.Users;

import com.Survey.FullSurveyProject.Exceptions.EmailIsAlreadyFoundException;
import com.Survey.FullSurveyProject.Models.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(name = "/Users")
public class UsersController {
    private UsersService UserService;

    public UsersController(UsersService UserService) {
        this.UserService = UserService;
    }

    @PostMapping("/Users/AddNewUser")
    @ResponseStatus(HttpStatus.CREATED)
    public String AddNewUser(@RequestBody Users User, @RequestBody MultipartFile UserImages) {
        UserService.AddNewUser(User, UserImages);
        return "User has been added Successfully";
    }

    @ExceptionHandler(EmailIsAlreadyFoundException.class)
    public ResponseEntity<ErrorResponse>
}
