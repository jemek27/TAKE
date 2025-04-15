package com.jureczko.take.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgeGroupReport {
    private String ageGroup;
    private Long orderCount;
}