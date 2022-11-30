package com.beerlot.core.domain.auth;

import com.beerlot.core.domain.common.BaseEntity;
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
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Builder
    public RefreshToken(String email, String token) {
        this.email = email;
        this.token = token;
        setCreatedAt(new Date());
    }

    public void updateRefreshToken(String token) {
        this.token = token;
    }
}
