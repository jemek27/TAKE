package com.jureczko.take.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientRequest {
    public String name;
    public int stockStatus;
}