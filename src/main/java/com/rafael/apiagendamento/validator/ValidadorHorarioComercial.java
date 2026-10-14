package com.rafael.apiagendamento.validator;

import com.rafael.apiagendamento.exceptions.HorarioComercialException;
import com.rafael.apiagendamento.model.Agendamento;

import java.time.LocalTime;

public class ValidadorHorarioComercial implements Validador{

    private final LocalTime horarioComercialInicio = LocalTime.of(8,0);
    private final LocalTime horarioComercialFim = LocalTime.of(18,0);

    @Override
    public void validar(Agendamento agendamento) {
        if (agendamento.getHoraInicio().isBefore(horarioComercialInicio) || agendamento.getHoraFim().isAfter(horarioComercialFim) ){
            throw new HorarioComercialException("O agendamento está fora do horário Comecial.");
        }
    }
}
