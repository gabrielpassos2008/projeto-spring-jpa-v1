package com.gabriel.projeto_spring_jpa_v1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;
import com.gabriel.projeto_spring_jpa_v1.service.DividaService;
import com.gabriel.projeto_spring_jpa_v1.service.OPeradorService;

import jakarta.servlet.http.HttpSession;
import lombok.var;



@Controller
public class OperadorController {
    
    @Autowired
    private OPeradorService operadorService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DividaService dividaService;


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

        // Verifica se o campo de busca está vazio ou não foi informado
        // search == null -> significa que não foi enviado nenhum valor
        // search.isBlank() -> verifica se está vazio ou só com espaços ("   ")
        // || -> OU (se qualquer uma das condições for verdadeira, entra no if) 
        if (search == null || search.isBlank()) {
            mv.addObject("mensagem","Digite um nome para pesquisar.");
            return mv;
        }

        var todosClientes = clienteService.retornarClienteNome(search);
        for (Cliente cliente : todosClientes) {
            Integer total = dividaService.retornarTotalDivida(cliente.getId());
            cliente.setTotalDivida(total);
        }
        
        // Verifica se a lista "todosClientes" está vazia (sem nenhum cliente dentro)
        // isEmpty() -> retorna true se a lista não tiver elementos
        if (todosClientes.isEmpty()) {
            mv.addObject("mensagem","Nenhum cliente encontrado.");
        }else{
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
    
    @GetMapping("/adm/abater-divida/{id}")
    public ModelAndView getAbaterDivida(HttpSession session, @PathVariable("id")Long id) {
        if (session.getAttribute("usuario")== null) {
            return new ModelAndView("redirect:/adm");
        }
        ModelAndView mv = new ModelAndView("operador/abater-divida-operador");
        var clientePorId = clienteService.retornaClientePorId(id);
        if (!clientePorId.isPresent()) {
            mv.setViewName("operador/pesquisar-usuario");
            return mv;
        }
        mv.addObject("cliente", clientePorId.get());
        return mv;
    }

    @PostMapping("/adm/abater-divida/{id}")
    public ModelAndView postAbaterDivida(HttpSession session, @PathVariable("id") Long id,@RequestParam("valor") int valor) {
        if(session.getAttribute("usuario") == null){
            return new ModelAndView("redirect:/adm");
        }
        
        boolean sucesso = dividaService.abaterDivida(id, valor);
        if (!sucesso) {
            System.out.println("nao tem divida");
            return new ModelAndView("redirect:/adm/pesquisar-usuario");
        }
        return new ModelAndView("redirect:/adm/pesquisar-usuario");
    }
    

    @GetMapping("/adm/salvar-divida/{id}")
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
