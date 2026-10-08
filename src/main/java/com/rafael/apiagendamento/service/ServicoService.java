package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.servico.CreateServicoRequest;
import com.rafael.apiagendamento.dto.servico.ServicoResponse;
import com.rafael.apiagendamento.dto.servico.UpdateServicoRequest;
import com.rafael.apiagendamento.exceptions.DadosNaoEncontradoException;
import com.rafael.apiagendamento.mappers.ServicoMapper;
import com.rafael.apiagendamento.model.Servico;
import com.rafael.apiagendamento.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final ServicoMapper servicoMapper;

    @Transactional
    public ServicoResponse create(CreateServicoRequest createServicoRequest){
        Servico servico = servicoMapper.toEntityCreate(createServicoRequest);
        return servicoMapper.toResponse(servicoRepository.save(servico));
    }

    public ServicoResponse searchById(UUID id){
        Servico servico = servicoRepository.findById(id).orElseThrow(()->new DadosNaoEncontradoException(id));
        return servicoMapper.toResponse(servico);
    }

    @Transactional
    public ServicoResponse update(UpdateServicoRequest updateServicoRequest,UUID id){
        Servico servico = servicoRepository.findById(id).orElseThrow(()-> new DadosNaoEncontradoException(id));
        servicoMapper.EntityUpdate(updateServicoRequest,servico);
        return servicoMapper.toResponse(servico);
    }

    @Transactional
    public void delete(UUID id){
        Servico servico = servicoRepository.findById(id).orElseThrow(()-> new DadosNaoEncontradoException(id));
        servicoRepository.delete(servico);
    }




}
