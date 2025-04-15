package com.jureczko.take.repository;

import com.jureczko.take.dto.dish.DishReportResponse;
import com.jureczko.take.model.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface  OrderDishRepository extends JpaRepository<OrderDish, Long> {
    @Query("SELECT new com.jureczko.take.dto.dish.DishReportResponse(od.dish.name, SUM(od.quantity)) " +
            "FROM OrderDish od " +
            "WHERE od.order.orderDateTime BETWEEN :startDate AND :endDate " +
            "GROUP BY od.dish.name " +
            "ORDER BY SUM(od.quantity) DESC")
    List<DishReportResponse> getDishReport(@Param("startDate") LocalDateTime startDate,
                                           @Param("endDate") LocalDateTime endDate);
}