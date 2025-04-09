package com.jureczko.take.mapper;

import com.jureczko.take.dto.OrderDishRequest;
import com.jureczko.take.dto.order.*;
import com.jureczko.take.model.Client;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Order;
import com.jureczko.take.model.OrderDish;
import com.jureczko.take.repository.ClientRepository;
import com.jureczko.take.repository.DishRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapperImpl implements OrderMapper {

    private final ClientRepository clientRepository;
    private final DishRepository dishRepository;

    public OrderMapperImpl(ClientRepository clientRepository, DishRepository dishRepository) {
        this.clientRepository = clientRepository;
        this.dishRepository = dishRepository;
    }

    @Override
    public Order toEntity(OrderRequest orderRequest) {
        Order order = new Order();

        Client client = clientRepository.findById(orderRequest.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found with id " + orderRequest.getClientId()));
        order.setClient(client);


        order.setOrderDateTime(orderRequest.getOrderDateTime());
        //todo sum up duplicates
        //  {
        //      "dishId": 1,
        //      "quantity": 3
        //  },
        //  {
        //      "dishId": 1,
        //      "quantity": 3
        //  }
        // INTO
        //  {
        //      "dishId": 1,
        //      "quantity": 6
        //  }
        double totalPrice = 0.0;
        List<OrderDish> orderDishes = new ArrayList<>();
        for (OrderDishRequest dishRequest : orderRequest.getDishes()) {
            Dish dish = dishRepository.findById(dishRequest.getDishId())
                    .orElseThrow(() -> new RuntimeException("Dish not found with id " + dishRequest.getDishId()));

            OrderDish orderDish = new OrderDish();
            orderDish.setDish(dish);
            orderDish.setQuantity(dishRequest.getQuantity());
            orderDish.setOrder(order);

            totalPrice += dish.getPrice() * dishRequest.getQuantity();
            orderDishes.add(orderDish);
        }

        order.setTotalPrice(totalPrice);
        order.setOrderDish(orderDishes);

        return order;
    }

    @Override
    public OrderResponse toDto(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setClientId(order.getClient().getId());
        orderResponse.setOrderDateTime(order.getOrderDateTime());
        orderResponse.setTotalPrice(order.getTotalPrice());
        orderResponse.setDishes(order.getOrderDish().stream().map(OrderDishRequest::new).collect(Collectors.toList()));
        return orderResponse;
    }
}
