package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.agendamento.AgendamentoResponse;
import com.rafael.apiagendamento.dto.agendamento.CreateAgendamentoRequest;
import com.rafael.apiagendamento.dto.agendamento.UpdateAgendamentoRequest;
import com.rafael.apiagendamento.exceptions.DadosNaoEncontradoException;
import com.rafael.apiagendamento.exceptions.StatusAgendamentoException;
import com.rafael.apiagendamento.mappers.AgendamentoMapper;
import com.rafael.apiagendamento.model.*;
import com.rafael.apiagendamento.repository.AgendamentoRepository;
import com.rafael.apiagendamento.repository.ClientRepository;
import com.rafael.apiagendamento.repository.ProfissionalRepository;
import com.rafael.apiagendamento.repository.ServicoRepository;
import com.rafael.apiagendamento.specification.AgendamentoSpecification;
import com.rafael.apiagendamento.validator.Validador;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClientRepository clientRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;
    private final AgendamentoMapper agendamentoMapper;
    private final List<Validador> validadores;


    private void atualizarHorarioFinal(Agendamento agendamento){
        agendamento.setHoraFim(agendamento.getHoraInicio().plusMinutes(agendamento.getServico().getDuracaoMinutos()));
    }

    @Transactional
    public AgendamentoResponse create(CreateAgendamentoRequest agendamentoRequest){
        Client cliente = clientRepository.findById(agendamentoRequest.idClient()).orElseThrow(()-> new DadosNaoEncontradoException(agendamentoRequest.idClient()));
        Profissional profissional = profissionalRepository.findById(agendamentoRequest.idProfissional()).orElseThrow(()-> new DadosNaoEncontradoException(agendamentoRequest.idProfissional()));
        Servico servico = servicoRepository.findById(agendamentoRequest.idServico()).orElseThrow(()->new DadosNaoEncontradoException(agendamentoRequest.idServico()));
        Agendamento agendamento = agendamentoMapper.toEntityCreate(agendamentoRequest,cliente,profissional,servico);
        agendamento.setStatus(Status.AGENDADO);
        atualizarHorarioFinal(agendamento);
        for(Validador validador: validadores){
            validador.validar(agendamento);
        }
        return agendamentoMapper.toResponse(agendamentoRepository.save(agendamento));
    }

    @Transactional
    public AgendamentoResponse update(UpdateAgendamentoRequest request, UUID id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new DadosNaoEncontradoException(id));
        if (request.idProfissional()!=null){
            Profissional profissional = profissionalRepository.findById(request.idProfissional()).orElseThrow(() -> new DadosNaoEncontradoException(request.idProfissional()));
            agendamento.setProfissional(profissional);

        }

        if (request.idClient()!=null){
            Client client = clientRepository.findById(request.idClient()).orElseThrow(() -> new DadosNaoEncontradoException(id));
            agendamento.setCliente(client);
        }
        if (request.idServico()!=null){
            Servico servico = servicoRepository.findById(request.idServico()).orElseThrow(()->new DadosNaoEncontradoException(id));
        }
        agendamentoMapper.updateEntity(request,agendamento);
        atualizarHorarioFinal(agendamento);
        for (Validador validador:validadores){
            validador.validar(agendamento);
        }
        return agendamentoMapper.toResponse(agendamento);
    }

    public AgendamentoResponse searchById(UUID id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new DadosNaoEncontradoException(id));
        return agendamentoMapper.toResponse(agendamento);
    }

    public Page<AgendamentoResponse> searchSpecs(UUID idClient, UUID idProfissional, UUID idServico, LocalDate data, LocalTime horaInicio,LocalTime horaFim,Integer numeroPagina,Integer tamanhoPagina){
        Specification<Agendamento> specs = Specification.allOf(AgendamentoSpecification.idClientAgendamentoEqual(idClient),
                AgendamentoSpecification.idProfissionalAgendamentoEqual(idProfissional),
                AgendamentoSpecification.idServicoAgendamentoEqual(idServico),
                AgendamentoSpecification.dataAgendamentoEqual(data),
                AgendamentoSpecification.horaInicioAgendamentoEqual(horaInicio),
                AgendamentoSpecification.horaFimAgendamentoEqual(horaFim));

        Pageable pageable = PageRequest.of(numeroPagina,tamanhoPagina);
        return agendamentoRepository.findAll(specs,pageable).map(agendamentoMapper::toResponse);

    }
    @Transactional
    public void agendamentoConcluido(UUID id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new DadosNaoEncontradoException(id));
        if(agendamento.getStatus()!= Status.AGENDADO){
            throw new StatusAgendamentoException("Somente agendamentos AGENDADOS podem ser concluídos.");
        }
        agendamento.setStatus(Status.CONCLUIDO);
    }
    @Transactional
    public void agendamentoCancelado(UUID id){
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(() -> new DadosNaoEncontradoException(id));
        agendamento.setStatus(Status.CANCELADO);
    }
}
