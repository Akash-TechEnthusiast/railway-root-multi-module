package com.india.railway.service.mysql;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @SuppressWarnings("null")
    public Optional<String> getCurrentAuditor() {
        // Implement logic to fetch current user (e.g., from Security Context)
        return Optional.of("DefaultUser"); // Replace with actual user
    }
}
