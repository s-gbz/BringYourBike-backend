package de.trzpiot.bringyourbike.service;

import de.trzpiot.bringyourbike.domain.Bike;
import de.trzpiot.bringyourbike.exception.BikeNotFoundException;
import de.trzpiot.bringyourbike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OwnerService {
    private final LightService lightService;
    private final BikeRepository bikeRepository;
    private final String BRIDGE_IP;
    private final String BRIDGE_USERNAME;
    private final long LIGHT_ID;

    @Autowired
    public OwnerService(LightService lightService, BikeRepository bikeRepository, Environment environment) {
        this.lightService = lightService;
        this.bikeRepository = bikeRepository;

        BRIDGE_IP = environment.getProperty("bridge.ip");
        BRIDGE_USERNAME = environment.getProperty("bridge.username");
        LIGHT_ID = Long.parseLong(Objects.requireNonNull(environment.getProperty("bridge.light.id")));
    }

    public Bike addBike(Bike bike) {
        lightService.switchOn(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID);
        lightService.setBrightness(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID, 255);
        setColor(bike.getPriority());
        return bikeRepository.save(bike);
    }

    public boolean updateBike(Bike bike) {
        Optional<Bike> bikeInDatabase = bikeRepository.getBikeByPin(bike.getPin());

        if (bikeInDatabase.isPresent()) {
            lightService.switchOn(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID);
            lightService.setBrightness(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID, 255);
            setColor(bike.getPriority());
            Bike updatedBike = bikeRepository.save(bike);
            return updatedBike.equals(bike);
        } else {
            throw new BikeNotFoundException(String.format("Bike with PIN '%s' not found", bike.getPin()));
        }
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

    private void setColor(long priority) {
        if (priority == 0) { // Green
            lightService.setColor(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID, 76, 175, 80);
        } else if (priority > 0 && priority <= 2) { // Blue
            lightService.setColor(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID, 33, 150, 243);
        } else if (priority > 2 && priority <= 5) { // Yellow
            lightService.setColor(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID, 255, 235, 59);
        } else if (priority >= 6) { // Red
            lightService.setColor(BRIDGE_IP, BRIDGE_USERNAME, LIGHT_ID, 244, 67, 54);
        }
    }
}
