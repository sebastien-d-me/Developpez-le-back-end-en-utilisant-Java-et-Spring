package com.sebastiend.ChaTop.repositories;


import com.sebastiend.ChaTop.models.entities.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {}