package com.beerlot.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    // ===== Beer ===== //
    BEER__NOT_EXIST("Beer does not exist."),
    BEER_INTERNATIONAL__NOT_EXIST("Beer international does not exist."),

    // ===== Beer Like ===== //
    BEER_LIKE_NOT_FOUND("Member has not liked the beer yet."),
    BEER_LIKE_CONFLICT("Member already liked the beer."),

    // ===== Review ===== //
    REVIEW_NOT_FOUND("Review does not exist."),

    // ===== Review Like ===== //
    REVIEW_LIKE_NOT_FOUND("Member has not liked the review yet."),
    REVIEW_LIKE_CONFLICT("Member already liked the review."),

    // ===== Tag ===== //
    TAG_NOT_FOUND("Tag does not exist."),

    // ===== Member ===== //
    MEMBER__ALREADY_SIGNED_UP("Member already signed up."),
    MEMBER__NOT_EXIST("Member does not exist."),
    MEMBER__ACCESS_DENIED("Member does not have proper rights."),

    // ===== Token ===== //
    MEMBER_REFRESH_TOKEN_NOT_EXIST("Member refresh token does not exist."),
    TOKEN__INVALID("Token is invalid."),
    TOKEN__NOT_EXPIRED_YET("Token is not expired yet."),
    TOKEN__EXPIRED("Token is expired."),

    // ===== Auth ===== //
    UNAUTHORIZED_REDIRECT_URI("Redirect URI is authorized."),
    NO_AUTHORIZATION("No Authorization was found."),

    // ===== Etc ===== //
    LANGUAGE__INVALID("Language code is invalid or not supported."),
    POLICY__REQUIRED_NOT_AGREED("Required policies have not agreed yet.");

    private final String message;
}
