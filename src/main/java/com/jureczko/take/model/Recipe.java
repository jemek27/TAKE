package com.jureczko.take.model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "recipes")
@Data
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    private Double quantity;
    private String unit;
}
