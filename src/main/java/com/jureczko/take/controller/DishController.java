package com.jureczko.take.controller;

import com.jureczko.take.dto.dish.*;
import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.mapper.DishMapper;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Ingredient;
import com.jureczko.take.service.DishService;
import com.jureczko.take.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;

import java.time.LocalDateTime;
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
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Dish> pagedResourcesAssembler;

    @GetMapping
    @Operation(summary = "Get a paginated list of dishes")
    public ResponseEntity<PagedModel<DishResponse>> getAllDishes(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(
                pagedResourcesAssembler.toModel(
                        dishService.getAllDishes(pageable),
                        DishResponse::new
                )
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

    @GetMapping("/report")
    public ResponseEntity<List<DishReportResponse>> getDishReport(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<DishReportResponse> report = dishService.getDishReport(startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
