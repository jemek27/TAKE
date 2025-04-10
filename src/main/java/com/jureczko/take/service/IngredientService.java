package com.jureczko.take.service;

import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Ingredient;
import com.jureczko.take.model.Recipe;
import com.jureczko.take.repository.DishRepository;
import com.jureczko.take.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final DishRepository dishRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingredient with ID " + id + " not found"));
    }

    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        Ingredient ingredient = getIngredientById(id);
        ingredientRepository.delete(ingredient);
    }

    public boolean existsById(Long id) {
        return ingredientRepository.existsById(id);
    }

    public List<Ingredient> getIngredientsByDishId(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dish with ID " + id + " not found"));
        return dish.getRecipe().stream().map(Recipe::getIngredient).collect(Collectors.toList());
    }
}