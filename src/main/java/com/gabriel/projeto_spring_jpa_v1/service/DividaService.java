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
// Abate um valor da dívida do cliente (cria um registro negativo como pagamento)
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
// Salva uma nova dívida para o cliente
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
// Retorna o total de dívidas de um cliente específico
    public Integer retornarTotalDividaId(Long clienteid) {
        Integer valorTotal = dividaRepository.somarDividasPorCliente(clienteid);
        if (valorTotal == null) {
            valorTotal = 0;
        }

        return valorTotal;
    }
// Retorna o total de dívidas de todos os clientes
    public Integer retornaTotalDivida(){
        return dividaRepository.somarTotalDivida();
    }
// Retorna o total pendente (ainda não pago) de um cliente
    public Integer retornaTotalPendenteId(Long id){
        Integer valorTotal = dividaRepository.somarTotalPendenteId(id);
        if (valorTotal == null) {
            valorTotal =  0;
        }
        return valorTotal;
    }
// Retorna o total pago de um cliente
    public Integer retornaTotalPagoId(Long id){
        Integer valorTotal = dividaRepository.somarTotalPagoId(id);
        if (valorTotal == null) {
            valorTotal =  0;
        }
        valorTotal = Math.abs(valorTotal);
        return valorTotal;
    }
// Retorna o total pago de todos os clientes
    public Integer retornaTotalPago(){
        Integer total = dividaRepository.somarTotalPago();
        total = Math.abs(total);
        return total;
    }
// Retorna o histórico de dívidas de um cliente (ordenado por data)  
    public List<Divida> retornaHistoricoDividaId(Long id) {
        return dividaRepository.findByClienteIdOrderByDataDesc(id);
    }
// Valida se o valor a ser abatido não é maior que a dívida total
    public boolean validarAbatimento(int valor, Long clienteid) {
        Integer total = dividaRepository.somarDividasPorCliente(clienteid);
        return total != null && valor <= total;
    }
}
