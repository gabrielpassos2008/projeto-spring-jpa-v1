package com.gabriel.projeto_spring_jpa_v1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.projeto_spring_jpa_v1.model.Operador;

public interface OperadorRepository extends JpaRepository<Operador, Long>{
    Optional<Operador> findByEmail(String email);

    Optional<Operador> findByEmailAndSenha(String email, String senha);
}
