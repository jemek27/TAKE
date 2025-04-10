package com.jureczko.take.service;


import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.model.Order;
import com.jureczko.take.model.Client;
import com.jureczko.take.model.OrderDish;
import com.jureczko.take.repository.ClientRepository;
import com.jureczko.take.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order with ID " + id + " not found"));
    }

    @Transactional
    public Order saveOrder(Order order) {
        Client client = clientRepository.findById(order.getClient().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Order with clientID " + order.getClient().getId() + " not found"));

        order.setClient(client);

        if (order.getOrderDish() != null && !order.getOrderDish().isEmpty()) {
            for (OrderDish orderDish : order.getOrderDish()) {
                orderDish.setOrder(order);
            }
        }

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    public boolean existsById(Long id) {
        return orderRepository.existsById(id);
    }
}
