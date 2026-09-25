package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.client.UpdateClientRequest;
import com.rafael.apiagendamento.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController implements GenericController {
    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse>create(@RequestBody @Valid CreateClientRequest createClientRequest){
        ClientResponse clientResponse = clientService.create(createClientRequest);
        URI location  = gerarHeadLocation(clientResponse.id());
        return ResponseEntity.created(location).body(clientResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse>searchById(@PathVariable String id){
        ClientResponse clientResponse = clientService.searchById(id);
        return ResponseEntity.ok(clientResponse);
    }
    @PutMapping
    public ResponseEntity<ClientResponse>update(@RequestBody UpdateClientRequest updateClientRequest){
        ClientResponse clientResponse = clientService.update(updateClientRequest);
        return ResponseEntity.ok(clientResponse);
    }
}
