package com.rbs.mini_zanzibar.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    @ExceptionHandler(LevelDBInitializationException.class)
    public ResponseEntity<ErrorMessage> handleLevelDBInitializationException(LevelDBInitializationException exception) {
        return createResponseEntity(createErrorMessage(exception, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception) {
        return createResponseEntity(createErrorMessage(exception, HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<ErrorMessage> createResponseEntity(ErrorMessage errorMessage) {
        return new ResponseEntity<>(errorMessage, errorMessage.getHttpStatus());
    }

    private ErrorMessage createErrorMessage(AbstractException exception, HttpStatus httpStatus) {
        return new ErrorMessage(exception, httpStatus, getPath(request));
    }

    private String getPath(HttpServletRequest request) {
        return request.getRequestURI();
    }
}