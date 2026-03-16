package com.gabriel.projeto_spring_jpa_v1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.repository.OperadorReposytory;
@Service
public class OPeradorService {

    @Autowired
    private OperadorReposytory repository;

    public boolean validarLogin(String email,String senha){
        Optional<Operador> clienteEncomtrado = repository.findByEmailAndSenha(email, senha);
        if(clienteEncomtrado.isPresent() ) {
            return true;
        } else {
            return false;
        }
    }
}
