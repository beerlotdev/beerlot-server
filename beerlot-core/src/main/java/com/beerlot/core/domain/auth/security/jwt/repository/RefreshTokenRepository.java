package com.beerlot.core.domain.auth.security.jwt.repository;

import com.beerlot.core.domain.auth.security.jwt.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByOauthId(String oauthId);
}
