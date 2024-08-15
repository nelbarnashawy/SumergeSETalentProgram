package com.sumerge.springtask.service;

import com.sumerge.springtask.model.UserEntity;
import com.sumerge.springtask.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity foundUser = this.userRepository.findByUsername(username);
        if(foundUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.withUsername(foundUser.getUsername())
                .password(foundUser.getPassword())
                .roles("ROLE_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }
}
