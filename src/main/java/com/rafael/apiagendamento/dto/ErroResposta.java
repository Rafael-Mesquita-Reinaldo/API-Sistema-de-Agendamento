package com.rafael.apiagendamento.dto;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {
}
