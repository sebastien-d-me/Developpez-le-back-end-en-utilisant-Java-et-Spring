package com.sebastiend.ChaTop.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @JsonIgnore
    @Column(name="password")
    private String password;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;
}