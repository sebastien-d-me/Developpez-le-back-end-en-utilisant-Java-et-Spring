package com.sebastiend.ChaTop.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sebastiend.ChaTop.models.entities.RentalEntity;


@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {}