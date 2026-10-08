package com.rafael.apiagendamento.exceptions;

public class TempoNegativoException extends RuntimeException {
    public TempoNegativoException(String message) {
        super(message);
    }
}
