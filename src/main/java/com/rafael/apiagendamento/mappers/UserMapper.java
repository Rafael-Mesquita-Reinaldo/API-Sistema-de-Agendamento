package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import com.rafael.apiagendamento.dto.users.UserResponse;
import com.rafael.apiagendamento.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntityCreate(CreateUserRequest createUserRequest);
    User toEntityUpdate(UpdateUserRequest updateUserRequest);
    UserResponse toResponse(User usuario);
}
