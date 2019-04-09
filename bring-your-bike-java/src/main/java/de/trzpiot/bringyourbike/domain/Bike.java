package de.trzpiot.bringyourbike.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ownerName;
    private String email;
    private String model;
    private Long priority;
    private Short status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private List<Issue> issues;
}
