package com.rafael.apiagendamento.specification;

import com.rafael.apiagendamento.model.Profissional;
import com.rafael.apiagendamento.model.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProfissionalSpecification {

    public static Specification<Profissional> nomeProfissionalLike(String nome){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("nome")),"%"+nome.toLowerCase()+"%" ) );
    }
    public static Specification<Profissional> especialidadeProfissionalLike(String especialidade){
        return (root, query, cb) -> cb.like(cb.lower(root.get("especialidade")),"%"+especialidade.toLowerCase()+"%" );
    }
    public static Specification<Profissional> emailUserLike(String email){
        return (root, query, cb) ->{
            Join<Profissional, User> userJoin = root.join("usuario");
            return cb.like(cb.lower(userJoin.get("email")),"%"+ email.toLowerCase()+"%");

        };
    }

}
