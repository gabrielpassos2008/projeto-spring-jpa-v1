package com.gabriel.projeto_spring_jpa_v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class errosController {
    @GetMapping("/teste-erro")
    public String testeErro() {
        throw new RuntimeException("Erro de teste");
    }
}
