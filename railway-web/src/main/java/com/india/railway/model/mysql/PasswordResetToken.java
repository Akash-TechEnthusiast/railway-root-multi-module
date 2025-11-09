package com.india.railway.model.mysql;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Foreign key in User table
    @NotNull(message = "user must not be null")
    private User user;

    private LocalDateTime expirationDate;

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expirationDate = LocalDateTime.now().plusHours(1); // 1 hour expiry
    }

    // Getters and setters
}
