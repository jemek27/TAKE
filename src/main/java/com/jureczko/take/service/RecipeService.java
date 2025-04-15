package com.jureczko.take.service;

import com.jureczko.take.dto.RecipeRequest;
import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Ingredient;
import com.jureczko.take.model.Recipe;
import com.jureczko.take.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final DishService dishService;
    private final IngredientService ingredientService;

    public void addRecipesToDish(Long dishId, List<RecipeRequest> recipeRequests) {
        Dish dish = dishService.getDishById(dishId);
        if (dish == null) {
            throw new ResourceNotFoundException("Dish with ID " + dishId + " not found");
        }

        for (RecipeRequest recipeRequest : recipeRequests) {
            Ingredient ingredient = ingredientService.getIngredientById(recipeRequest.getIngredientId());
            if (ingredient == null) {
                throw new ResourceNotFoundException("Ingredient with ID " + recipeRequest.getIngredientId() + " not found");
            }

            Recipe recipe = new Recipe();
            recipe.setDish(dish);
            recipe.setIngredient(ingredient);
            recipe.setQuantity(recipeRequest.getQuantity());
            recipe.setUnit(recipeRequest.getUnit());

            recipeRepository.save(recipe);
        }
    }

    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }
}

