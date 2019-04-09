package de.trzpiot.bringyourbike.service;

import de.trzpiot.bringyourbike.domain.Bike;
import de.trzpiot.bringyourbike.exception.BikeNotFoundException;
import de.trzpiot.bringyourbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

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

    public Bike getBikeByPin(Long pin) {
        Optional<Bike> bike = bikeRepository.getBikeByPin(pin);

        if (bike.isPresent())
            return bike.get();
        else
            throw new BikeNotFoundException(String.format("Bike with PIN '%s' not found", pin));
    }

    public List<Bike> getAllBikes() {
        return bikeRepository.findAll();
    }

    public Long generatePin() {
        while (true) {
            Long pin = ThreadLocalRandom.current().nextLong(1000, 10000);
            if (bikeRepository.countBikeByPin(pin) == 0)
                return pin;
        }
    }
}
