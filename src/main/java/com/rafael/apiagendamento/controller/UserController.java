package com.rafael.apiagendamento.controller;

import com.rafael.apiagendamento.dto.users.CreateUserRequest;
import com.rafael.apiagendamento.dto.users.UpdateUserRequest;
import com.rafael.apiagendamento.dto.users.UserResponse;
import com.rafael.apiagendamento.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UserController implements GenericController{

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid CreateUserRequest createUserRequest){
        UserResponse userResponse = userService.create(createUserRequest);
        URI location = gerarHeadLocation(userResponse.id());
        return ResponseEntity.created(location).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> searchById(@PathVariable String id){
        UserResponse userResponse = userService.searchById(id);
        return ResponseEntity.ok(userResponse);

    }

    @PutMapping
    public ResponseEntity<UserResponse>update(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        UserResponse userResponse = userService.update(updateUserRequest);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable String id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
