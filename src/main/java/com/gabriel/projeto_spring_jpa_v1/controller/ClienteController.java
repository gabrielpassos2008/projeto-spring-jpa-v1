package com.gabriel.projeto_spring_jpa_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.model.Divida;

import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;
import com.gabriel.projeto_spring_jpa_v1.service.DividaService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
    public ModelAndView postLogin(Cliente cliente, HttpSession session) {
        Cliente clienteBanco = clienteService.validarLogin(cliente.getEmail(), cliente.getSenha());
        if (clienteBanco != null) {
            session.setAttribute("cliente", clienteBanco);
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView mv = new ModelAndView("cliente/login-cliente");
            mv.addObject("mensagemErro", "Email ou senha inválidos!");
            return mv;
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
    public ModelAndView getPerfil(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return new ModelAndView("redirect:/login");
        }
        Cliente clienteSession = (Cliente) session.getAttribute("cliente");
        Cliente cliente = clienteService.retornaClientePorId(clienteSession.getId()).orElse(null);
        ModelAndView mv = new ModelAndView("cliente/perfil-cliente");
        mv.addObject("cliente", cliente);
        return mv;
    }

    @GetMapping("/perfil/editar")
    public ModelAndView getEditarPerfil(HttpSession session) {
        if (session.getAttribute("cliente") == null) {
            return new ModelAndView("redirect:/login");
        }
        Cliente clienteSession = (Cliente) session.getAttribute("cliente");
        Cliente cliente = clienteService.retornaClientePorId(clienteSession.getId()).orElse(null);
        ModelAndView mv = new ModelAndView("cliente/editar-Perfil-cliente");
        mv.addObject("cliente", cliente);
        return mv;
    }

    @PostMapping("/perfil/editar")
    public ModelAndView postEditarPerfil(HttpSession session, @Valid Cliente clienteForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("cliente") == null) {
            return new ModelAndView("redirect:/login");
        }
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("cliente/editar-Perfil-cliente");
            mv.addObject("mensagemErro", "Todos os campos devem estar preenchidos!");
            mv.addObject("cliente", clienteForm);
            return mv;
        }
        Cliente clienteSession = (Cliente) session.getAttribute("cliente");
        Cliente cliente = clienteService.retornaClientePorId(clienteSession.getId()).orElse(null);
        cliente.setApelido(clienteForm.getApelido());
        cliente.setEmail(clienteForm.getEmail());
        cliente.setTelefone(clienteForm.getTelefone());
        cliente.setSenha(clienteForm.getSenha());

        clienteService.cadastrarCLiente(cliente);
        ModelAndView mv = new ModelAndView("redirect:/");
        redirectAttributes.addFlashAttribute("sucesso", "Salvo com sucesso!");
        return mv;
    }

    @GetMapping("/sair")
    public String getSair(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}
