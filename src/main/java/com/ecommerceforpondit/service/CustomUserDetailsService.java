package com.ecommerceforpondit.ecommerceforpondit.service;

import com.ecommerceforpondit.ecommerceforpondit.model.User;
import com.ecommerceforpondit.ecommerceforpondit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AopAutoConfiguration aopAutoConfiguration;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                      .findByUsername(username)
                      .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        System.out.println( user.toString() );
        return org.springframework.security.core.userdetails.User
                 .builder()
                 .username(username)
                 .password(user.getPassword())
                 .roles(user.getRoles())
                 .build();
    }
}
