package de.trzpiot.bringyourbike.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BikeResource {
    private Long id;
    private String ownerName;
    private String email;
    private String model;
    private Long priority;
    private Short status;
    private List<IssueResource> issues;
}
