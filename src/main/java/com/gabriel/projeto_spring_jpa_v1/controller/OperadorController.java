package com.gabriel.projeto_spring_jpa_v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;
import com.gabriel.projeto_spring_jpa_v1.service.OPeradorService;

import jakarta.servlet.http.HttpSession;
import lombok.var;


@Controller
public class OperadorController {
    
    @Autowired
    private OPeradorService operadorService;

    @Autowired
    private ClienteService clienteService;


    @GetMapping("/adm")
    public String getLoginAdm() {
        return "operador/login-operador";
    }
    
    @PostMapping("/adm")
    public String postLoginAdm(Operador operador,HttpSession session) {
        if (operadorService.validarLogin(operador.getEmail(), operador.getSenha())) {
            session.setAttribute("usuario", operador);
            return "redirect:/adm/pesquisar-usuario";
        }else{
            return "operador/login-operador";
        }
    }

    @GetMapping("/adm/pesquisar-usuario")
    public ModelAndView getPesquisarUsuario(HttpSession session,@RequestParam(required = false) String search) {

        if(session.getAttribute("usuario") == null){
            return new ModelAndView("redirect:/adm");
        }
        ModelAndView mv = new ModelAndView("operador/pesquisar-usuario");
        if (search == null || search.isBlank()) {
            mv.addObject("mensagem","Digite um nome para pesquisar.");
            return mv;
        }

        var todosClientes = clienteService.retornarClienteNome(search);
        
        if (todosClientes.isEmpty()) {
            mv.addObject("mensagem","Nenhum cliente encontrado.");
            System.out.println("nao achou");
        }else{
            System.out.println("deu certo");
            mv.addObject("clientes",todosClientes);
        }
        return mv;
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
    
    @GetMapping("/adm/sair")
    public String getSair(HttpSession session) {
        session.invalidate();
        return "redirect:/adm";
    }
    
    
}
