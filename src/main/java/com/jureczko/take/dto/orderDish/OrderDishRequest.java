package com.jureczko.take.dto.orderDish;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishRequest {
    private Long dishId;
    private int quantity;
}
