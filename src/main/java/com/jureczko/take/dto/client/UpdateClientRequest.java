package com.jureczko.take.dto.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientRequest {
    private String name;
    private String lastName;

    @Email(message = "A valid email is required")
    private String email;

    private LocalDate birthday;
    @Pattern(
            regexp = "^\\+?[1-9]\\d{1,3}?[-.\\s]?\\(?\\d{1,4}\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$",
            message = "Phone number is incorrect"
    )
    private String phoneNumber;
}
