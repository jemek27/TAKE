package com.jureczko.take.dto.client;

import com.jureczko.take.controller.ClientController;
import com.jureczko.take.model.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
public class ClientResponse extends RepresentationModel<ClientResponse> {
    public Long id;
    public String name;
    public String lastName;
    public String email;
    public LocalDate birthday;
    public String phoneNumber;

    public ClientResponse(Client client) {
        super();
        this.id = client.getId();
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.birthday = client.getBirthday();
        this.phoneNumber = client.getPhoneNumber();

        this.add(linkTo(methodOn(ClientController.class)
                .getClientOrdersById(client.getId())).withRel("orders"));
    }
}
