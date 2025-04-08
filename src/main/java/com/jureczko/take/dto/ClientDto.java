package com.jureczko.take.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClientDto {
    public Long id;
    public String name;
    public String lastName;
    public String email;
    public LocalDate birthday;
    public String phoneNumber;
}
