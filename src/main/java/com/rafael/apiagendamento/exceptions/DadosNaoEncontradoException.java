package com.rafael.apiagendamento.exceptions;

import java.util.UUID;

public class DadosNaoEncontradoException extends RuntimeException{
    public DadosNaoEncontradoException(UUID id){
        super("Dados não encontrado: "+ id);
    }
}
