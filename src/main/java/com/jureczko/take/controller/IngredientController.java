package com.jureczko.take.controller;

import com.jureczko.take.dto.ingredient.*;
import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.mapper.IngredientMapper;
import com.jureczko.take.model.Ingredient;
import com.jureczko.take.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    @GetMapping
    public ResponseEntity<List<IngredientResponse>> getAllIngredients() {
        return ResponseEntity.ok(
                ingredientService.getAllIngredients().stream()
                        .map(ingredientMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResponse> getIngredientById(@PathVariable Long id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return ResponseEntity.ok(ingredientMapper.toDto(ingredient));
    }

    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@Valid @RequestBody IngredientRequest ingredientRequest) {
        Ingredient ingredient = ingredientMapper.toEntity(ingredientRequest);
        Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientMapper.toDto(savedIngredient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponse> updateIngredient(@PathVariable Long id, @Valid @RequestBody IngredientRequest ingredientRequest) {
        if (!ingredientService.existsById(id)) {
            throw new ResourceNotFoundException("Ingredient with ID " + id + " not found");
        }
        Ingredient ingredient = ingredientMapper.toEntity(ingredientRequest);
        ingredient.setId(id);
        Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);
        return ResponseEntity.ok(ingredientMapper.toDto(savedIngredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }
}
