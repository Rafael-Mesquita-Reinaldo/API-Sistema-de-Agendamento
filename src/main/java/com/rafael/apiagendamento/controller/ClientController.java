package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.client.UpdateClientRequest;
import com.rafael.apiagendamento.model.Client;
import com.rafael.apiagendamento.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<PagedModel<ClientResponse>> searchSpecs(@RequestParam(value = "nome",required = false) String nome,
                                                          @RequestParam(value = "telefone",required = false)String telefone,
                                                          @RequestParam(value = "email",required = false)String email,
                                                          @RequestParam(value = "numero-pagina",defaultValue = "0")Integer numeroPagina,
                                                          @RequestParam(value = "tamanho-pagina",defaultValue = "5") Integer tamanhoPagina){
        Page<ClientResponse> resultadoPesquisa = clientService.searchSpecs(nome,telefone,email,numeroPagina,tamanhoPagina);
        if (resultadoPesquisa.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(new PagedModel<>(resultadoPesquisa));
    }


}
