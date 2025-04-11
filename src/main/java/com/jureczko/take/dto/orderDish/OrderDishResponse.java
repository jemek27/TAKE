package com.jureczko.take.dto.orderDish;

import com.jureczko.take.controller.DishController;
import com.jureczko.take.model.OrderDish;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class OrderDishResponse extends RepresentationModel<OrderDishResponse> {
    private Long dishId;
    private String name;
    private int quantity;

    public OrderDishResponse(OrderDish orderDish) {
        super();
        this.dishId = orderDish.getDish().getId();
        this.name = orderDish.getDish().getName();
        this.quantity = orderDish.getQuantity();

        this.add(linkTo(methodOn(DishController.class)
                .getIngredientsOfDishById(orderDish.getDish().getId())).withRel("ingredients"));
    }
}
