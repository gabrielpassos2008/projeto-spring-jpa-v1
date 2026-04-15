package com.gabriel.projeto_spring_jpa_v1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    List<String> erros = new ArrayList<>();

    public Cliente validarLogin(String email, String senha) {
        return repository.findByEmailAndSenha(email, senha).orElse(null);
    }

    public boolean validarEmailDisponivel(String email) {
        if (repository.findByEmail(email).isPresent()) {
            return true;
        }
        erros.add("Ops! Esse email já foi utilizado. Tente outro.");
        return false;
    }

    public List<Cliente> retornarClienteNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Cliente> retornaClientePorId(Long id) {
        return repository.findById(id);
    }

    public List<String> cadastrarCLiente(Cliente cliente) {
        erros.clear();
        if (validaDadosCliente(cliente)) {
            repository.save(cliente);
            return null;
        } else {
            return erros;
        }
    }

    public List<String> editarCLiente(Cliente cliente) {
        erros.clear();
        if (validaDadosClienteEditar(cliente)) {
            repository.save(cliente);
            return null;
        }
        return erros;
    }

    public boolean validaDadosCliente(Cliente cliente) {
        // if para cada um para salvar cada erro no array.
        // se fosse somente um if, iria salvar somente o primeiro erro.
        boolean valido = true;

        if (!validarCampoNome(cliente.getNome())) {
            valido = false;
        }
        if (!validarCampoEmail(cliente.getEmail())) {
            valido = false;
        }
        if (!validarCampoSenha(cliente.getSenha())) {
            valido = false;
        }
        if (!validarCampotelefone(cliente.getTelefone())) {
            valido = false;
        }
        if (!validarEmailDisponivel(cliente.getEmail())) {
            valido = false;
        }
        return valido;
    }

    public boolean validaDadosClienteEditar(Cliente cliente) {
        // if para cada um para salvar cada erro no array.
        // se fosse somente um if, iria salvar somente o primeiro erro.
        boolean valido = true;

        if (!validarCampoNome(cliente.getNome())) {
            valido = false;
        }
        if (!validarCampoEmail(cliente.getEmail())) {
            valido = false;
        }
        if (!validarCampoSenha(cliente.getSenha())) {
            valido = false;
        }
        if (!validarCampotelefone(cliente.getTelefone())) {
            valido = false;
        }
        return valido;
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

    public Long retornaTotalDeClienteId(Long idOPerador) {
        return repository.countByOperadorId(idOPerador);
    }
}