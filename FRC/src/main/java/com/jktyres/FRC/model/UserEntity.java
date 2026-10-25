package com.jktyres.FRC.model;

import com.jktyres.FRC.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(unique = true ,nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, name = "is_active")
    private Boolean isActive = true;
    @Column(nullable = false, name = "last_login")
    private LocalDateTime lastLogin;
}
