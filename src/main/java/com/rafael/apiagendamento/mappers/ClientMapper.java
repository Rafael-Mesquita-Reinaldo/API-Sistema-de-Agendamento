package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.client.UpdateClientRequest;
import com.rafael.apiagendamento.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = UserMapper.class)
public interface ClientMapper {

    @Mapping(source = "createUserRequest",target = "usuario")
    Client toEntityCreate(CreateClientRequest createClientRequest);

    @Mapping(target = "usuario",ignore = true)
    @Mapping(target = "id",ignore = true)
    void entityUpdate(UpdateClientRequest updateClientRequest, @MappingTarget Client client);

    @Mapping(source = "usuario",target = "userResponse")
    ClientResponse toResponse(Client client);

}
