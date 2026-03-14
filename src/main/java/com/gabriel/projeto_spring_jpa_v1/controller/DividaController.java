package com.gabriel.projeto_spring_jpa_v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gabriel.projeto_spring_jpa_v1.model.Divida;
import com.gabriel.projeto_spring_jpa_v1.repository.DividaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DividaController {
    private DividaRepository repository;

    public DividaController(DividaRepository dividaRepository){
        this.repository = dividaRepository;
    } 
    
    
    @GetMapping("/pesquisar")
    public String getPequisar() {
        return "operador/pesquisar-usuario";
    }
    
    
    @GetMapping("/dividas")
    public String getSalvarDivida(){
        return "salvarDivida";
    }
    @PostMapping("/dividas")
    public String postSalvarDivida(Divida divida) {
        repository.save(divida);     
        System.out.println("feito");
        return "redirect:/dividas";
    }

    @GetMapping("listarDivida")
    public ModelAndView getLitardividas(){
        ModelAndView mv = new ModelAndView("/listarDivida");
        
        mv.addObject("dividas", repository.findAll());
        return mv;
    }
    
}
