package com.jureczko.take.dto.dish;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishReportResponse {
    private String dishName;
    private Long totalOrdered;
}
