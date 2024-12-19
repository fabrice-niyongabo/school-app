package com.fabrice.studentregistration.studentregistration.exceptions;


import com.fabrice.studentregistration.studentregistration.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handle not found exception
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    //handling bad request exception
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //unique column exception handler
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleUniqueConstraintViolation(DataIntegrityViolationException ex) {


        ErrorResponse errorResponse = new ErrorResponse();
        String columnValue = "";

        // You can customize the error message here
        // errorResponse.setMessage( "Error: Duplicate value detected for a unique column. ");

        // Extract column value from the exception message using a regular expression
        String exceptionMessage = ex.getMessage();
        Pattern pattern = Pattern.compile("Duplicate entry '(.+)' for key '(.+)'");
        Matcher matcher = pattern.matcher(exceptionMessage);

        if (matcher.find()) {
            columnValue = matcher.group(1);
        } else {
            columnValue += "unknown column name";
        }

        //formatted message
        String message = columnValue + " already exists. Try again with different values";

        errorResponse.setMessage(message);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    } 

    // global exceptions => reformatting spring boot exception response
    // for all remaining kind of errors which may occur within our app
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(Exception exception) {
        String message = exception.getMessage();
        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
