package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.profissional.CreateProfissionalRequest;
import com.rafael.apiagendamento.dto.profissional.ProfissionalResponse;
import com.rafael.apiagendamento.dto.profissional.UpdateProfissionalRequest;
import com.rafael.apiagendamento.service.ProfissionalService;
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
import java.util.UUID;

@Validated
@RestController
@RequestMapping("api/profissionais")
@RequiredArgsConstructor
public class ProfissionalController implements GenericController{

    private final ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<ProfissionalResponse> create(@RequestBody @Valid CreateProfissionalRequest createProfissionalRequest){
        ProfissionalResponse profissionalResponse = profissionalService.create(createProfissionalRequest);
        URI location = gerarHeadLocation(profissionalResponse.id());
        return ResponseEntity.created(location).body(profissionalResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalResponse> searchById(@PathVariable UUID id){
        ProfissionalResponse profissionalResponse = profissionalService.searchById(id);
        return ResponseEntity.ok(profissionalResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfissionalResponse> update(@RequestBody @Valid UpdateProfissionalRequest updateProfissionalRequest,@PathVariable UUID id){
        ProfissionalResponse profissionalResponse = profissionalService.update(updateProfissionalRequest,id);
        return ResponseEntity.ok(profissionalResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        profissionalService.delete(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping
    public ResponseEntity<PagedModel<ProfissionalResponse>> searchSpecs(@RequestParam(value = "nome",required = false)String nome,
                                                                        @RequestParam(value = "especialidade",required = false)String especialidade,
                                                                        @RequestParam(value = "email",required = false)String email,
                                                                        @RequestParam(value = "numero-pagina",defaultValue = "0")@Min(value = 0,message = "O número da página não pode ser negativo.") Integer numeroPagina,
                                                                        @RequestParam(value = "tamanho-pagina",defaultValue = "5") @Min(value = 1,message = "O tamanho da página deve ser no mínimo 1.")@Max(value = 100,message = "O tamanho da página dever ser no máximo 100.")Integer tamanhoPagina){
        Page<ProfissionalResponse> resultadoPesquisa = profissionalService.searchSpecs(nome,especialidade,email,numeroPagina,tamanhoPagina);
        return ResponseEntity.ok(new PagedModel<>(resultadoPesquisa));
    }
}
