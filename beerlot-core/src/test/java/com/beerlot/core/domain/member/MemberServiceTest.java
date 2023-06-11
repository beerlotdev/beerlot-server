package com.beerlot.core.domain.member;

import com.beerlot.core.fixture.Fixture;
import com.beerlot.domain.member.Member;
import com.beerlot.domain.member.dto.request.MemberProfileRequest;
import com.beerlot.domain.member.dto.request.MemberUsernameRequest;
import com.beerlot.domain.member.dto.response.MemberResponse;
import com.beerlot.domain.member.dto.response.MemberUsernameResponse;
import com.beerlot.domain.member.service.MemberService;
import com.beerlot.exception.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    private Member member;

    @BeforeEach
    public void setUp() {
        member = Fixture.createMember();
    }

    @Nested
    class MemberUpdateProfileTest {
        @Test
        public void should_updateProfile() {
            MemberProfileRequest memberProfileRequest = MemberProfileRequest.builder()
                    .imageUrl("https://beerlot.com/profiles/official_member_new_image")
                    .statusMessage("New Status Message!")
                    .build();

            MemberResponse result = memberService.updateProfile(member, memberProfileRequest);

            assertSame("Beer Lover", result.getUsername());
            assertSame("https://beerlot.com/profiles/official_member_new_image", result.getImageUrl());
            assertSame("New Status Message!", result.getStatusMessage());
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

    @Nested
    class MemberUpdateUsernameTest {
        @Test
        public void should_updateUsername() {
            MemberUsernameRequest memberUsernameRequest = MemberUsernameRequest.builder()
                    .username("New Beer Lover")
                    .build();

            MemberUsernameResponse result = memberService.updateUsername(member, memberUsernameRequest);

            assertSame("New Beer Lover", result.getUsername());
        }

        @Test
        public void should_throwException_when_memberAlreadyChangedUsernameWithin30days() {
            member.setUsernameUpdatedAtToNow();
            MemberUsernameRequest memberUsernameRequest = MemberUsernameRequest.builder()
                    .username("New Beer Lover")
                    .build();

            IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
                memberService.updateUsername(member, memberUsernameRequest);
            });
            assertTrue(exception.getMessage().contains(ErrorMessage.MEMBER__USERNAME_30DAYS.getMessage()));
        }
    }


}
