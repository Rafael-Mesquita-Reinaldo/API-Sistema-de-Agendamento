package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.client.ClientResponse;
import com.rafael.apiagendamento.dto.client.CreateClientRequest;
import com.rafael.apiagendamento.dto.client.UpdateClientRequest;
import com.rafael.apiagendamento.service.ClientService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;
@Validated
@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
public class ClientController implements GenericController {
    private final ClientService clientService;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<ClientResponse>create(@RequestBody @Valid CreateClientRequest createClientRequest){
        ClientResponse clientResponse = clientService.create(createClientRequest);
        URI location  = gerarHeadLocation(clientResponse.id());
        return ResponseEntity.created(location).body(clientResponse);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse>searchById(@PathVariable UUID id,@AuthenticationPrincipal UserDetails usuarioLogado){
        ClientResponse clientResponse = clientService.searchById(id,usuarioLogado);
        return ResponseEntity.ok(clientResponse);
    }

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponse>update(@RequestBody @Valid UpdateClientRequest updateClientRequest, @PathVariable UUID id, @AuthenticationPrincipal UserDetails usuarioLogado){
        ClientResponse clientResponse = clientService.update(updateClientRequest,id,usuarioLogado);
        return ResponseEntity.ok(clientResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('PROFISSIONAIS','ADMIN')")
    @GetMapping
    public ResponseEntity<PagedModel<ClientResponse>> searchSpecs(@RequestParam(value = "nome",required = false) String nome,
                                                                  @RequestParam(value = "telefone",required = false)String telefone,
                                                                  @RequestParam(value = "email",required = false)String email,
                                                                  @RequestParam(value = "numero-pagina",defaultValue = "0") @Min(value = 0,message = "O número da página não pode ser negativo.") Integer numeroPagina,
                                                                  @RequestParam(value = "tamanho-pagina",defaultValue = "5") @Min(value = 1,message = "O tamanho da página deve ser no mínimo 1.")@Max(value = 100,message = "O tamanho da página dever ser no máximo 100.") Integer tamanhoPagina){
        Page<ClientResponse> resultadoPesquisa = clientService.searchSpecs(nome,telefone,email,numeroPagina,tamanhoPagina);
        return ResponseEntity.ok(new PagedModel<>(resultadoPesquisa));
    }


}
