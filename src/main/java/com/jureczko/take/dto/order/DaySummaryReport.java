package com.jureczko.take.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DaySummaryReport {
    private String dayOfWeek;
    private Long orderCount;
}