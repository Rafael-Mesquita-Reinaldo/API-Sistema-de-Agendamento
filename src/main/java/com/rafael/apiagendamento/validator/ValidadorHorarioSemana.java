package com.rafael.apiagendamento.validator;

import com.rafael.apiagendamento.exceptions.HorarioComercialException;
import com.rafael.apiagendamento.model.Agendamento;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioSemana implements Validador{
    @Override
    public void validar(Agendamento agendamento) {
        if(agendamento.getData().getDayOfWeek() == DayOfWeek.SUNDAY){
            throw new HorarioComercialException("Barbearia fechada aos domingos.");
        }
    }
}
