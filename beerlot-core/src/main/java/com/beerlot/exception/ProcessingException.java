package com.beerlot.exception;

import lombok.Getter;

@Getter
public class ProcessingException extends RuntimeException {

    private final String message;

    public ProcessingException(String message) {
        super(message);
        this.message = message;
    }
}
