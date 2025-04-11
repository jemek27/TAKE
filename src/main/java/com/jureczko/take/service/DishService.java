package com.jureczko.take.service;

import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Ingredient;
import com.jureczko.take.model.Recipe;
import com.jureczko.take.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public Page<Dish> getAllDishes(Pageable pageable) {
        return dishRepository.findAll(pageable);
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dish with ID " + id + " not found"));
    }

    public Dish saveDish(Dish dish) {
        return dishRepository.save(dish);
    }

    public void deleteDish(Long id) {
        Dish dish = getDishById(id);
        dishRepository.delete(dish);
    }

    public boolean existsById(Long id) {
        return dishRepository.existsById(id);
    }

    public List<Dish> getDishesByName(String name){
        return dishRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Dish> getAvailableDishes() {
        return dishRepository.findAll().stream()
                .filter(this::isDishAvailable)
                .collect(Collectors.toList());
    }

    private boolean isDishAvailable(Dish dish) {
        if (dish.getRecipe() == null || dish.getRecipe().isEmpty()) {
            return false;
        }

        for (Recipe recipe : dish.getRecipe()) {
            Ingredient ingredient = recipe.getIngredient();
            if (ingredient.getStockStatus() < recipe.getQuantity()) {
                return false;
            }
        }
        return true;
    }
}
