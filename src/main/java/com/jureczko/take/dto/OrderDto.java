package com.jureczko.take.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class OrderDto {
    public Long id;
    public Long clientId;
    public LocalDate orderDate;
    public Double totalPrice;
}
