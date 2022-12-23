package com.beerlot.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    // ===== Beer ===== //
    BEER__NOT_EXIST("Beer does not exist."),
    BEER_INTERNATIONAL__NOT_EXIST("Beer international does not exist."),

    // ===== Beer Like ===== //
    BEER_LIKE_NOT_FOUND("User has not liked Beer."),
    BEER_LIKE_CONFLICT("User already liked Beer."),

    // ===== Review ===== //
    REVIEW_NOT_FOUND("Review does not exist."),

    // ===== Review Like ===== //
    REVIEW_LIKE_NOT_FOUND("User has not liked Review."),
    REVIEW_LIKE_CONFLICT("User already liked Review."),

    // ===== Tag ===== //
    TAG_NOT_FOUND("Tag does not exist."),

    // ===== Member ===== //
    MEMBER__ALREADY_SIGNED_UP("Member already signed up."),
    MEMBER__NOT_EXIST("Member does not exist."),

    // ===== Token ===== //
    MEMBER_REFRESH_TOKEN_NOT_EXIST("Member refresh token does not exist."),
    TOKEN__INVALID("Invalid token."),
    TOKEN__NOT_EXPIRED_YET("Token is not expired yet."),
    TOKEN__EXPIRED("Token is expired."),

    // ===== Auth ===== //
    UNAUTHORIZED_REDIRECT_URI("Redirect URI is authorized."),

    // ===== Etc ===== //
    LANGUAGE_INVALID("Language code is invalid or not supported.");

    private final String message;
}
