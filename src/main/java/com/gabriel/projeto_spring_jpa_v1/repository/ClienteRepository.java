package com.gabriel.projeto_spring_jpa_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findBySenha(String senha);
    
    Optional<Cliente> findByEmailAndSenha(String email, String senha);
}
