package com.jureczko.take.dto.orderDish;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishRequest {
    private Long dishId;
    @Min(value = 1, message = "Quantity must be at least 1.")
    private int quantity;
}
