package com.jureczko.take.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderSummaryReport {
    private Long orderCount;
    private Double totalRevenue;
}
