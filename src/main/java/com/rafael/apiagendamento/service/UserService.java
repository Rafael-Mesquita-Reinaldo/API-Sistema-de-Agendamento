package com.rafael.apiagendamento.service;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import com.rafael.apiagendamento.dto.users.UserResponse;
import com.rafael.apiagendamento.mappers.UserMapper;
import com.rafael.apiagendamento.model.User;
import com.rafael.apiagendamento.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserResponse create(CreateUserRequest createUserRequest){
        User user = userMapper.toEntityCreate(createUserRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse searchById(String id){
        UUID uuid = UUID.fromString(id);
        Optional<User> userOptional = userRepository.findById(uuid);
        if (!userOptional.isPresent()){
            throw new RuntimeException("Usuário não encontrado");
        }
        return userMapper.toResponse(userOptional.get());
    }

    public UserResponse update(UpdateUserRequest updateUserRequest){
        if (updateUserRequest.id()== null){
            throw new IllegalArgumentException("Para atualizar, é necessário do ID!");
        }
        if (!userRepository.existsById(updateUserRequest.id())){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        User user =  userMapper.toEntityUpdate(updateUserRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    public void delete(String id){
        UUID uuid = UUID.fromString(id);
        if (!userRepository.existsById(uuid)){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        userRepository.deleteById(uuid);
    }

}
