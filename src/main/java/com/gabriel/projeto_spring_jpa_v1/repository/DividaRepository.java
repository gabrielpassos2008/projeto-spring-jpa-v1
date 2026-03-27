package com.gabriel.projeto_spring_jpa_v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.projeto_spring_jpa_v1.model.Divida;

public interface DividaRepository extends JpaRepository<Divida, Long> {
    // Retorna a soma de todas as dívidas de um cliente
    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.cliente.id = :clienteId")
    Integer somarDividasPorCliente(@Param("clienteId") Long clienteId);

    // Retorna a lista de dívidas de um cliente ordenadas por data
    List<Divida> findByClienteIdOrderByDataDesc(Long clienteId);

    // Retorna a soma de todas as dívidas do sistema
    @Query("SELECT SUM(d.valor) FROM Divida d")
    Integer somarTotalDivida();

    // Retorna a soma de todos os valores pagos do sistema
    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.status = 'PAGO'")
    Integer somarTotalPago();

    // Retorna a soma das dívidas pendentes de um cliente
    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.status = 'PENDENTE' AND d.cliente.id = :clienteId")
    Integer somarTotalPendenteId(@Param("clienteId") Long clienteId);

    // Retorna a soma dos valores pagos de um cliente
    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.status = 'PAGO' AND d.cliente.id = :clienteId")
    Integer somarTotalPagoId(@Param("clienteId") Long clienteId);
}
