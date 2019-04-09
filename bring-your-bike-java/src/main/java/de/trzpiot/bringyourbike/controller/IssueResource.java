package de.trzpiot.bringyourbike.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueResource {
    private Long id;
    private Long number;
    private Boolean fixed;
}
