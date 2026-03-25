package com.gabriel.projeto_spring_jpa_v1.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.projeto_spring_jpa_v1.model.Cliente;
import com.gabriel.projeto_spring_jpa_v1.model.Divida;
import com.gabriel.projeto_spring_jpa_v1.repository.ClienteRepository;
import com.gabriel.projeto_spring_jpa_v1.repository.DividaRepository;

import lombok.var;

@Service
public class DividaService {

    @Autowired
    private DividaRepository dividaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean abaterDivida(Long clienteId, int valor) {
        var consultaCliente = clienteRepository.findById(clienteId);
        if (consultaCliente.isEmpty()) {
            return false;
        }
        if (valor <= 0 || !validarAbatimento(valor, clienteId)) {
            return false;
        }

        Cliente cliente = consultaCliente.get();
        Divida divida = new Divida();
        divida.setValor(-valor);
        divida.setCliente(cliente);
        divida.setStatus("PAGO");
        divida.setData(LocalDateTime.now());
        dividaRepository.save(divida);
        return true;
    }

    public boolean salvarDivida(Long clienteId, Integer valor) {
        var consultaCliente = clienteRepository.findById(clienteId);
        if (consultaCliente.isEmpty()) {
            return false;
        }

        if (valor == null || valor <= 0) {
            return false;
        }

        Cliente cliente = consultaCliente.get();
        Divida divida = new Divida();
        divida.setCliente(cliente);
        divida.setValor(valor);
        divida.setStatus("PENDENTE");
        divida.setData(LocalDateTime.now());
        dividaRepository.save(divida);
        return true;
    }

    public Integer retornarTotalDivida(Long clienteid) {
        return dividaRepository.somarDividasPorCliente(clienteid);
    }

    public List<Divida> retornaTodasDividaId(Long id) {
        return dividaRepository.findByClienteIdOrderByDataDesc(id);
    }

    public boolean validarAbatimento(int valor, Long clienteid) {
        Integer total = dividaRepository.somarDividasPorCliente(clienteid);
        return total != null && valor <= total;
    }
}
