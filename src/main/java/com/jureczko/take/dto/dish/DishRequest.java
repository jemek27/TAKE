package com.jureczko.take.dto.dish;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishRequest {
    public String name;
    public Double price;
}
