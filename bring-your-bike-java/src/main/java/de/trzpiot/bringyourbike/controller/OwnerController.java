package de.trzpiot.bringyourbike.controller;

import de.trzpiot.bringyourbike.domain.Bike;
import de.trzpiot.bringyourbike.service.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/owner")
@Transactional
public class OwnerController {
    private final OwnerService ownerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OwnerController(OwnerService ownerService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add-bike")
    public ResponseEntity<BikeResource> addBike(@RequestBody BikeResource resource) {
        Bike bike = convertToEntity(resource);
        bike.setPin(ownerService.generatePin());
        return new ResponseEntity<>(convertToResource(ownerService.addBike(bike)), HttpStatus.CREATED);
    }

    @PostMapping("/update-bike")
    @ResponseStatus(HttpStatus.OK)
    public void updateBike(@RequestBody BikeResource resource) {
        ownerService.updateBike(convertToEntity(resource));
    }

    @GetMapping("/get-bike/{pin}")
    public ResponseEntity<BikeResource> getBike(@PathVariable Long pin) {
        return new ResponseEntity<>(convertToResource(ownerService.getBikeByPin(pin)), HttpStatus.OK);
    }

    private BikeResource convertToResource(Bike bike) {
        return modelMapper.map(bike, BikeResource.class);
    }

    private Bike convertToEntity(BikeResource resource) {
        return modelMapper.map(resource, Bike.class);
    }
}
