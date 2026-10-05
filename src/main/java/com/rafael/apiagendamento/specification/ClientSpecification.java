package com.rafael.apiagendamento.specification;

import com.rafael.apiagendamento.model.Client;
import com.rafael.apiagendamento.model.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecification {

    public static Specification<Client> nomeClientLike(String nome){
        return ((root, query, cb) -> cb.like(cb.lower(root.get("nome")),"%"+nome.toLowerCase()+"%"));
    }

    public static Specification<Client> telefoneClientEqual(String telefone){
        return ((root, query, cb) -> cb.equal(root.get("telefone"),telefone));

    }

    public static Specification<Client> emailUserLike(String email){
        return ((root, query, cb) ->{
            Join<Client, User> userJoin = root.join("usuario");
            return cb.like(cb.lower(userJoin.get("email")),"%"+email.toLowerCase()+"%");
        } );
    }
}
