package com.beerlot.core.domain.auth.security.oauth.service;

import com.beerlot.core.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.core.domain.auth.security.oauth.entity.ProviderType;
import com.beerlot.core.domain.member.Member;
import com.beerlot.core.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OAuthService extends DefaultOAuth2UserService {

    private final MemberService memberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1: Get provider id from UserRequest
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 2: Get oauth user information attributes
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 3: Get member profile out of attributes
        OAuthUserPrincipal oAuthUserPrincipal = ProviderType.getOAuthUser(registrationId, attributes);

        // 4: Check if registered user
        Optional<Member> memberOptional = memberService.findMemberByOauthId(oAuthUserPrincipal.getId());

        // 5-1: If yes, update email return user info
        if (memberOptional.isPresent()) {
            memberService.updateEmail(memberOptional.get(), oAuthUserPrincipal.getEmail());
            return OAuthUserPrincipal.of(memberOptional.get());

        // 5-2: If not, return user info with role_guest
        } else {
            long count = memberService.countByUsername(oAuthUserPrincipal.getUsername());
            oAuthUserPrincipal.setUsername(oAuthUserPrincipal.getUsername() + String.valueOf(count + 1));
            memberService.createMember(oAuthUserPrincipal);
            return oAuthUserPrincipal;
        }
    }
}
