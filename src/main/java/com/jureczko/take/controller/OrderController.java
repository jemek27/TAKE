package com.jureczko.take.controller;

import com.jureczko.take.model.Order;
import com.jureczko.take.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) { this.orderService = orderService; }

    @GetMapping
    public List<Order> getAllClients() { return orderService.findAll(); }

    @GetMapping("/{id}")
    public Order get(@PathVariable Long id) { return orderService.findById(id); }

    @PostMapping
    public Order createClient(@RequestBody Order order) { return orderService.save(order); }

    @PutMapping("/{id}")
    public Order update(@PathVariable Long id, @RequestBody Order o) {
        o.setId(id);
        return orderService.save(o);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { orderService.delete(id); }
}

