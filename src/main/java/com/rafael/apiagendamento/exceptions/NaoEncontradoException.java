package com.rafael.apiagendamento.exceptions;

import java.util.UUID;

public class NaoEncontradoException extends RuntimeException{
    public NaoEncontradoException(UUID id){
        super("Dados não encontrado: "+ id);
    }
}
