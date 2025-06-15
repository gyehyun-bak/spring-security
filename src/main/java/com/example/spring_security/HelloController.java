package com.example.spring_security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")

    public String index(Authentication authentication) {
        log.info("authentication={}", authentication);
        return "Hello " + authentication.getName();
    }
}
