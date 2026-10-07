package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.profissional.CreateProfissionalRequest;
import com.rafael.apiagendamento.dto.profissional.ProfissionalResponse;
import com.rafael.apiagendamento.dto.profissional.UpdateProfissionalRequest;
import com.rafael.apiagendamento.model.Profissional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = UserMapper.class)
public interface ProfissionalMapper {

    @Mapping(source = "createUserRequest",target = "usuario")
    Profissional toEntityCreate(CreateProfissionalRequest createProfissionalRequest);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "usuario",ignore = true)
    void EntityUpdate(UpdateProfissionalRequest updateProfissionalRequest, @MappingTarget Profissional profissional);

    @Mapping(source = "usuario",target = "userResponse")
    ProfissionalResponse toResponse(Profissional profissional);

}
