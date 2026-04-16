package com.gabriel.projeto_spring_jpa_v1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findById(Long id);

    Optional<Cliente> findBySenha(String senha);

    // Containing → busca por parte do nome (não precisa ser igual)
    // IgnoreCase → ignora maiúsculas e minúsculas
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    Optional<Cliente> findByEmailAndSenha(String email, String senha);

    Long countByOperadorId(Long operadorId);
}
