package com.jureczko.take.service;

import com.jureczko.take.model.Dish;
import com.jureczko.take.repository.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DishService {
    private DishRepository dishRepository;
    public List<Dish> findAll() { return dishRepository.findAll(); }
    public Dish findById(Long id) { return dishRepository.findById(id).orElseThrow(); }
    public Dish save(Dish d) { return dishRepository.save(d); }
    public void delete(Long id) { dishRepository.deleteById(id); }
}
