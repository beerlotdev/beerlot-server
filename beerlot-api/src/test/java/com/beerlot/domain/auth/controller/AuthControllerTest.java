package com.beerlot.domain.auth.controller;

import com.beerlot.domain.auth.security.jwt.service.TokenService;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.dto.request.MemberRequest;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.domain.policy.PolicyType;
import com.beerlot.tool.fixture.MemberFixture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberService memberService;

    @MockBean
    TokenService tokenService;

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() {
    }


    @Nested
    class SignUpTest {

        MemberRequest request;
        Member guestMember;
        Member member;

        @BeforeEach
        public void setUp() {
            request = MemberRequest.builder()
                    .username("beerlot")
                    .statusMessage("Never late to open a new beer.")
                    .imageUrl("https://beerlot.com/image_url")
                    .agreedPolicies(Set.of(PolicyType.TERMS_OF_SERVICE, PolicyType.PERSONAL_INFORMATION_POLICY))
                    .build();

            guestMember = MemberFixture.createGuestMember();
            member = MemberFixture.createMember();
        }

        @Test
        public void signup_success() throws Exception {
            when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenReturn(guestMember);
            doNothing().when(memberService).signUpMember(any(), isA(MemberRequest.class));

            mockMvc.perform(patch("/api/v1/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                            .with(user(OAuthUserPrincipal.of(MemberFixture.createMember())))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        public void signup_memberNotGuest() throws Exception {

            mockMvc.perform(patch("/api/v1/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isForbidden());
        }

        @Test
        public void signup_memberAlreadySignedUp() throws Exception {
            when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenReturn(member);
            doThrow(IllegalStateException.class).when(memberService).signUpMember(any(), isA(MemberRequest.class));

            mockMvc.perform(patch("/api/v1/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isBadRequest());
        }
    }
}
