package com.sebastiend.ChaTop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sebastiend.ChaTop.models.entities.RentalEntity;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Long> {
    
}
