package com.jureczko.take.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClientRequest {
    @NotNull(message = "Client ID is required")
    public String name;
    public String lastName;
    @NotNull(message = "Email is required")
    @Email(message = "A valid email is required")
    public String email;
    public LocalDate birthday;
    @Pattern(
            regexp = "^\\+?[1-9]\\d{1,3}?[-.\\s]?\\(?\\d{1,4}\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$",
            message = "Phone number is incorrect"
    )
    public String phoneNumber;
}

