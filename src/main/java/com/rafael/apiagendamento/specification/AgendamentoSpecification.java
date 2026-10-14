package com.rafael.apiagendamento.specification;

import com.rafael.apiagendamento.model.Agendamento;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class AgendamentoSpecification {

    public static Specification<Agendamento>idClientAgendamentoEqual(UUID idClient){
        if (idClient==null){
            return Specification.unrestricted();
        }
        return (root, query, cb) -> cb.equal(root.get("cliente").get("id"),idClient);
    }
    public static Specification<Agendamento>idProfissionalAgendamentoEqual(UUID idProfissional){
        if (idProfissional==null){
            return Specification.unrestricted();
        }
        return (root, query, cb) -> cb.equal(root.get("profissional").get("id"),idProfissional);
    }
    public static Specification<Agendamento>idServicoAgendamentoEqual(UUID idServico){
        if (idServico==null){
            return Specification.unrestricted();
        }
        return (root, query, cb) -> cb.equal(root.get("servico").get("id"),idServico);
    }
    public static Specification<Agendamento>dataAgendamentoEqual(LocalDate data){
        if (data==null){
            return Specification.unrestricted();
        }
        return (root, query, cb) -> cb.equal(root.get("data"),data);
    }

    public static Specification<Agendamento>horaInicioAgendamentoEqual(LocalTime horaInicio){
        if (horaInicio==null){
            return Specification.unrestricted();
        }
        return (root, query, cb) -> cb.equal(root.get("horaInicio"),horaInicio);
    }
    public static Specification<Agendamento>horaFimAgendamentoEqual(LocalTime horaFim){
        if (horaFim==null){
            return Specification.unrestricted();
        }
        return (root, query, cb) -> cb.equal(root.get("horaFim"),horaFim);
    }




}
