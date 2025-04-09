package com.jureczko.take.controller;

import com.jureczko.take.dto.dish.*;
import com.jureczko.take.mapper.DishMapper;
import com.jureczko.take.model.Dish;
import com.jureczko.take.service.DishService;
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

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@RequestBody DishRequest dishRequest) {
        Dish dish = dishMapper.toEntity(dishRequest);
        Dish savedDish = dishService.saveDish(dish);
        return ResponseEntity.ok(dishMapper.toDto(savedDish));
    }

    @GetMapping
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        return ResponseEntity.ok(
                dishService.getAllDishes().stream()
                        .map(dishMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable Long id) {
        return dishService.getDishById(id)
                .map(dishMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable Long id, @RequestBody DishRequest dishRequest) {
        Dish dish = dishMapper.toEntity(dishRequest);
        dish.setId(id);
        Dish savedDish = dishService.saveDish(dish);
        return ResponseEntity.ok(dishMapper.toDto(savedDish));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
