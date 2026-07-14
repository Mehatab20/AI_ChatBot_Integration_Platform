package com.example.ai_chatbot_integration_platform.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest webRequest){
       
        ApiErrorResponse apiErrorResponse=new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        apiErrorResponse.setError("Not Found");
        apiErrorResponse.setMessage(ex.getMessage());
        apiErrorResponse.setPath(webRequest.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex, WebRequest webRequest){

        ApiErrorResponse apiErrorResponse=new ApiErrorResponse();
        apiErrorResponse.setTimestamp(LocalDateTime.now());
        apiErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiErrorResponse.setError("Internal Server Error");
        apiErrorResponse.setMessage(ex.getMessage());
        apiErrorResponse.setPath(webRequest.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(apiErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
