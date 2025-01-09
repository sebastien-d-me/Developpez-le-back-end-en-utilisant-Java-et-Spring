package com.sebastiend.ChaTop.models.entities;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class RentalEntity {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="surface")
    private String surface;

    @Column(name="price")
    private String price;

    @Column(name="picture_src")
    private String pictureSrc;

    @Column(name="description", columnDefinition="MEDIUMTEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private UserEntity owner;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;
}
