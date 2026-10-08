package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.servico.CreateServicoRequest;
import com.rafael.apiagendamento.dto.servico.ServicoResponse;
import com.rafael.apiagendamento.dto.servico.UpdateServicoRequest;
import com.rafael.apiagendamento.model.Servico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    Servico toEntityCreate(CreateServicoRequest createServicoRequest);
    @Mapping(target = "id", ignore = true)
    void EntityUpdate(UpdateServicoRequest updateServicoRequest, @MappingTarget Servico servico);
    ServicoResponse toResponse(Servico servico);

}
