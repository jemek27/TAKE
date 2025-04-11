package com.jureczko.take.repository;

import com.jureczko.take.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> findByNameContainingIgnoreCase(String name);
    //Page<Dish> findAll(Pageable pageable);
}
