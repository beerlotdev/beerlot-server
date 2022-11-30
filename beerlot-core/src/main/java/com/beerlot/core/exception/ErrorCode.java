package com.beerlot.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BEER_NOT_FOUND(HttpStatus.NOT_FOUND, "BR001", "Beer does not exist."),
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "RV001", "Review does not exist."),
    BEER_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "BL001", "User has not liked Beer."),
    BEER_LIKE_CONFLICT(HttpStatus.CONFLICT, "BL002", "User already liked Beer."),
    REVIEW_LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "RL001", "User has not liked Review."),
    REVIEW_LIKE_CONFLICT(HttpStatus.CONFLICT, "RL002", "User already liked Review."),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "TG001", "Tag does not exist."),
    MEMBER_ALREADY_EXIST(HttpStatus.CONFLICT, "MB001", "Member already exist."),
    MEMBER_NOT_EXIST(HttpStatus.NOT_FOUND, "MB002", "Member does not exist."),
    MEMBER_REFRESH_TOKEN_NOT_EXIST(HttpStatus.NOT_FOUND, "MR001", "Member refresh token does not exist.");

    private HttpStatus status;
    private String code;
    private String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
