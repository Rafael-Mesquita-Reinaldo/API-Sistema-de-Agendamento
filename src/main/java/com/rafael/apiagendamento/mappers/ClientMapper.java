package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = UserMapper.class)
public interface ClientMapper {

    @Mapping(source = "createUserRequest",target = "usuario")
    Client toEntityCreate(CreateClientRequest createClientRequest);

    @Mapping(source = "usuario",target = "userResponse")
    ClientResponse toDto(Client client);

}
