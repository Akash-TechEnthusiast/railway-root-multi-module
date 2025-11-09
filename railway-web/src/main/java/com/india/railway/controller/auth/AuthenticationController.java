package com.india.railway.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.india.railway.authservice.JwtRequest;
import com.india.railway.authservice.JwtResponse;
import com.india.railway.authservice.JwtUtils;
import com.india.railway.model.mysql.User;
import com.india.railway.repository.mysql.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final User userDetails = userRepository.findByUserName(
                jwtRequest.getUsername());

        log.info("Received Request at authenticaton controller");
        final String jwt = jwtUtil.generateToken(userDetails.getUserName(), userDetails.getRoles());

        return new JwtResponse(jwt);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("Invalid credentials", e);
        }
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('USER')") // TEST (it should be added as ROLE_USER in
    // spring context)
    public String register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // user.setPassword("test");
        user.setEmail(user.getEmail());
        user.setMobileNumber(user.getMobileNumber());
        // save the user to the database
        // ...
        User usera = userRepository.save(user);
        return "User registered successfully";
    }
}
