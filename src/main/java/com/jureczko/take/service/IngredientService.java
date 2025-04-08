package com.jureczko.take.service;

import com.jureczko.take.model.Ingredient;
import com.jureczko.take.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private IngredientRepository ingredientRepository ;
    public List<Ingredient> findAll() { return ingredientRepository.findAll(); }
    public Ingredient findById(Long id) { return ingredientRepository.findById(id).orElseThrow(); }
    public Ingredient save(Ingredient i) { return ingredientRepository.save(i); }
    public void delete(Long id) { ingredientRepository.deleteById(id); }
}