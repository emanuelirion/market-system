package com.emanuel.modal;

import com.emanuel.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


//tabela user no banco de dados
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false,  unique = true)
    @Email(message= "Email should be valid")
    private String email;

    private String phone;
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = false)
    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;



}
