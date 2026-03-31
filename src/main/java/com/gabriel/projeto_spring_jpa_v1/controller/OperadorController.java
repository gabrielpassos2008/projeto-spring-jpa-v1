package com.gabriel.projeto_spring_jpa_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.model.Divida;
import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.service.ClienteService;
import com.gabriel.projeto_spring_jpa_v1.service.DividaService;
import com.gabriel.projeto_spring_jpa_v1.service.OPeradorService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    public ModelAndView postLoginAdm(Operador operador, HttpSession session) {
        Operador operadorBanco = operadorService.validarLogin(operador.getEmail(), operador.getSenha());

        if (operadorBanco != null) {
            session.setAttribute("usuario", operadorBanco);
            return new ModelAndView("redirect:/adm/pesquisar-usuario");
        } else {
            ModelAndView mv = new ModelAndView("operador/login-operador");
            mv.addObject("mensagemErro", "Email ou senha inválidos!");
            return mv;
        }
    }

    @GetMapping("/adm/perfil")
    public ModelAndView getPerfil(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        Operador operadorSession = (Operador) session.getAttribute("usuario");
        Operador operador = operadorService.operadorPorId(operadorSession.getId()).orElse(null);
        if (operador == null) {
            return new ModelAndView("redirect:/adm");
        }

        ModelAndView mv = new ModelAndView("operador/perfil-operador");
        mv.addObject("operador", operador);
        return mv;
    }

    @GetMapping("/adm/historico-cliente/{id}")
    public ModelAndView getHistoricoCliente(HttpSession session, @PathVariable("id") Long id) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        ModelAndView mv = new ModelAndView("operador/historicoCliente-operador");
        List<Divida> dividas = dividaService.retornaHistoricoDividaId(id);
        Cliente cliente = clienteService.retornaClientePorId(id).get();
        Integer totalDivida = dividaService.retornarTotalDividaId(id);
        mv.addObject("totalDivida", totalDivida);
        mv.addObject("cliente", cliente);
        mv.addObject("dividas", dividas);
        return mv;
    }

    @GetMapping("/adm/perfil/editar")
    public ModelAndView getEditarPerfil(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        Operador operadorSession = (Operador) session.getAttribute("usuario");
        Operador operador = operadorService.operadorPorId(operadorSession.getId()).orElse(null);

        ModelAndView mv = new ModelAndView("operador/editar-Perfil-operador");
        mv.addObject("operador", operador);
        return mv;
    }

    @PostMapping("/adm/perfil/editar")
    public ModelAndView postEditarPerfil(HttpSession session, Operador operadorForm,RedirectAttributes redirectAttributes, @Valid Operador operadorErroOperado, BindingResult result) {

        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }

        Operador operadorSession = (Operador) session.getAttribute("usuario");
        Operador operador = operadorService.operadorPorId(operadorSession.getId()).orElse(null);

        if (operador == null) {
            return new ModelAndView("redirect:/adm");
        }

        operador.setNome(operadorForm.getNome());
        operador.setEmail(operadorForm.getEmail());
        operador.setTelefone(operadorForm.getTelefone());
        operador.setSenha(operadorForm.getSenha());

        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("operador/editar-Perfil-operador");
            mv.addObject("mensagemErro", "Todos os campos devem estar preenchidos!");
            return mv;
        }
        operadorService.salvar(operador);
        ModelAndView mv = new ModelAndView("redirect:/adm/pesquisar-usuario");

        // ✅ mensagem
        redirectAttributes.addFlashAttribute("sucesso", "Salvo com sucesso!");
        return mv;
    }

    @GetMapping("/adm/cadastrar-cliente")
    public String getCadastrarCliente(HttpSession session, Cliente cliente) {
        if (session.getAttribute("usuario") == null) {
            return "redirect:/adm";
        }
        return "operador/cadastrarCliente-operador";
    }

    @PostMapping("/adm/cadastrar-cliente")
    public ModelAndView postCadastrarCliente(HttpSession session, Cliente cliente,RedirectAttributes redirectAttributes,@Valid Cliente clienteErro,BindingResult result) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        Operador operadorSession = (Operador) session.getAttribute("usuario");
        Operador operador = operadorService.operadorPorId(operadorSession.getId()).orElse(null);

        if (operador == null) {
            return new ModelAndView("redirect:/adm");
        }
        cliente.setOperador(operador);
        ModelAndView mv = new ModelAndView("operador/cadastrarCliente-operador");

        if (result.hasErrors()) {
            mv.addObject("mensagemErro", "Todos os campos devem estar preenchidos!");
            return mv;
        }
    
        cliente.setOperador(operador);
    
        if (clienteService.cadastrarCLiente(cliente)) {
            redirectAttributes.addFlashAttribute("sucesso", "sucesso ao cadastrar um cliente");
            return new ModelAndView("redirect:/adm/pesquisar-usuario");
        }

        mv.addObject("mensagemErro", "Erro ao cadastrar cliente!");
        return mv;
    }

    @GetMapping("/adm/pesquisar-usuario")
    public ModelAndView getPesquisarUsuario(HttpSession session, @RequestParam(required = false) String search) {

        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        ModelAndView mv = new ModelAndView("operador/pesquisar-usuario");
        Integer totalDivida = dividaService.retornaTotalDivida();
        mv.addObject("totalDivida", totalDivida);
        Integer totalDividaPaga = dividaService.retornaTotalPago();
        mv.addObject("totalDividaPaga", totalDividaPaga);
        Operador operadorSession = (Operador) session.getAttribute("usuario");
        Long totalCliente = clienteService.retornaTotalDeClienteId(operadorSession.getId());
        mv.addObject("totalClinte", totalCliente);
        // Verifica se o campo de busca está vazio ou não foi informado
        // search == null -> significa que não foi enviado nenhum valor
        // search.isBlank() -> verifica se está vazio ou só com espaços (" ")
        // || -> OU (se qualquer uma das condições for verdadeira, entra no if)
        if (search == null || search.isBlank()) {
            mv.addObject("mensagemErro", "Digite um nome para pesquisar.");
            return mv;
        }

        var todosClientes = clienteService.retornarClienteNome(search);
        for (Cliente cliente : todosClientes) {
            Integer total = dividaService.retornarTotalDividaId(cliente.getId());
            cliente.setTotalDivida(total);
        }

        // Verifica se a lista "todosClientes" está vazia (sem nenhum cliente dentro)
        // isEmpty() -> retorna true se a lista não tiver elementos
        if (todosClientes.isEmpty()) {
            mv.addObject("mensagemErro", "Nenhum cliente encontrado.");
        } else {
            mv.addObject("clientes", todosClientes);
        }

        return mv;
    }

    @GetMapping("/adm/abater-divida/{id}")
    public ModelAndView getAbaterDivida(HttpSession session, @PathVariable("id") Long id) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        ModelAndView mv = new ModelAndView("operador/abater-divida-operador");
        Integer totalPago = dividaService.retornaTotalPagoId(id);
        Integer totalPendente = dividaService.retornarTotalDividaId(id);
        mv.addObject("totalPago", totalPago);
        mv.addObject("totalPendente", totalPendente);
        var clientePorId = clienteService.retornaClientePorId(id);
        if (!clientePorId.isPresent()) {
            mv.setViewName("operador/pesquisar-usuario");
            return mv;
        }
        mv.addObject("cliente", clientePorId.get());
        return mv;
    }

    @PostMapping("/adm/abater-divida/{id}")
    public ModelAndView postAbaterDivida(HttpSession session, @PathVariable("id") Long id,
            @RequestParam("valor") Integer valor) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        
        boolean sucesso = dividaService.abaterDivida(id, valor);
        if (!sucesso) {
            return new ModelAndView("redirect:/adm/pesquisar-usuario");
        }
        return new ModelAndView("redirect:/adm/pesquisar-usuario");
    }

    @GetMapping("/adm/salvar-divida/{id}")
    public ModelAndView getSalvarDivida(HttpSession session, @PathVariable("id") Long id) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        var clientePorId = clienteService.retornaClientePorId(id);
        ModelAndView mv = new ModelAndView("operador/salvarDivida-operador");
        Integer totalPago = dividaService.retornaTotalPagoId(id);
        Integer totalPendente = dividaService.retornarTotalDividaId(id);
        mv.addObject("totalPago", totalPago);
        mv.addObject("totalPendente", totalPendente);
        mv.addObject("cliente", clientePorId.get());
        return mv;
    }

    @PostMapping("/adm/salvar-divida/{id}")
    public ModelAndView postSalvarDivida(HttpSession session, @PathVariable("id") Long id,
            @RequestParam("valor") Integer valor) {
        if (session.getAttribute("usuario") == null) {
            return new ModelAndView("redirect:/adm");
        }
        dividaService.salvarDivida(id, valor);
        return new ModelAndView("redirect:/adm/pesquisar-usuario");
    }

    @GetMapping("/adm/sair")
    public String getSair(HttpSession session) {
        session.invalidate();
        return "redirect:/adm";
    }

}
