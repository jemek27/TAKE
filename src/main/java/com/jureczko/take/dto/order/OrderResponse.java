package com.jureczko.take.dto.order;

import com.jureczko.take.dto.OrderDishRequest;
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
    private List<OrderDishRequest> dishes;
    private LocalDateTime orderDateTime;
    private Double totalPrice;
}
