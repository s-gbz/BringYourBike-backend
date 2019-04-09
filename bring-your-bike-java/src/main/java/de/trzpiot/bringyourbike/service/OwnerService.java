package de.trzpiot.bringyourbike.service;

import de.trzpiot.bringyourbike.domain.Bike;
import de.trzpiot.bringyourbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    private final BikeRepository bikeRepository;

    @Autowired
    public OwnerService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike addBike(Bike bike) {
        return bikeRepository.save(bike);
    }

    public void updateBike(Bike bike) {
        bikeRepository.save(bike);
    }

    public Bike getOneBike(Long id) {
        return bikeRepository.getOne(id);
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }
}
