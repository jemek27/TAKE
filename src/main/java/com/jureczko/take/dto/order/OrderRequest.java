package com.jureczko.take.dto.order;

import com.jureczko.take.dto.orderDish.OrderDishRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull(message = "Client ID is required")
    @Positive(message = "Client IDe must be a positive number")
    private Long clientId;
    @NotEmpty(message = "List of dishes can't be empty")
    @Valid  // <-- this validates every OrderDishRequest in the list
    private List<OrderDishRequest> dishes;
    private LocalDateTime orderDateTime;
}
