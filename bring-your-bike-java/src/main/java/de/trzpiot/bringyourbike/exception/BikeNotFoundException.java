package de.trzpiot.bringyourbike.exception;

public class BikeNotFoundException extends RuntimeException {
    public BikeNotFoundException(String message) {
        super(message);
    }
}
