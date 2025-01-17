package com.beerlot.domain.member.repository;

import com.beerlot.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByOauthId(String oauthId);
    long countByUsername(String username);
    boolean existsByUsername(String username);
}
