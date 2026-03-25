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
}
