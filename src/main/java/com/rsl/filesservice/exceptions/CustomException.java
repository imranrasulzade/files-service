package com.rsl.filesservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private Class<?> entityClass;
    private HttpStatus status;

    public CustomException(HttpStatus status) {
        this.status = status;
    }
}
