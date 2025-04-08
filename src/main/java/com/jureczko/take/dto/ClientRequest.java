package com.jureczko.take.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClientRequest {
    public String name;
    public String lastName;
    public String email;
    public LocalDate birthday;
    public String phoneNumber;
}

