package com.jureczko.take.service;

import com.jureczko.take.model.Order;
import com.jureczko.take.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;
    public List<Order> findAll() { return orderRepository.findAll(); }
    public Order findById(Long id) { return orderRepository.findById(id).orElseThrow(); }
    public Order save(Order o) { return orderRepository.save(o); }
    public void delete(Long id) { orderRepository.deleteById(id); }
}
