package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.client.UpdateClientRequest;
import com.rafael.apiagendamento.mappers.ClientMapper;
import com.rafael.apiagendamento.model.Client;
import com.rafael.apiagendamento.repository.ClientRepository;
import com.rafael.apiagendamento.repository.UserRepository;
import com.rafael.apiagendamento.specification.ClientSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final UserRepository userRepository;

    public ClientResponse create(CreateClientRequest createClientRequest){

        Client client = clientMapper.toEntityCreate(createClientRequest);
        client.setUsuario(userRepository.save(client.getUsuario()));
        ClientResponse clientResponse = clientMapper.toResponse(clientRepository.save(client));
        return clientResponse;
    }

    public ClientResponse searchById(String id){
        UUID uuid = UUID.fromString(id);
        Optional<Client> optionalClient = clientRepository.findById(uuid);
        if (!optionalClient.isPresent()){
            throw new RuntimeException("Cliente não encontrado");
        }
        Client client = optionalClient.get();
        return clientMapper.toResponse(client);
    }

    public ClientResponse update(UpdateClientRequest updateClientRequest){
        if(updateClientRequest.id()==null){
            throw new IllegalArgumentException("Para atualizar, é necessário do ID!");
        }
        if (!clientRepository.existsById(updateClientRequest.id())){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Client client = clientMapper.toEntityUpdate(updateClientRequest);
        return clientMapper.toResponse(clientRepository.save(client));

    }

    public void delete(String id){
        UUID uuid = UUID.fromString(id);
        if (!clientRepository.existsById(uuid)){
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        clientRepository.deleteById(uuid);
    }

    public Page<ClientResponse> searchSpecs(String nome, String telefone,String email, Integer numeroPagina,Integer tamanhoPagina){
        Specification specs = Specification.unrestricted();

        if (nome != null){
            specs = specs.and(ClientSpecification.nomeClientLike(nome));
        }
        if (telefone != null){
            specs = specs.and(ClientSpecification.telefoneClientEqual(telefone));
        }
        if (email != null){
            specs = specs.and(ClientSpecification.emailUserLike(email));
        }
        Pageable page = PageRequest.of(numeroPagina,tamanhoPagina);

        Page<Client> resultado = clientRepository.findAll(specs,page);

        return resultado.map(clientMapper::toResponse);

    }
    
}
