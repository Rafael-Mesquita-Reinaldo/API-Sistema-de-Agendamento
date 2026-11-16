package com.rafael.apiagendamento.security;

import com.rafael.apiagendamento.model.UserModel;
import com.rafael.apiagendamento.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = repository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado."));
        return User.withUsername(userModel.getEmail())
                .password(userModel.getSenha()).authorities("ROLE_"+userModel.getRole().name()).build();
    }
}
