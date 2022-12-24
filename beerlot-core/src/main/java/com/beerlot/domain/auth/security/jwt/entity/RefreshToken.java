package com.beerlot.domain.auth.security.jwt.entity;

import com.beerlot.domain.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "refresh_token")
public class RefreshToken extends BaseEntity {
    @Id
    @Column(name = "oauth_id", nullable = false, unique = true)
    private String oauthId;

    @Column(name = "token", nullable = false, unique = true, columnDefinition = "TEXT")
    private String token;

    @Builder
    public RefreshToken(String oauthId, String token) {
        this.oauthId = oauthId;
        this.token = token;
        setCreatedAt(new Date());
    }

    public void updateRefreshToken(String token) {
        this.token = token;
    }
}
