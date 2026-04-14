package com.gabriel.projeto_spring_jpa_v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.projeto_spring_jpa_v1.model.Operador;
import com.gabriel.projeto_spring_jpa_v1.repository.OperadorRepository;

@Service
public class OPeradorService {
    List<String> erros = new ArrayList<>();
    @Autowired
    private OperadorRepository repository;

    public Operador validarLogin(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha).orElse(null);
    }

    public Optional<Operador> operadorPorId(Long id) {
        return repository.findById(id);
    }

    public void salvar(Operador operador) {
        repository.save(operador);
    }

    public List<String> editarOperador(Operador operador) {
        erros.clear();
        if (validaDadosClienteEditar(operador)) {
            repository.save(operador);
            return null;
        }
        return erros;
    }

    public boolean validaDadosClienteEditar(Operador operador) {
        if (validarCampoNome(operador.getNome()) && validarCampoEmail(operador.getEmail())
                && validarCampoSenha(operador.getSenha()) && validarCampotelefone(operador.getTelefone())) {
            return true;
        }
        return false;
    }

    public boolean validarCampoNome(String nome) {
        if (nome.length() <= 70 && nome.length() >= 2) {
            return true;
        }
        erros.add("Verificar nome do cliente");
        return false;
    }

    public boolean validarCampotelefone(String telefone) {
        if (telefone.length() <= 15 && telefone.length() >= 10) {
            return true;
        }
        erros.add("Verificar telefone do cliente");
        return false;
    }

    public boolean validarCampoEmail(String email) {
        if (email.length() <= 120 && email.length() >= 5) {
            return true;
        }
        erros.add("Verificar Email do cliente");
        return false;
    }

    public boolean validarCampoSenha(String senha) {
        if (senha.length() <= 64 && senha.length() >= 8) {
            return true;
        }
        erros.add("Verificar senha do cliente");
        return false;
    }
}
