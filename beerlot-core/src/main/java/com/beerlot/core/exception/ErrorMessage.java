package com.beerlot.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    BEER_NOT_FOUND("Beer does not exist."),

    REVIEW_NOT_FOUND("Review does not exist."),

    BEER_LIKE_NOT_FOUND("User has not liked Beer."),
    BEER_LIKE_CONFLICT("User already liked Beer."),

    REVIEW_LIKE_NOT_FOUND("User has not liked Review."),
    REVIEW_LIKE_CONFLICT("User already liked Review."),

    TAG_NOT_FOUND("Tag does not exist."),

    MEMBER__ALREADY_SIGNED_UP("Member already signed up."),
    MEMBER__NOT_EXIST("Member does not exist."),

    MEMBER_REFRESH_TOKEN_NOT_EXIST("Member refresh token does not exist."),
    TOKEN__INVALID("Invalid token."),
    TOKEN__NOT_EXPIRED_YET("Token is not expired yet."),
    TOKEN__EXPIRED("Token is expired."),

    UNAUTHORIZED_REDIRECT_URI("Redirect URI is authorized.");

    private final String message;
}
