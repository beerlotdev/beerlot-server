package com.beerlot.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BEER_NOT_FOUND(HttpStatus.NOT_FOUND, "B001", "Beer does not exist."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "R001", "Review does not exist."),
    BEER_LIKE_CONFLICT(HttpStatus.OK, "BL001", "User already liked Beer.");

    private HttpStatus status;
    private String code;
    private String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
