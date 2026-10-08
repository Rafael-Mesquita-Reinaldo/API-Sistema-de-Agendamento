package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.users.AdminCreateUserRequest;
import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import com.rafael.apiagendamento.dto.users.UserResponse;
import com.rafael.apiagendamento.model.Role;
import com.rafael.apiagendamento.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController implements GenericController{

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid AdminCreateUserRequest adminCreateUserRequest){
        UserResponse userResponse = userService.create(adminCreateUserRequest);
        URI location = gerarHeadLocation(userResponse.id());
        return ResponseEntity.created(location).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> searchById(@PathVariable UUID id){
        UserResponse userResponse = userService.searchById(id);
        return ResponseEntity.ok(userResponse);

    }

    @PutMapping
    public ResponseEntity<UserResponse>update(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        UserResponse userResponse = userService.update(updateUserRequest);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable UUID id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
