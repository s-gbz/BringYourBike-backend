package de.trzpiot.bringyourbike.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Issue {
    @Id
    private Long id;
    private Long number;
    private Boolean fixed;

    @ManyToOne
    @JoinColumn(name = "bike_id")
    private Bike bike;
}
