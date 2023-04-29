package com.beerlot.domain.auth.security.oauth.filter;

import com.beerlot.domain.auth.security.jwt.service.JwtService;
import com.beerlot.domain.auth.security.oauth.entity.OAuthUserPrincipal;
import com.beerlot.domain.auth.util.HeaderUtils;
import com.beerlot.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuthAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        String bearerToken = HeaderUtils.getAccessToken(request);

        if (StringUtils.hasText(bearerToken)) {
            String email = jwtService.getClaims(bearerToken).getSubject();

            OAuthUserPrincipal userPrincipal = OAuthUserPrincipal.of(memberService.findMemberByEmail(email).get());

            AbstractAuthenticationToken authentication = new OAuth2AuthenticationToken(
                    userPrincipal,
                    userPrincipal.getAuthorities(),
                    userPrincipal.getProvider().getRegistrationId());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
