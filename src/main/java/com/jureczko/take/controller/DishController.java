package com.jureczko.take.controller;

import com.jureczko.take.model.Dish;
import com.jureczko.take.service.DishService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/dishes")
public class DishController {
    private DishService dishService;
    @GetMapping
    public List<Dish> getAll() { return dishService.findAll(); }

    @GetMapping("/{id}")
    public Dish get(@PathVariable Long id) { return dishService.findById(id); }

    @PostMapping
    public Dish create(@RequestBody Dish d) { return dishService.save(d); }

    @PutMapping("/{id}")
    public Dish update(@PathVariable Long id, @RequestBody Dish d) {
        d.setId(id);
        return dishService.save(d);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { dishService.delete(id); }
}
