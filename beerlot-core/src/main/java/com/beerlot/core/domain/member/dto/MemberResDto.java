package com.beerlot.core.domain.member.dto;

import com.beerlot.core.domain.common.BaseResDto;

public class MemberResDto extends BaseResDto {

    private String email;

    private String password;

    private String username;

    private String imageUrl;

    private Boolean emailVerified = false;
}
