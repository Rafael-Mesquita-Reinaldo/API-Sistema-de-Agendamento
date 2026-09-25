package com.rafael.apiagendamento.repository;

import com.rafael.apiagendamento.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
