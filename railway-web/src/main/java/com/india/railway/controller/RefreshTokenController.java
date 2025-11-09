package com.india.railway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.india.railway.authservice.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RefreshTokenController {

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");

        if (claims == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid refresh token");
        }

        String username = claims.getSubject();
        // String newToken = jwtUtil.generateToken(username,);
        String newToken = null;

        return ResponseEntity.ok(new AuthResponse(newToken));
    }

    public  class AuthResponse {
        private String jwt;

        public AuthResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }

        public void setJwt(String jwt) {
            this.jwt = jwt;
        }
    }
}
