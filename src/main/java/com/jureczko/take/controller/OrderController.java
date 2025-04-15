package com.jureczko.take.controller;

import com.jureczko.take.dto.dish.DishResponse;
import com.jureczko.take.dto.order.*;
import com.jureczko.take.exception.ResourceNotFoundException;
import com.jureczko.take.mapper.OrderMapper;
import com.jureczko.take.model.Dish;
import com.jureczko.take.model.Order;
import com.jureczko.take.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Order> pagedResourcesAssembler;

    @GetMapping
    @Operation(summary = "Get a paginated list of orders")
    public ResponseEntity<PagedModel<OrderResponse>> getAllOrders(
            @PageableDefault(size = 10, sort = "orderDateTime", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(
                pagedResourcesAssembler.toModel(
                        orderService.getAllOrders(pageable),
                        orderMapper::toDto
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid  @RequestBody OrderRequest orderRequest) {
        Order order = orderMapper.toEntity(orderRequest);
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toDto(savedOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@Valid @PathVariable Long id, @RequestBody OrderRequest orderRequest) {
        if (!orderService.existsById(id)) {
            throw new ResourceNotFoundException("Order with ID " + id + " not found");
        }
        Order order = orderMapper.toEntity(orderRequest);
        order.setId(id);
        Order updatedOrder = orderService.saveOrder(order);
        return ResponseEntity.ok(orderMapper.toDto(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public OrderSummaryReport getOrderSummary(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        return orderService.getOrderSummary(startDate, endDate);
    }
}

