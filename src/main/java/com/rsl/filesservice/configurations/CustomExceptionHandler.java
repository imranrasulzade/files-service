package com.rsl.filesservice.configurations;

import com.rsl.filesservice.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public @ResponseBody
    ResponseEntity<?> handleCustomException(final CustomException exception, WebRequest request) {
        log.error("CustomException -> ExceptionHandler -> {} {}", exception, request);
        //configs
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());

    }
}
