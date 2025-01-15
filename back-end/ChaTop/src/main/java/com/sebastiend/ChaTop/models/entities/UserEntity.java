package com.sebastiend.ChaTop.models.entities;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "Int")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    @JsonProperty(value = "email")
    private String email;

    @Column(name="password")
    @JsonProperty(value = "password")
    private String password;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;
}