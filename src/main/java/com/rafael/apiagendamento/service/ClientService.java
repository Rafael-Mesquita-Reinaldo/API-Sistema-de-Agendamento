package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.client.UpdateClientRequest;
import com.rafael.apiagendamento.exceptions.DadosNaoEncontradoException;
import com.rafael.apiagendamento.mappers.ClientMapper;
import com.rafael.apiagendamento.model.Client;
import com.rafael.apiagendamento.model.Role;
import com.rafael.apiagendamento.repository.ClientRepository;
import com.rafael.apiagendamento.repository.UserRepository;
import com.rafael.apiagendamento.specification.ClientSpecification;
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
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UserRepository userRepository;

    @Transactional
    public ClientResponse create(CreateClientRequest createClientRequest){
        Client client = clientMapper.toEntityCreate(createClientRequest);
        client.getUsuario().setRole(Role.CLIENTE);
        client.setUsuario(userRepository.save(client.getUsuario()));
        return clientMapper.toResponse(clientRepository.save(client));
    }

    public ClientResponse searchById(UUID id){
        Client client = clientRepository.findById(id).orElseThrow(()-> new DadosNaoEncontradoException(id));
        return clientMapper.toResponse(client);
    }

    @Transactional
    public ClientResponse update(UpdateClientRequest updateClientRequest){
        if(updateClientRequest.id()==null){
            throw new IllegalArgumentException("Para atualizar, é necessário do ID!");
        }

        Client client = clientRepository.findById(updateClientRequest.id()).orElseThrow(()-> new DadosNaoEncontradoException(updateClientRequest.id()));
        clientMapper.entityUpdate(updateClientRequest,client);
        return clientMapper.toResponse(client);

    }
    @Transactional
    public void delete(UUID id){
        if (!clientRepository.existsById(id)){
            throw new DadosNaoEncontradoException(id);
        }
        clientRepository.deleteById(id);
    }

    public Page<ClientResponse> searchSpecs(String nome, String telefone,String email, Integer numeroPagina,Integer tamanhoPagina){
        Specification<Client> specs = Specification.unrestricted();

        if (nome != null&& !nome.isBlank()){
            specs = specs.and(ClientSpecification.nomeClientLike(nome));
        }
        if (telefone != null&&!telefone.isBlank()){
            specs = specs.and(ClientSpecification.telefoneClientEqual(telefone));
        }
        if (email != null&&!email.isBlank()){
            specs = specs.and(ClientSpecification.emailUserLike(email));
        }
        Pageable page = PageRequest.of(numeroPagina,tamanhoPagina);

        Page<Client> resultado = clientRepository.findAll(specs,page);

        return resultado.map(clientMapper::toResponse);

    }
    
}
