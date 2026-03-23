package com.gabriel.projeto_spring_jpa_v1.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.repository.OperadorRepository;
@Service
public class OPeradorService {

    @Autowired
    private OperadorRepository repository;

    public Operador validarLogin(String email,String senha){
        return repository.findByEmailAndSenha(email, senha).orElse(null);
    }

    public Optional<Operador> operadorPorId(Long id){
        return repository.findById(id);
    }
}
