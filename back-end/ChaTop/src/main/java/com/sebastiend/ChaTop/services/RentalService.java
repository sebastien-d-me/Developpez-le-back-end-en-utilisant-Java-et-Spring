package com.sebastiend.ChaTop.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastiend.ChaTop.models.entities.RentalEntity;
import com.sebastiend.ChaTop.repositories.RentalRepository;

import lombok.Data;

@Data
@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public Optional<RentalEntity> getRental(final Long id) {
        // Renvoyer un objet reformaté avec données utiles uniquement
        return rentalRepository.findById(id);
    }

    public Iterable<RentalEntity> getRentals() {
        return rentalRepository.findAll();
    }

    public void deleteRental(final Long id) {
        rentalRepository.deleteById(id);
    }

    public RentalEntity saveRental(RentalEntity rental) {
        RentalEntity savedRental = rentalRepository.save(rental);
        return savedRental;
    }
}
