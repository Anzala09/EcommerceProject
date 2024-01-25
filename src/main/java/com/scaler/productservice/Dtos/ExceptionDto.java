package com.scaler.productservice.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import javax.swing.*;

@Getter
@Setter
public class ExceptionDto {
    private HttpStatus errorcode;
    private String message;

    public ExceptionDto(HttpStatus status, String message) {
        this.errorcode = status;
        this.message = message;
    }
}
