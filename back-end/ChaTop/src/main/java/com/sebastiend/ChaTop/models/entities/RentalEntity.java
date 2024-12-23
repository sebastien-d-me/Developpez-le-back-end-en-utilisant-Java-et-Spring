package com.sebastiend.ChaTop.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class RentalEntity {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surface")
    private String surface;

    @Column(name="price")
    private String price;

    @Column(name="picture_src")
    private String pictureSrc;

    @Column(name="description")
    private String description;

    @Column(name="owner_id")
    private Integer ownerId;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;
}
