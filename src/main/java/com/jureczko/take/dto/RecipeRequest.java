package com.jureczko.take.dto;

import lombok.Data;

@Data
public class RecipeRequest {
    private Long ingredientId;
    private Double quantity;
    private String unit;
}
