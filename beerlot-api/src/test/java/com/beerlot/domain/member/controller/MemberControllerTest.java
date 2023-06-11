package com.beerlot.domain.member.controller;

import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.common.entity.LanguageType;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.dto.request.MemberProfileRequest;
import com.beerlot.domain.member.dto.request.MemberUsernameRequest;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.member.dto.response.MemberUsernameResponse;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.exception.NotFoundException;
import com.beerlot.tool.fixture.Fixture;
import com.beerlot.tool.fixture.MemberFixture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.isA;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MemberService memberService;

    final String MEMBER_BASEURL = "/api/v1/members";

    private Member member;
    private Member guestMember;

    @BeforeEach
    public void setUp() {
        member = MemberFixture.createMember();
        guestMember = MemberFixture.createGuestMember();
    }

    @Nested
    class GetMemberProfileTest {
        @Test
        public void should_getMemberProfile() throws Exception {
            Mockito.when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenReturn(member);
            Mockito.when(memberService.getProfile(isA(Member.class)))
                    .thenReturn(MemberResponse.of(member));

            mockMvc.perform(get(MEMBER_BASEURL + "/me")
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        public void should_throwException_when_memberNotExist() throws Exception {
            Mockito.when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenThrow(NoSuchElementException.class);

            mockMvc.perform(get(MEMBER_BASEURL + "/me")
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isNotFound());
        }

        @Test
        public void should_throwException_when_guestMember() throws Exception {
            mockMvc.perform(get(MEMBER_BASEURL + "/me")
                            .with(user(OAuthUserPrincipal.of(guestMember)))
                    )
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    class UpdateMemberProfileTest {

        private MemberProfileRequest memberProfileRequest;

        @BeforeEach
        public void setUp() {
            memberProfileRequest = MemberProfileRequest.builder()
                    .statusMessage("")
                    .imageUrl("")
                    .build();
        }

        @Test
        void should_updateMemberProfile() throws Exception {
            Mockito.when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenReturn(member);
            Mockito.when(memberService.updateProfile(isA(Member.class), isA(MemberProfileRequest.class)))
                    .thenReturn(MemberResponse.of(member));

            mockMvc.perform(put(MEMBER_BASEURL + "/me")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(memberProfileRequest))
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        void should_throwException_when_memberNotExist() throws Exception {
            Mockito.when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenThrow(NoSuchElementException.class);

            mockMvc.perform(put(MEMBER_BASEURL + "/me")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(memberProfileRequest))
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isNotFound());
        }

        @Test
        void should_throwException_when_guestMember() throws Exception {
            mockMvc.perform(put(MEMBER_BASEURL + "/me")
                    .with(user(OAuthUserPrincipal.of(guestMember))))
                    .andExpect(status().isForbidden());
        }

        @Test
        void should_throwException_when_imageUrlIsNull() throws Exception {
            MemberProfileRequest incompleteRequest = MemberProfileRequest.builder()
                    .imageUrl(null)
                    .statusMessage("")
                    .build();

            mockMvc.perform(put(MEMBER_BASEURL + "/me")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(incompleteRequest))
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        void should_throwException_when_statusMessageIsNull() throws Exception {
            MemberProfileRequest incompleteRequest = MemberProfileRequest.builder()
                    .imageUrl("")
                    .statusMessage(null)
                    .build();

            mockMvc.perform(put(MEMBER_BASEURL + "/me")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(incompleteRequest))
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    class UpdateMemberUsernameTest {
        private MemberUsernameRequest memberUsernameRequest;

        @BeforeEach
        public void setUp() {
            memberUsernameRequest = MemberUsernameRequest.builder()
                    .username("abc")
                    .build();
        }

        @Test
        void should_updateMemberUsername() throws Exception {
            Mockito.when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenReturn(member);
            Mockito.when(memberService.updateUsername(isA(Member.class), isA(MemberUsernameRequest.class)))
                    .thenReturn(MemberUsernameResponse.of("abc"));

            mockMvc.perform(put(MEMBER_BASEURL + "/username")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(memberUsernameRequest))
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isOk());
        }

        @Test
        void should_throwException_when_memberNotExist() throws Exception {
            Mockito.when(memberService.findMemberByOauthId(isA(String.class)))
                    .thenThrow(NoSuchElementException.class);

            mockMvc.perform(put(MEMBER_BASEURL + "/username")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(memberUsernameRequest))
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isNotFound());
        }

        @Test
        void should_throwException_when_guestMember() throws Exception {
            mockMvc.perform(put(MEMBER_BASEURL + "/username")
                            .with(user(OAuthUserPrincipal.of(guestMember)))
                    )
                    .andExpect(status().isForbidden());
        }

        @Test
        void should_throwException_when_usernameIsNull() throws Exception {
            MemberUsernameRequest incompleteRequest = MemberUsernameRequest.builder()
                            .username(null)
                            .build();

            mockMvc.perform(put(MEMBER_BASEURL + "/username")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(incompleteRequest))
                            .with(user(OAuthUserPrincipal.of(member)))
                    )
                    .andExpect(status().isBadRequest());
        }
    }
}
