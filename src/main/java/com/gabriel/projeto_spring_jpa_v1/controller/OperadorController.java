package com.gabriel.projeto_spring_jpa_v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.service.OPeradorService;

import jakarta.servlet.http.HttpSession;


@Controller
public class OperadorController {
    
    private OPeradorService service;

    public OperadorController(OPeradorService OPeradorService){
        this.service = OPeradorService;
    } 

    @GetMapping("/adm")
    public String getLoginAdm() {
        return "operador/login-operador";
    }
    
    @PostMapping("/adm")
    public String postLoginAdm(Operador operador,HttpSession session) {
        if (service.validarLogin(operador.getEmail(), operador.getSenha())) {
            session.setAttribute("usuario", operador);
            return "redirect:/adm/pesquisar-usuario";
        }else{
            return "operador/login-operador";
        }
    }

    @GetMapping("/adm/pesquisar-usuario")
    public String getPesquisarUsuario(HttpSession session) {
        if(session.getAttribute("usuario") == null){
            return "redirect:/adm";
        }
        return "operador/pesquisar-usuario";
    }

    @GetMapping("/adm/cadastrar-cliente")
    public String getCadastrarCliente(HttpSession session) {
        if(session.getAttribute("usuario")== null){
            return "redirect:/adm";
        }
        return "operador/cadastrarCliente-operador";
    }
    
    @GetMapping("/adm/abater-divida")
    public String getAbaterDivida(HttpSession session) {
        if (session.getAttribute("usuario")== null) {
            return "redirect:/adm";
        }
        return "operador/abater-divida-operador";
    }
    

    @GetMapping("/adm/salvar-divida")
    public String getSalvarDivida(HttpSession session) {
        if (session.getAttribute("usuario")== null) {
            return "redirect:/adm";
        }
        return "operador/salvarDivida-operador";
    }

    @GetMapping("/adm/perfil")
    public String getPerfil(HttpSession session) {
        if (session.getAttribute("usuario")== null) {
            return "redirect:/adm";
        }
        return "operador/perfil-operador";
    }
    
    @GetMapping("/adm/perfil/editar")
    public String getEditarPerfil(HttpSession session) {
        if (session.getAttribute("usuario")== null) {
            return "redirect:/adm";
        }
        return "operador/editar-Perfil-operador";
    }
    
    
}
