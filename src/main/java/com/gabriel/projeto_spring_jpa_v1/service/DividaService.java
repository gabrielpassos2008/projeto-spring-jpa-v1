package com.gabriel.projeto_spring_jpa_v1.service;

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

    public boolean abaterDivida(Long clienteId,int valor){
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
        divida.setStatus("ABATIMENTO");

        dividaRepository.save(divida);
        return true;
    }

    public Integer retornarTotalDivida(Long clienteid){
        return dividaRepository.somarDividasPorCliente(clienteid);
    }

    public boolean validarAbatimento(int valor, Long clienteid){
        Integer total = dividaRepository.somarDividasPorCliente(clienteid);
        return total != null && valor <= total;
    }
}
