package de.trzpiot.bringyourbike.repository;

import de.trzpiot.bringyourbike.domain.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {
    Long countBikeByPin(Long pin);

    Optional<Bike> getBikeByPin(Long pin);
}
