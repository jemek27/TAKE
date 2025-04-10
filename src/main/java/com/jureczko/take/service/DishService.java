package com.jureczko.take.service;

import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.model.Dish;
import com.jureczko.take.dto.dish.*;
import com.jureczko.take.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
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
}
