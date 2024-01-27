package ru.segam.user_service.exception;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static int INVALID_REQUEST = 400;
    private final static int INCORRECT_PASSWORD = 401;
    private final static int NOT_FOUND = 404;
    private final static int CONFLICT = 409;

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(
            final InvalidRequestException e) {
        ErrorResponse response = new ErrorResponse(INVALID_REQUEST,
                "Invalid request. Make sure that the entered data"
                        + " is correct and try again. " + e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectPasswordException(
            final IncorrectPasswordException e) {
        ErrorResponse response = new ErrorResponse(INCORRECT_PASSWORD,
                "The password was entered incorrectly. " + e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(
            final UserNotFoundException e) {
        ErrorResponse response = new ErrorResponse(NOT_FOUND,
                "User not found. " + e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NickNameBusyException.class)
    public ResponseEntity<ErrorResponse> handleNickNameBusyException(
            final NickNameBusyException e) {
        ErrorResponse response = new ErrorResponse(CONFLICT,
                "This nickname is busy. " + e.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
