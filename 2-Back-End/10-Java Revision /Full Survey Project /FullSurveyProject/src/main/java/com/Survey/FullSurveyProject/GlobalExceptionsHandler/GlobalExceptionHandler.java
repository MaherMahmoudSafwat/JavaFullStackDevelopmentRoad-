package com.Survey.FullSurveyProject.GlobalExceptionsHandler;

import com.Survey.FullSurveyProject.Exceptions.EmailIsAlreadyFoundException;
import com.Survey.FullSurveyProject.Exceptions.EmailIsInvalidException;
import com.Survey.FullSurveyProject.Exceptions.PasswordIsNotValidException;
import jakarta.el.MethodInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<String>> handleExceptionErrors(Exception exp) {
        List<String> errors = new ArrayList<>();
        
        // Log the full exception for debugging
        System.err.println("=== EXCEPTION CAUGHT ===");
        System.err.println("Exception Type: " + exp.getClass().getName());
        System.err.println("Exception Message: " + exp.getMessage());
        exp.printStackTrace();
        System.err.println("========================");

        // Special handling for validation errors
        if (exp instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException validationExp = (MethodArgumentNotValidException) exp;

            validationExp.getBindingResult().getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String message = error.getDefaultMessage();
                errors.add(fieldName + ": " + message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        if (exp instanceof EmailIsAlreadyFoundException) {
            errors.add(exp.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
        }

        if (exp instanceof IOException) {
            errors.add(exp.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
        }

        if (exp instanceof EmailIsInvalidException) {
            errors.add(exp.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        if (exp instanceof PasswordIsNotValidException) {
            errors.add(exp.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        errors.add(exp.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}

