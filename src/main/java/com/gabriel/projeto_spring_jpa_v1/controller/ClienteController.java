package com.gabriel.projeto_spring_jpa_v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;

import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;

import jakarta.servlet.http.HttpSession;
@Controller
public class ClienteController {
    
    private ClienteService service;

    public ClienteController(ClienteService clienteService){
        this.service = clienteService;
    } 

    @GetMapping("/")
    public String getLogin() {
        return "cliente/login-cliente";
    }
    
    @PostMapping("/")
    public String postLogin(Cliente cliente, HttpSession session) {
        if(service.validarLogin(cliente.getEmail(),cliente.getSenha())) {
            session.setAttribute("cliente", cliente);
            return "operador/pesquisar-usuario";
        } else {
            return "cliente/login-cliente";
        }
        
    }
    
}
