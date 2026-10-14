package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.agendamento.AgendamentoResponse;
import com.rafael.apiagendamento.dto.agendamento.CreateAgendamentoRequest;
import com.rafael.apiagendamento.dto.agendamento.UpdateAgendamentoRequest;
import com.rafael.apiagendamento.model.Agendamento;
import com.rafael.apiagendamento.model.Client;
import com.rafael.apiagendamento.model.Profissional;
import com.rafael.apiagendamento.model.Servico;
import org.mapstruct.*;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring",uses = {ClientMapper.class, ProfissionalMapper.class, ServicoMapper.class})
public interface AgendamentoMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "status",ignore = true)
    @Mapping(target = "horaFim",ignore = true)
    Agendamento toEntityCreate(CreateAgendamentoRequest agendamentoRequest, Client cliente, Profissional profissional, Servico servico);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "profissional",ignore = true)
    @Mapping(target = "cliente",ignore = true)
    @Mapping(target = "servico",ignore = true)
    void updateEntity(UpdateAgendamentoRequest request,@MappingTarget Agendamento agendamento);
    AgendamentoResponse toResponse(Agendamento agendamento);

}
