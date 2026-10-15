package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.servico.CreateServicoRequest;
import com.rafael.apiagendamento.dto.servico.ServicoResponse;
import com.rafael.apiagendamento.dto.servico.UpdateServicoRequest;
import com.rafael.apiagendamento.model.Servico;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    Servico toEntityCreate(CreateServicoRequest createServicoRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(UpdateServicoRequest updateServicoRequest, @MappingTarget Servico servico);
    ServicoResponse toResponse(Servico servico);

}
