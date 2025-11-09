package com.india.railway.service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.india.railway.repository.mysql.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        com.india.railway.model.mysql.User userDetails = userRepository.findByUserName(
                username);

        // Convert roles to GrantedAuthority
        Collection<GrantedAuthority> authorities = userDetails.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRolename()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(userDetails.getUserName(),
                userDetails.getPassword(), authorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // return user.getRoles().stream()
        // .map(role -> new SimpleGrantedAuthority(role.getName()))
        // .collect(Collectors.toList());
        List<GrantedAuthority> arr = new ArrayList<>();
        arr.add(new SimpleGrantedAuthority("admin"));
        return arr;
    }
}