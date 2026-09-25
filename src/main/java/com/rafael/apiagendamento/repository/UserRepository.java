package com.rafael.apiagendamento.repository;

import com.rafael.apiagendamento.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
