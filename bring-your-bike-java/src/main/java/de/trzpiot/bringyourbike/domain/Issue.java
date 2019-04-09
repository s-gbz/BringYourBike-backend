package de.trzpiot.bringyourbike.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Issue {
    @Id
    private long id;
    private long number;
    private boolean fixed;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;
}
