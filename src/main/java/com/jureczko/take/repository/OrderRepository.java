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

    @Query(value = """
    SELECT 
        CASE 
            WHEN TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()) < 18 THEN '<18'
            WHEN TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()) BETWEEN 18 AND 25 THEN '18-25'
            WHEN TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()) BETWEEN 26 AND 35 THEN '26-35'
            WHEN TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()) BETWEEN 36 AND 50 THEN '36-50'
            ELSE '>50'
        END AS ageGroup,
        COUNT(o.id) AS orderCount
    FROM orders o
    JOIN clients c ON o.client_id = c.id
    WHERE o.order_date_time BETWEEN :startDate AND :endDate
    GROUP BY ageGroup
    ORDER BY ageGroup
    """, nativeQuery = true)
    List<Object[]> getAgeGroupReport(@Param("startDate") LocalDateTime startDate,
                                     @Param("endDate") LocalDateTime endDate);

    @Query("SELECT " +
            "FUNCTION('DAYNAME', o.orderDateTime) AS dayOfWeek, " +
            "COUNT(o) as count " +
            "FROM Order o " +
            "WHERE o.orderDateTime BETWEEN :startDate AND :endDate " +
            "GROUP BY dayOfWeek " +
            "ORDER BY count DESC")
    List<Object[]> getDaySummaryReport(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);

}
