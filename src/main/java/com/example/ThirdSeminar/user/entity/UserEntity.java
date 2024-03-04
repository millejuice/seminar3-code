package com.example.ThirdSeminar.user.entity;

import com.example.ThirdSeminar.user.dto.SignUpDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String userEmail;
    @Column(length = 20)
    private String userPassword;
    @CreationTimestamp
    private Timestamp createdAt;
}
