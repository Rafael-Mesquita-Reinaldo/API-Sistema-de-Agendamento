package com.rafael.apiagendamento.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "servicos",schema = "agendamento")
@Getter
@Setter
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "duracao_minutos",nullable = false)
    private Integer duracaoMinutos;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal preco;

    public Servico(String descricao, int duracaoMinutos, BigDecimal preco) {
        this.descricao = descricao;
        this.duracaoMinutos = duracaoMinutos;
        this.preco = preco;
    }
}
