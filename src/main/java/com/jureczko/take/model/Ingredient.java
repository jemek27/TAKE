package com.jureczko.take.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Double stockStatus;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)//, orphanRemoval = true)
    private List<Recipe> recipe = new ArrayList<>();
}
