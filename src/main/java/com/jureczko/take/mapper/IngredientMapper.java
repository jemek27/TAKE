package com.jureczko.take.mapper;

import com.jureczko.take.dto.ingredient.*;
import com.jureczko.take.model.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientResponse toDto(Ingredient ingredient);
    Ingredient toEntity(IngredientRequest ingredientRequest);
}
