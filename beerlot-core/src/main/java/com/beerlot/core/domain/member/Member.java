package com.beerlot.core.domain.member;

import com.beerlot.core.domain.auth.security.oauth.entity.ProviderType;
import com.beerlot.core.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "oauth_id", nullable = false, unique = true)
    private String oauthId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private ProviderType provider;

    @Convert(converter = RoleTypeConverter.class)
    @Column(name = "roles", nullable = false)
    private Set<RoleType> roles;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "status_message")
    private String statusMessage;

    @Builder
    public Member(String oauthId, String email, String username, ProviderType provider, Set<RoleType> roles, String imageUrl, String statusMessage) {
        this.oauthId = oauthId;
        this.email = email;
        this.username = username;
        this.provider = provider;
        this.roles = roles;
        this.imageUrl = imageUrl;
        this.statusMessage = statusMessage;
    }

    public void updateEmail(String email) {
        this.email = email;
    }
    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void updateImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void addRole(RoleType role) {
        roles.add(role);
    }
}

