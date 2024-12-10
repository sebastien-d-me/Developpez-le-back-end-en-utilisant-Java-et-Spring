package com.sebastiend.ChaTop.models.entities;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "messages")
public class MessageEntity {
            
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rental_id")
    private Integer rentalId;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="message")
    private String message;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;
}
