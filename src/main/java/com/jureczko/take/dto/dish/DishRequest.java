package com.jureczko.take.dto.dish;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DishRequest {
    @NotNull(message = "Dish name is required")
    public String name;
    @NotNull(message = "Dish price is required")
    @Positive(message = "Dish price must be a positive number")
    public Double price;
}
