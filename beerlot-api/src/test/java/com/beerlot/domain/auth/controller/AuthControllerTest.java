package com.beerlot.domain.auth.controller;

import com.beerlot.domain.auth.security.jwt.service.TokenService;
import com.beerlot.domain.member.dto.request.MemberRequest;
import com.beerlot.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
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

        @BeforeEach
        public void setUp() {
            request = MemberRequest.builder()
                    .username("beerlot")
                    .statusMessage("Never late to open a new beer.")
                    .imageUrl("https://beerlot.com/image_url")
                    .build();
        }

        @Test
        @WithMockUser(roles = {"GUEST"})
        public void signup_success() throws Exception {
            doNothing().when(memberService).signUpMember(any(), isA(MemberRequest.class));

            mockMvc.perform(patch("/api/v1/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isOk())
                    .andDo(MockMvcRestDocumentation.document("userSignup",
                            requestFields(
                                    fieldWithPath("username").description("Username"),
                                    fieldWithPath("status_message").description("User status message"),
                                    fieldWithPath("image_url").description("User profile image")
                            )))
            ;
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
        @WithMockUser(roles = {"GUEST", "MEMBER"})
        public void signup_memberAlreadySignedUp() throws Exception {
            doThrow(IllegalStateException.class).when(memberService).signUpMember(any(), isA(MemberRequest.class));

            mockMvc.perform(patch("/api/v1/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                    )
                    .andExpect(status().isBadRequest());
        }
    }
}
