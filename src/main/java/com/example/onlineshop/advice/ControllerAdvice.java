package com.example.onlineshop.advice;//package com.example.login_retgister.advice;


import com.example.onlineshop.dto.response.AppResponseInfo;
import com.example.onlineshop.dto.response.AuthenticationResponse;
import com.example.onlineshop.exceptions.BadRequestException;
import com.example.onlineshop.exceptions.ResourceNotFoundException;
import com.example.onlineshop.security.jwt.JwtAuthenticationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public AuthenticationResponse handleUsernameNotFoundException(UsernameNotFoundException exception) {
        return AuthenticationResponse.builder()
                .message(exception.getMessage())
                .success(false)
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public AuthenticationResponse handleBadRequestException(BadRequestException exception) {
        return AuthenticationResponse.builder()
                .message(exception.getMessage())
                .success(false)
                .build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public AppResponseInfo handleResourceNotFoundException(ResourceNotFoundException exception) {
        return new AppResponseInfo(exception.getMessage(), false);
    }


    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public AppResponseInfo handleJwtAuthenticationExceptionException(JwtAuthenticationException exception) {
        return new AppResponseInfo(exception.getMessage(), false);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public AppResponseInfo handleException(Exception exception) {
        return new AppResponseInfo(exception.getMessage(), false);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public AppResponseInfo handleException(MethodArgumentNotValidException exception) {
        List<ObjectError> allErrors = exception.getAllErrors();
        final String errorMsg = allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return new AppResponseInfo(errorMsg, false);
    }



}
