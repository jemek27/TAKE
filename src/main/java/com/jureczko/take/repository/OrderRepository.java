package com.jureczko.take.repository;

import com.jureczko.take.model.Order;
import com.jureczko.take.dto.order.OrderSummaryReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getByClientId(Long id);

    @Query("SELECT new com.jureczko.take.dto.order.OrderSummaryReport(COUNT(o), SUM(o.totalPrice)) " +
            "FROM Order o " +
            "WHERE o.orderDateTime BETWEEN :startDate AND :endDate")
    OrderSummaryReport getOrderSummaryReport(@Param("startDate") LocalDateTime startDate,
                                             @Param("endDate") LocalDateTime endDate);
}
