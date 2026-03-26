package com.gabriel.projeto_spring_jpa_v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;

import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;
import com.gabriel.projeto_spring_jpa_v1.service.DividaService;
import com.gabriel.projeto_spring_jpa_v1.service.OPeradorService;

import jakarta.servlet.http.HttpSession;


@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DividaService dividaService;
    
    @Autowired
    private OPeradorService operadorService;

    @GetMapping("/login")
    public String getLogin() {
        return "cliente/login-cliente";
    }
    
    @PostMapping("/login")
    public String postLogin(Cliente cliente, HttpSession session) {
        if(clienteService.validarLogin(cliente.getEmail(),cliente.getSenha())) {
            session.setAttribute("cliente", cliente);
            return "redirect:/";
        } else {
            return "cliente/login-cliente";
        }
        
    }

    @GetMapping("/")
    public String getDeshboard(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return "redirect:/login";
        }
        return "cliente/deshboard-cliente";
    }
    
    @GetMapping("/historico")
    public String getHistorico(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return "redirect:/login";
        }
        return "cliente/historico-cliente";
    }

    @GetMapping("/perfil")
    public String getPerfil(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return "redirect:/login";
        }
        return "cliente/perfil-cliente";
    }

    @GetMapping("/perfil/editar")
    public String getEditarPerfil(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return "redirect:/login";
        }
        return "cliente/editar-Perfil-cliente";
    }
    
    @GetMapping("/sair")
    public String getSair(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
}
