package de.trzpiot.bringyourbike.service;

import de.trzpiot.bringyourbike.domain.Bike;
import de.trzpiot.bringyourbike.exception.BikeNotFoundException;
import de.trzpiot.bringyourbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final BikeRepository bikeRepository;

    @Autowired
    public CustomerService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public Bike getBikeByPin(Long pin) {
        Optional<Bike> bike = bikeRepository.getBikeByPin(pin);

        if (bike.isPresent())
            return bike.get();
        else
            throw new BikeNotFoundException(String.format("Bike with PIN '%s' not found", pin));
    }
}
