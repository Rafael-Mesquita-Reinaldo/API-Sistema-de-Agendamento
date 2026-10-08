package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.servico.CreateServicoRequest;
import com.rafael.apiagendamento.dto.servico.ServicoResponse;
import com.rafael.apiagendamento.dto.servico.UpdateServicoRequest;
import com.rafael.apiagendamento.service.ServicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/servicos")
@RequiredArgsConstructor
public class ServicoController implements GenericController{
    private final ServicoService servicoService;

    @PostMapping
    public ResponseEntity<ServicoResponse> create(@RequestBody @Valid CreateServicoRequest createServicoRequest){
        ServicoResponse servicoResponse = servicoService.create(createServicoRequest);
        URI location = gerarHeadLocation(servicoResponse.id());
        return ResponseEntity.created(location).body(servicoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse> searchById(@PathVariable UUID id){
        ServicoResponse servicoResponse = servicoService.searchById(id);
        return ResponseEntity.ok(servicoResponse);

    }
    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponse> update(@RequestBody UpdateServicoRequest updateServicoRequest,@PathVariable UUID id){
        ServicoResponse servicoResponse = servicoService.update(updateServicoRequest,id);
        return ResponseEntity.ok(servicoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        servicoService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
