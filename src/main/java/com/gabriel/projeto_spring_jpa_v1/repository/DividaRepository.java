package com.gabriel.projeto_spring_jpa_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.projeto_spring_jpa_v1.model.Divida;

public interface DividaRepository extends JpaRepository<Divida, Long>{
    
}
