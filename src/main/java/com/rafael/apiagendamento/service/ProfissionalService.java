package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.profissional.CreateProfissionalRequest;
import com.rafael.apiagendamento.dto.profissional.ProfissionalResponse;
import com.rafael.apiagendamento.dto.profissional.UpdateProfissionalRequest;
import com.rafael.apiagendamento.exceptions.NaoEncontradoException;
import com.rafael.apiagendamento.mappers.ProfissionalMapper;
import com.rafael.apiagendamento.model.Profissional;
import com.rafael.apiagendamento.model.Role;
import com.rafael.apiagendamento.repository.ProfissionalRepository;
import com.rafael.apiagendamento.repository.UserRepository;
import com.rafael.apiagendamento.specification.ProfissionalSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;
    private final ProfissionalMapper profissionalMapper;
    private final UserRepository userRepository;

    @Transactional
    public ProfissionalResponse create(CreateProfissionalRequest createProfissionalRequest){
        Profissional profissional = profissionalMapper.toEntityCreate(createProfissionalRequest);
        profissional.getUsuario().setRole(Role.PROFISSIONAL);
        profissional.setUsuario(userRepository.save(profissional.getUsuario()));
        return profissionalMapper.toResponse(profissionalRepository.save(profissional));
    }


    public ProfissionalResponse searchById(UUID id){
        Profissional profissional = profissionalRepository.findById(id).orElseThrow(() -> new NaoEncontradoException(id));
        return profissionalMapper.toResponse(profissional);
    }

    @Transactional
    public ProfissionalResponse update(UpdateProfissionalRequest updateProfissionalRequest){
        if (updateProfissionalRequest.id()==null){
            throw new NullPointerException("É necessário do id para atualizar.");
        }
        Profissional profissional = profissionalRepository.findById(updateProfissionalRequest.id()).orElseThrow(()-> new NaoEncontradoException(updateProfissionalRequest.id()));
        profissionalMapper.EntityUpdate(updateProfissionalRequest,profissional);
        return profissionalMapper.toResponse(profissional);
    }
    @Transactional
    public void delete(UUID id){
        if (id == null){
            throw new IllegalArgumentException("Para deletar, é necessário do ID!");
        }
        if (!profissionalRepository.existsById(id)){
            throw new RuntimeException("Profissional não encontrado");
        }
        profissionalRepository.deleteById(id);
    }

    public Page<ProfissionalResponse> searchSpecs(String nome, String especialidade, String email, Integer numeroPagina, Integer tamanhoPagina){
        Specification<Profissional> specs = Specification.unrestricted();

        if (nome != null&&!nome.isBlank()){
            specs = specs.and(ProfissionalSpecification.nomeProfissionalLike(nome));
        }
        if(especialidade != null&&especialidade.isBlank()){
            specs = specs.and(ProfissionalSpecification.especialidadeProfissionalLike(especialidade));
        }
        if (email!=null&&email.isBlank()){
            specs = specs.and(ProfissionalSpecification.emailUserLike(email));
        }

        Pageable page = PageRequest.of(numeroPagina,tamanhoPagina);
        Page<Profissional> resultadoPesquisa= profissionalRepository.findAll(specs,page);
        return resultadoPesquisa.map(profissionalMapper::toResponse);
    }
}
