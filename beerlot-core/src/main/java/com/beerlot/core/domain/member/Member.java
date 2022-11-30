package com.beerlot.core.domain.member;

import com.beerlot.api.generated.model.MemberCreateRequest;
import com.beerlot.core.domain.auth.ProviderType;
import com.beerlot.core.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_type", nullable = false)
    private ProviderType providerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type", nullable = false)
    private RoleType roleType;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "status_message")
    private String statusMessage;

    @Builder
    public Member(String email, String username, ProviderType providerType, RoleType roleType, String imageUrl, String statusMessage) {
        this.email = email;
        this.username = username;
        this.providerType = providerType;
        this.roleType = roleType;
        this.imageUrl = imageUrl;
        this.statusMessage = statusMessage;
    }
}

