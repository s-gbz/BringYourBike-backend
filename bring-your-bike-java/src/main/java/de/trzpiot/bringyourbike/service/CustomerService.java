package de.trzpiot.bringyourbike.service;

import de.trzpiot.bringyourbike.domain.Bike;
import de.trzpiot.bringyourbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final BikeRepository bikeRepository;

    @Autowired
    public CustomerService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike getOneBike(Long id) {
        return bikeRepository.getOne(id);
    }
}
