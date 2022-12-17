package com.beerlot.core.domain.auth.jwt.repository;

import com.beerlot.core.domain.auth.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByOauthId(String oauthId);
}
