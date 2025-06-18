package com.example.spring_security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/permitted")
    public String permitted() {
        SecurityContext context = SecurityContextHolder.getContext();
        log.info("context={}", context);
        log.info("context.authentication={}", context.getAuthentication());
        return "Hello " + context.getAuthentication().getName();
    }

    @Secured(value = { "ROLE_USER" })
    @GetMapping("/secured/user")
    public String securedUser(Authentication authentication) {
        log.info("authentication={}", authentication);
        return "Hello " + authentication.getName();
    }

    @Secured(value = { "ROLE_ADMIN" })
    @GetMapping("/secured/admin")
    public String securedAdmin(Authentication authentication) {
        log.info("authentication={}", authentication);
        return "Hello " + authentication.getName();
    }
}
