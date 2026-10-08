package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.users.AdminCreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import com.rafael.apiagendamento.dto.users.UserResponse;
import com.rafael.apiagendamento.exceptions.DadosNaoEncontradoException;
import com.rafael.apiagendamento.mappers.UserMapper;
import com.rafael.apiagendamento.model.User;
import com.rafael.apiagendamento.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse create(AdminCreateUserRequest adminCreateUserRequest){
        User user = userMapper.toEntityAdminCreate(adminCreateUserRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse searchById(UUID id){
         User user = userRepository.findById(id).orElseThrow(()-> new DadosNaoEncontradoException(id));
        return userMapper.toResponse(user);
    }

    @Transactional
    public UserResponse update(UpdateUserRequest updateUserRequest){
        if (updateUserRequest.id()== null){
            throw new IllegalArgumentException("Para atualizar, é necessário do ID!");
        }
        User user =  userRepository.findById(updateUserRequest.id()).orElseThrow(()-> new DadosNaoEncontradoException(updateUserRequest.id()));
        userMapper.EntityUpdate(updateUserRequest,user);
        return userMapper.toResponse(user);
    }

    @Transactional
    public void delete(UUID id){
        if (!userRepository.existsById(id)){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }

}
