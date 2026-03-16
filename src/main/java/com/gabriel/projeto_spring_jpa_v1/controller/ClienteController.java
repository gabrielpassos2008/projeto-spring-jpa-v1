package com.gabriel.projeto_spring_jpa_v1.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.repository.ClienteRepository;

@Controller
public class ClienteController {
    
    private ClienteRepository repository;

    public ClienteController(ClienteRepository clienteRepository){
        this.repository = clienteRepository;
    } 

    @GetMapping("/")
    public String getLogin() {
        return "login-cliente";
    }
    
    @PostMapping("/")
    public String postLogin(Cliente cliente) {
        Optional<Cliente> clienteEncomtrado = repository.findByEmailAndSenha(cliente.getEmail(), cliente.getSenha());
        if(clienteEncomtrado.isPresent() ) {
            return "operador/pesquisar-usuario";
        } else {
            return "login-cliente";
        }
        
    }
    
}
