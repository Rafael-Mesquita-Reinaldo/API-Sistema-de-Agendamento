package com.rafael.apiagendamento.validator;

import com.rafael.apiagendamento.exceptions.ConflitoException;
import com.rafael.apiagendamento.model.Agendamento;
import com.rafael.apiagendamento.model.Status;
import com.rafael.apiagendamento.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ValidadorConflitoHorario implements Validador{

    private final AgendamentoRepository agendamentoRepository;

    @Override
    public void validar(Agendamento agendamentoNovo) {
        List<Agendamento> agendamentosExistentes;
        if(agendamentoNovo.getId()==null){
            agendamentosExistentes = agendamentoRepository.findByProfissionalAndDataAndStatus(agendamentoNovo.getProfissional(), agendamentoNovo.getData(), Status.AGENDADO);
        }else{

            agendamentosExistentes = agendamentoRepository.findByProfissionalAndDataAndStatusAndIdNot(agendamentoNovo.getProfissional(),agendamentoNovo.getData(),Status.AGENDADO,agendamentoNovo.getId());
        }

        boolean conflito = agendamentosExistentes.stream().anyMatch(agendamentoExistente ->
             agendamentoNovo.getHoraInicio().isBefore(agendamentoExistente.getHoraFim()) && agendamentoNovo.getHoraFim().isAfter(agendamentoExistente.getHoraInicio()));
        if (conflito){
            throw new ConflitoException("Horário indisponível");
        }


    }
}
