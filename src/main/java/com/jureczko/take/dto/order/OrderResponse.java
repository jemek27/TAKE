package com.jureczko.take.dto.order;


import com.jureczko.take.dto.orderDish.OrderDishResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    public Long id;
    private Long clientId;
    private List<OrderDishResponse> dishes;
    private LocalDateTime orderDateTime;
    private Double totalPrice;
}
