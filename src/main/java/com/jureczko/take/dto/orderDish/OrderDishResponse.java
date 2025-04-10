package com.jureczko.take.dto.orderDish;

import com.jureczko.take.model.OrderDish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishResponse {
    private Long dishId;
    private String name;
    private int quantity;

    public OrderDishResponse(OrderDish orderDish) {
        this.dishId = orderDish.getDish().getId();
        this.name = orderDish.getDish().getName();
        this.quantity = orderDish.getQuantity();
    }
}
