package com.beerlot.core.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}
