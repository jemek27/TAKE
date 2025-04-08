package com.jureczko.take.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientDto {
    public Long id;
    public String name;
    public int stockStatus;
}
