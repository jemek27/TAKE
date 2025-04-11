package com.jureczko.take.controller;

import com.jureczko.take.dto.dish.*;
import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.mapper.DishMapper;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Ingredient;
import com.jureczko.take.service.DishService;
import com.jureczko.take.service.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/dishes")
public class DishController {
    private final DishService dishService;
    private final DishMapper dishMapper;
    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        return ResponseEntity.ok(
                dishService.getAllDishes().stream()
                        .map(DishResponse::new)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable Long id) {
        Dish dish = dishService.getDishById(id);
        return ResponseEntity.ok(new DishResponse(dish));
    }

    @GetMapping("/{id}/ingredients")
    public ResponseEntity<List<String>> getIngredientsOfDishById(@PathVariable Long id) {
        return ResponseEntity.ok(
                ingredientService.getIngredientsByDishId(id).stream()
                        .map(Ingredient::getName)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<DishResponse>> getDishesByName(@PathVariable String name) {
        return ResponseEntity.ok(
                dishService.getDishesByName(name).stream()
                        .map(DishResponse::new)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/available")
    public ResponseEntity<List<DishResponse>> getAvailableDishes() {
        return ResponseEntity.ok(
                dishService.getAvailableDishes().stream()
                        .map(DishResponse::new)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@Valid @RequestBody DishRequest dishRequest) {
        Dish dish = dishMapper.toEntity(dishRequest);
        Dish savedDish = dishService.saveDish(dish);
        return ResponseEntity.ok(new DishResponse(savedDish));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable Long id, @Valid @RequestBody DishRequest dishRequest) {
        if (!dishService.existsById(id)) {
            throw new ResourceNotFoundException("Dish with ID " + id + " not found");
        }
        Dish dish = dishMapper.toEntity(dishRequest);
        dish.setId(id);
        Dish savedDish = dishService.saveDish(dish);
        return ResponseEntity.ok(new DishResponse(savedDish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
