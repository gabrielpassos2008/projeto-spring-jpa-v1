package com.gabriel.projeto_spring_jpa_v1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.projeto_spring_jpa_v1.model.Divida;

public interface DividaRepository extends JpaRepository<Divida, Long>{
    
    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.cliente.id = :clienteId")
    Integer somarDividasPorCliente(@Param("clienteId") Long clienteId);

    List<Divida> findByClienteIdOrderByDataDesc(Long clienteId);

    @Query("SELECT SUM(d.valor) FROM Divida d")
    Integer somarTotalDivida();

    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.status = 'PAGO'")
    Integer somarTotalPago();

    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.status = 'PENDENTE' AND d.cliente.id = :clienteId")
    Integer somarTotalPendenteId(@Param("clienteId") Long clienteId);

    @Query("SELECT SUM(d.valor) FROM Divida d WHERE d.status = 'PAGO' AND d.cliente.id = :clienteId")
    Integer somarTotalPagoId(@Param("clienteId") Long clienteId);
}
