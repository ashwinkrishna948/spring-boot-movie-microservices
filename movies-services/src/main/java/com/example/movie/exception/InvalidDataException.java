package com.example.movie.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message) 
    {
       super(message);
    }
    
}
