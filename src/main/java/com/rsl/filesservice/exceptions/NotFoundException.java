package com.rsl.filesservice.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException{
    public NotFoundException(Class<?> entityClass){
        super(entityClass, HttpStatus.NOT_FOUND);
    }
}
