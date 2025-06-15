package com.example.spring_security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationTest {

    @Test
    @DisplayName("TestAuthenticationToken")
    void testAuthenticationToken() {
        // given
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = new TestingAuthenticationToken("user", "password", "ROLE_USER");
        context.setAuthentication(authentication);

        // when
        SecurityContextHolder.setContext(context);

        // then
        SecurityContext savedContext = SecurityContextHolder.getContext();
        Authentication savedAuthentication = savedContext.getAuthentication();
        String username = savedAuthentication.getName();
        Object principal = savedAuthentication.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = savedAuthentication.getAuthorities();

        System.out.println("username = " + username);
        System.out.println("principal = " + principal);
        for (GrantedAuthority authority : authorities) {
            System.out.println("authority = " + authority.getAuthority());
        }
        System.out.println("details = " + savedContext.getAuthentication().getDetails());
    }

    @Test
    @DisplayName("UsernamePasswordAuthenticationToken")
    void usernamePasswordAuthenticationToken() {
        // given
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        String username = "user";
        String password = "password";
        SimpleGrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_USER");

        Collection<? extends GrantedAuthority> authorities = List.of(roleUser);
        UserDetails userDetails = new User(username, password, authorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        context.setAuthentication(authentication);

        // when
        SecurityContextHolder.setContext(context);

        // then
        SecurityContext savedContext = SecurityContextHolder.getContext();
        Authentication savedAuthentication = savedContext.getAuthentication();
        Object principal = savedAuthentication.getPrincipal();

        assertThat(principal).isInstanceOf(UserDetails.class);
        UserDetails savedUserDetails = (UserDetails) principal;
        assertThat(savedUserDetails.getUsername()).isEqualTo(username);
        assertThat(savedUserDetails.getPassword()).isEqualTo(password);
        assertThat(savedUserDetails.getAuthorities()).hasSize(authorities.size());
    }
}
