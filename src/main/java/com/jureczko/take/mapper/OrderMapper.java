package com.jureczko.take.mapper;

import com.jureczko.take.dto.order.*;
import com.jureczko.take.model.Order;

//@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(OrderRequest orderRequest);
    OrderResponse toDto(Order order);
}
