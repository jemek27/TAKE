package com.jureczko.take.dto.ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientResponse {
    public Long id;
    public String name;
    public int stockStatus;
}
