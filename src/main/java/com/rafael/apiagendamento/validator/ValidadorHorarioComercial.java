package com.rafael.apiagendamento.validator;

import com.rafael.apiagendamento.exceptions.HorarioComercialException;
import com.rafael.apiagendamento.model.Agendamento;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ValidadorHorarioComercial implements Validador{

    private final LocalTime horarioComercialInicio = LocalTime.of(8,0);
    private final LocalTime horarioComercialFim = LocalTime.of(18,0);

    @Override
    public void validar(Agendamento agendamento) {
        if (agendamento.getHoraInicio().isBefore(horarioComercialInicio) ||agendamento.getHoraInicio().isAfter(horarioComercialFim)|| agendamento.getHoraFim().isAfter(horarioComercialFim) ){
            throw new HorarioComercialException("O agendamento está fora do horário comecial.");
        }
    }
}
