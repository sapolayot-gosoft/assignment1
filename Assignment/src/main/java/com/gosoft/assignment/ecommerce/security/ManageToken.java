package com.gosoft.assignment.ecommerce.security;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ManageToken {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static JwtTokenProvider tokenProvider0;

    private static CustomUserDetailsService userDetailsService0;

    @PostConstruct
    private void initStaticDao() {
        tokenProvider0 = tokenProvider;
        userDetailsService0 = userDetailsService;
    }

    public static String generateTokenFromUsername(String username){
        UserDetails userDetails = userDetailsService0.loadUserByUsername(username);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider0.generateJwtToken((UserPrincipal) authentication.getPrincipal());
    }
}
