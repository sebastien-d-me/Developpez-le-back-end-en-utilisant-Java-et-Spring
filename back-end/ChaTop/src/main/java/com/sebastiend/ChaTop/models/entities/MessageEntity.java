package com.sebastiend.ChaTop.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class MessageEntity {
            
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rental_id", referencedColumnName = "id")
    private RentalEntity rental;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name="message", columnDefinition = "MEDIUMTEXT")
    private String message;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;
}
