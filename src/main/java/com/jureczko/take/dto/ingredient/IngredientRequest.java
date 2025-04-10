package com.jureczko.take.dto.ingredient;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientRequest {
    @NotNull(message = "Ingredient name is required")
    public String name;
    @Positive(message = "Ingredient quantity must be a positive number")
    public int stockStatus;
}