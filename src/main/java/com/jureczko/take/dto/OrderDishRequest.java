package com.jureczko.take.dto;

import com.jureczko.take.model.OrderDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishRequest {
    private Long dishId;
    private int quantity;

    public OrderDishRequest(OrderDish orderDish) {
        this.dishId = orderDish.getDish().getId();
        this.quantity = orderDish.getQuantity();
    }
}
