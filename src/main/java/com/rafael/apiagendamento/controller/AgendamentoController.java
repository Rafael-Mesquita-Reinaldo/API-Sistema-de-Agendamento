package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.agendamento.AgendamentoResponse;
import com.rafael.apiagendamento.dto.agendamento.CreateAgendamentoRequest;
import com.rafael.apiagendamento.dto.agendamento.UpdateAgendamentoRequest;
import com.rafael.apiagendamento.service.AgendamentoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/agendamentos")
public class AgendamentoController implements GenericController {

    private final AgendamentoService service;


    @PostMapping
    public ResponseEntity<AgendamentoResponse> create(@RequestBody @Valid CreateAgendamentoRequest request){
        AgendamentoResponse response = service.create(request);
        URI location = gerarHeadLocation(response.id());
        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AgendamentoResponse>update(@RequestBody @Valid UpdateAgendamentoRequest request, @PathVariable UUID id){
        AgendamentoResponse response = service.update(request, id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> searchById(@PathVariable UUID id){
        AgendamentoResponse response = service.searchById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PagedModel<AgendamentoResponse>> searchSpecs(@RequestParam(name = "id_client",required = false)UUID idClient,
                                                                       @RequestParam(name = "id_profissional",required = false)UUID idProfissional,
                                                                       @RequestParam(name = "id_servico",required = false)UUID idServico,
                                                                       @RequestParam(name = "data",required = false) LocalDate data,
                                                                       @RequestParam(name = "hora_inicio",required = false)LocalTime horaInicio,
                                                                       @RequestParam(name = "hora_fim",required = false) LocalTime horaFim,
                                                                       @RequestParam(name = "numero_pagina",defaultValue = "0")@Min(value = 1,message = "O número da página não pode ser negativo.") Integer numeroPagina,
                                                                       @RequestParam(name = "tamanho_pagina",defaultValue = "5")@Min(value = 1,message = "O tamanho da página deve ser no mínimo 1.")@Max(value = 100,message = "O tamanho da página dever ser no máximo 100.") Integer tamanhoPagina){
        Page<AgendamentoResponse> resultadoPesquisa = service.searchSpecs(idClient,idProfissional,idServico,data,horaInicio,horaFim,numeroPagina,tamanhoPagina);
        return ResponseEntity.ok(new PagedModel<>(resultadoPesquisa));
    }
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Void> agendamentoConcluido(@PathVariable UUID id){
        service.agendamentoConcluido(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> agendamentoCancelado(@PathVariable UUID id){
        service.agendamentoCancelado(id);
        return ResponseEntity.noContent().build();
    }





}
