package com.test.fakebook.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.fakebook.entity.User;
import com.test.fakebook.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// Service class for loading user details by username
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    // Autowired repository for users
    private final UserRepository userRepository;

    // Method to load user details by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Find user by username or throw exception if not found
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        // Create UserDetails object with user details and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(),
                true, 
                true, 
                true,
                true, 
                getAuthorities(user));
    }

    // Method to get user authorities
    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
