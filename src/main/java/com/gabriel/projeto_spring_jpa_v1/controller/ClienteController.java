package com.gabriel.projeto_spring_jpa_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.model.Divida;

import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;
import com.gabriel.projeto_spring_jpa_v1.service.DividaService;


import jakarta.servlet.http.HttpSession;


@Controller
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DividaService dividaService;
    

    @GetMapping("/login")
    public String getLogin() {
        return "cliente/login-cliente";
    }
    
    @PostMapping("/login")
    public String postLogin(Cliente cliente, HttpSession session) {
        Cliente clienteBanco = clienteService.validarLogin(cliente.getEmail(),cliente.getSenha());
        if(clienteBanco != null) {
            session.setAttribute("cliente", clienteBanco);
            return "redirect:/";
        } else {
            return "cliente/login-cliente";
        }
        
    }

    @GetMapping("/")
    public ModelAndView getDeshboard(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return new ModelAndView("redirect:/login");
        }
        Cliente clienteSession = (Cliente) session.getAttribute("cliente");
        Cliente cliente = clienteService.retornaClientePorId(clienteSession.getId()).orElse(null);
        ModelAndView mv = new ModelAndView("cliente/deshboard-cliente");
        Integer totalDivida = dividaService.retornarTotalDividaId(cliente.getId());
        Integer totalPago = dividaService.retornaTotalPagoId(cliente.getId());
        mv.addObject("cliente", cliente);
        mv.addObject("totalDivida", totalDivida);
        mv.addObject("totalPago", totalPago);
        return mv;
    }
    
    @GetMapping("/historico")
    public ModelAndView getHistorico(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return new ModelAndView("redirect:/login");
        }
        Cliente clienteSession = (Cliente) session.getAttribute("cliente");
        Cliente cliente = clienteService.retornaClientePorId(clienteSession.getId()).orElse(null);
        List<Divida> dividas = dividaService.retornaHistoricoDividaId(cliente.getId());
        Integer totalDivida = dividaService.retornarTotalDividaId(cliente.getId());
        ModelAndView mv = new ModelAndView("cliente/historico-cliente");
        mv.addObject("cliente", cliente);
        mv.addObject("dividas", dividas);
        mv.addObject("totalDivida", totalDivida);
        return mv;
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
