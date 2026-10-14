package com.rafael.apiagendamento.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "agendamentos",schema = "agendamento")
@NoArgsConstructor
@Getter
@Setter
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id",nullable = false)
    private Client cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profissional_id",nullable = false)
    private Profissional profissional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id",nullable = false)
    private Servico servico;

    @Column(nullable = false)
    private LocalDate data;

    @Column(name = "hora_inicio",nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim",nullable = false)
    private LocalTime horaFim;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public Agendamento(Client cliente, Profissional profissional, Servico servico, LocalDate data, LocalTime horaInicio, LocalTime horafim, Status status) {
        this.cliente = cliente;
        this.profissional = profissional;
        this.servico = servico;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horafim;
        this.status = status;
    }
}
