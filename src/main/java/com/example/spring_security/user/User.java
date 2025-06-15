package com.example.spring_security.user;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
