package com.example.education.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.education.Authentication.CustomUserDetails;
import com.example.education.entities.User;
import com.example.education.repositories.UserRep;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRep userRepository;

    public CustomUserDetailsService(UserRep userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            return new CustomUserDetails(userOptional.get());
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
