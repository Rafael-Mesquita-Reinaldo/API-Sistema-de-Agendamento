package com.rafael.apiagendamento.mappers;

import com.rafael.apiagendamento.dto.users.AdminCreateUserRequest;
import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import com.rafael.apiagendamento.dto.users.UserResponse;
import com.rafael.apiagendamento.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntityCreate(CreateUserRequest createUserRequest);
    User toEntityAdminCreate(AdminCreateUserRequest adminCreateUserRequest);

    @Mapping(target = "id", ignore = true)
    void EntityUpdate(UpdateUserRequest updateUserRequest, @MappingTarget User user);
    UserResponse toResponse(User usuario);
}
