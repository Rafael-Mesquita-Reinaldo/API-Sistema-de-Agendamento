package com.rafael.apiagendamento.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "clientes",schema = "agendamento")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 20)
    private String telefone;

    @OneToOne
    @JoinColumn(name = "usuario_id",unique = true,nullable = true)
    private User usuario;

    public Client(String nome, String telefone, User usuario) {
        this.nome = nome;
        this.telefone = telefone;
        this.usuario = usuario;
    }

    public Client(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }
}
