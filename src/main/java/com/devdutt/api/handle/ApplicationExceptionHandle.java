package com.devdutt.api.handle;

import com.devdutt.api.dto.ErrorResponseType;
import com.devdutt.api.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandle {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException mae) {
        Map<String, String> errorMap = new HashMap<>();
        mae.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    /* @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     @ExceptionHandler(UserNotFoundException.class)
     public Map<String, String> handleBusinessException(UserNotFoundException ufe) {
         Map<String, String> errorMap = new HashMap<>();
         errorMap.put("errorMessage", ufe.getMessage());
         return errorMap;
     }*/
                         //OR
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseType> handleNotFoundException(UserNotFoundException nfe) {
        return new ResponseEntity<ErrorResponseType>(new ErrorResponseType(new Date(System.currentTimeMillis()).toString(), "404- NOT FOUND", nfe.getMessage()), HttpStatus.NOT_FOUND);
    }
}
