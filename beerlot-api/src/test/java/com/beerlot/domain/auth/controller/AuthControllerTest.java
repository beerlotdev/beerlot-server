package com.beerlot.domain.auth.controller;

import com.beerlot.config.JwtConfig;
import com.beerlot.config.SecurityConfig;
import com.beerlot.domain.auth.AuthController;
import com.beerlot.domain.auth.security.jwt.service.TokenService;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.auth.security.oauth.filter.OAuthAuthenticationFilter;
import com.beerlot.domain.auth.security.oauth.repository.OAuthAuthorizationRequestCookieRepository;
import com.beerlot.domain.auth.security.oauth.service.OAuthService;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.RoleType;
import com.beerlot.domain.member.dto.request.MemberRequest;
import com.beerlot.domain.member.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static com.beerlot.tool.fixture.Fixture.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
                            .with(csrf())
                    )
                    .andExpect(status().isOk());
        }

        @Test
        @WithMockUser
        public void signup_memberNotGuest() throws Exception {

            mockMvc.perform(patch("/api/v1/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(request))
                            .with(csrf())
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
                        .with(csrf())
                    )
                    .andExpect(status().isBadRequest());
        }
    }
}