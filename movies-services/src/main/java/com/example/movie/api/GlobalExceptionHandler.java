package com.example.movie.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.movie.exception.*;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice 
@Slf4j
public class GlobalExceptionHandler 
{
    
    @Getter
    static class Error
    {
        private final String reason;
        private final String message;


        Error(String reason , String message) 
        {
           this.reason = reason;
           this.message = message;
        }    
    }

    @ExceptionHandler({InvalidDataException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleInvalidDataException(Throwable throwable)
    {
         log.warn(throwable.getMessage());
         return new Error(HttpStatus.BAD_REQUEST.getReasonPhrase(), throwable.getMessage()); 
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleNotFoundException(NotFoundException e)
    {
         log.warn(e.getMessage());
         return new Error(HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage()); 
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleUnknownException(Exception e)
    {
         log.warn(e.getMessage());
         return new Error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage()); 
    }

}    
