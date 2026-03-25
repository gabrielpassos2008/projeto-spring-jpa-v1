package com.gabriel.projeto_spring_jpa_v1.service;

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

    public boolean validarLogin(String email,String senha){
        Optional<Cliente> clienteEncomtrado = repository.findByEmailAndSenha(email, senha);
        if(clienteEncomtrado.isPresent() ) {
            return true;
        } else {
            return false;
        }
    }
    public List<Cliente> retornarClienteNome(String nome){
        return repository.findByNomeContainingIgnoreCase(nome);
    } 

    public Optional<Cliente> retornaClientePorId(Long id){
        return repository.findById(id);
    }

    public boolean cadastrarCLiente(Cliente cliente){  
        try {
            repository.save(cliente);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long retornaTotalDeClienteId(Long idOPerador){
        return repository.countByOperadorId(idOPerador);
    }
}
