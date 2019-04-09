package de.trzpiot.bringyourbike.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Bike {
    @Id
    private Long id;
    private String ownerName;
    private String email;
    private String model;
    private long priority;
    private short status;

    @OneToMany(mappedBy = "bike")
    private List<Issue> issues;
}
