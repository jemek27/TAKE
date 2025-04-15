package com.jureczko.take.dto.order;

import com.jureczko.take.dto.orderDish.OrderDishResponse;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse extends RepresentationModel<OrderResponse> {
    public Long id;
    private Long clientId;
    private List<OrderDishResponse> dishes;
    private LocalDateTime orderDateTime;
    private Double totalPrice;
}
