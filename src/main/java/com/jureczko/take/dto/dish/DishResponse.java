package com.jureczko.take.dto.dish;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishResponse {
    public Long id;
    public String name;
    public Double price;
}
