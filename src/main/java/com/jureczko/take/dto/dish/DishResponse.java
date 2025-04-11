package com.jureczko.take.dto.dish;

import com.jureczko.take.controller.DishController;
import com.jureczko.take.model.Dish;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class DishResponse extends RepresentationModel<DishResponse> {
    public Long id;
    public String name;
    public Double price;

    public DishResponse(Dish dish) {
        super();
        this.id = dish.getId();
        this.name = dish.getName();
        this.price = dish.getPrice();

        this.add(linkTo(methodOn(DishController.class)
                .getIngredientsOfDishById(dish.getId())).withRel("ingredients"));
    }
}
