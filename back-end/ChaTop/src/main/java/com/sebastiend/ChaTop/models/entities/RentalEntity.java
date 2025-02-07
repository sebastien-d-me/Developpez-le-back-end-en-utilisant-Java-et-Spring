package com.sebastiend.ChaTop.models.entities;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "rentals")
public class RentalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="surface")
    private Integer surface;

    @Column(name="price")
    private Integer price;

    @Column(name="picture_src")
    private String pictureSrc;

    @Column(name="description", columnDefinition="MEDIUMTEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", columnDefinition = "int")
    private UserEntity owner;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="updated_at")
    private String updatedAt;
}