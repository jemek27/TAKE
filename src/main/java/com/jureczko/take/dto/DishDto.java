package com.jureczko.take.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishDto {
    public Long id;
    public String name;
    public Double price;
}
