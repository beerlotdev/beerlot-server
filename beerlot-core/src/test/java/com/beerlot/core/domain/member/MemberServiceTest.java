package com.beerlot.core.domain.member;

import com.beerlot.core.fixture.Fixture;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.dto.request.MemberProfileRequest;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.member.repository.MemberRepository;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.exception.ConflictException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Fixture.createMember();
    }

    @Nested
    class MemberUpdateProfileTest {
        @Test
        public void should_updateProfile_when_allFieldsAreChanged() {
            Mockito.when(memberRepository.existsByUsername(isA(String.class)))
                    .thenReturn(false);
            MemberProfileRequest memberProfileRequest = MemberProfileRequest.builder()
                    .imageUrl("https://beerlot.com/profiles/official_member_new_image")
                    .statusMessage("Another perfect day for one cold beer!")
                    .username("New Beer Lover")
                    .build();

            MemberResponse result = memberService.updateProfile(member, memberProfileRequest);

            assertSame("https://beerlot.com/profiles/official_member_new_image", result.getImageUrl());
            assertSame("Another perfect day for one cold beer!", result.getStatusMessage());
            assertSame("New Beer Lover", result.getUsername());
        }

        @Test
        public void should_updateProfile_when_usernameIsNotChanged() {
            MemberProfileRequest memberProfileRequest = MemberProfileRequest.builder()
                    .imageUrl("https://beerlot.com/profiles/official_member_new_image")
                    .username("Beer Lover")
                    .statusMessage("Another perfect day for one cold beer!")
                    .build();

            MemberResponse result = memberService.updateProfile(member, memberProfileRequest);

            assertSame("https://beerlot.com/profiles/official_member_new_image", result.getImageUrl());
            assertSame("Another perfect day for one cold beer!", result.getStatusMessage());
            assertSame("Beer Lover", result.getUsername());
        }

        @Test
        public void should_updateProfile_when_onlyUsernameIsChanged() {
            Mockito.when(memberRepository.existsByUsername(isA(String.class)))
                    .thenReturn(false);
            MemberProfileRequest memberProfileRequest = MemberProfileRequest.builder()
                    .imageUrl("https://beerlot.com/profiles/official_member")
                    .username("New Beer Lover")
                    .statusMessage("Perfect day for one cold beer!")
                    .build();

            MemberResponse result = memberService.updateProfile(member, memberProfileRequest);

            assertSame("https://beerlot.com/profiles/official_member", result.getImageUrl());
            assertSame("Perfect day for one cold beer!", result.getStatusMessage());
            assertSame("New Beer Lover", result.getUsername());
        }

        @Test
        public void should_throwException_when_usernameAlreadyExists() {
            Mockito.when(memberRepository.existsByUsername(isA(String.class)))
                    .thenReturn(true);
            MemberProfileRequest memberProfileRequest = MemberProfileRequest.builder()
                    .imageUrl("https://beerlot.com/profiles/official_member_new_image")
                    .statusMessage("Another perfect day for one cold beer!")
                    .username("Exist Username")
                    .build();

            ConflictException exception = assertThrows(ConflictException.class, () -> {
                memberService.updateProfile(member, memberProfileRequest);
            });
        }

        @Test
        public void should_throwException_when_usernameHasChangedIn30LastDays() {
            member.setUsernameUpdatedAtToNow();

            MemberProfileRequest memberProfileRequest = MemberProfileRequest.builder()
                    .imageUrl("https://beerlot.com/profiles/official_member_new_image")
                    .statusMessage("Another perfect day for one cold beer!")
                    .username("Exist Username")
                    .build();

            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
                memberService.updateProfile(member, memberProfileRequest);
            });
        }
    }

    @Nested
    class MemberGetProfileTest {
        @Test
        public void should_getProfile() {
            MemberResponse result = memberService.getProfile(member);

            assertSame("Beer Lover", result.getUsername());
            assertSame("Perfect day for one cold beer!", result.getStatusMessage());
            assertSame("https://beerlot.com/profiles/official_member", result.getImageUrl());
        }
    }
}
