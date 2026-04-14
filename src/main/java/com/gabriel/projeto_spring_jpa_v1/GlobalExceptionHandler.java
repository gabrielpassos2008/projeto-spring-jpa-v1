package com.gabriel.projeto_spring_jpa_v1;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleErroGeral(HttpServletRequest request, Model model,Exception e) {
        System.out.println("ERRO");
        e.printStackTrace();
        // Pega a URL que deu erro
        String uri = request.getRequestURI();

        // Define para onde o botão "voltar" vai
        if (uri.startsWith("/adm")) {
            model.addAttribute("home", "/adm");
        } else {
            model.addAttribute("home", "/");
        }

        // Mensagem para o usuário
        model.addAttribute("mensagem", "Ocorreu um erro inesperado. Tente novamente.");

        return "erro"; // templates/erro.html
    }
}
