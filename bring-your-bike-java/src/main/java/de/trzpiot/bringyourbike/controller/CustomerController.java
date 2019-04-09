package de.trzpiot.bringyourbike.controller;

import de.trzpiot.bringyourbike.domain.Bike;
import de.trzpiot.bringyourbike.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@Transactional
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(ModelMapper modelMapper, CustomerService customerService) {
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @GetMapping("/bike-status/{id}")
    public ResponseEntity<BikeResource> getBikeStatus(@PathVariable Long id) {
        return new ResponseEntity<>(convertToResource(customerService.getOneBike(id)), HttpStatus.OK);
    }

    private BikeResource convertToResource(Bike bike) {
        return modelMapper.map(bike, BikeResource.class);
    }
}
