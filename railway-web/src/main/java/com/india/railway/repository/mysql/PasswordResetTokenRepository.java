package com.india.railway.repository.mysql;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.india.railway.model.mysql.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);
}