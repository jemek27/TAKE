package com.jureczko.take.mapper;

import com.jureczko.take.dto.dish.*;
import com.jureczko.take.model.Dish;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface DishMapper {
    Dish toEntity(DishRequest dto);
    //DishResponse toDto(Dish dish);
}
