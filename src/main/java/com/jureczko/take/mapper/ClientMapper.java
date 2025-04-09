package com.jureczko.take.mapper;

import com.jureczko.take.dto.client.*;
import com.jureczko.take.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientRequest clientRequest);
    ClientResponse toDto(Client client);
}
