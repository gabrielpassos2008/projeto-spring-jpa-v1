package com.gabriel.projeto_spring_jpa_v1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Divida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataVencimento;
    private String dataCompra;
    private int valor;
    private String status;

    public Divida(){}

    public Divida(String dataVencimento, String dataCompra, int valor,String status){
        this.dataVencimento = dataVencimento;
        this.dataCompra = dataCompra;
        this.valor = valor;
        this.status = status;
    }
    public String getDataCompra() {
        return dataCompra;
    }
    public String getDataVencimento() {
        return dataVencimento;
    }
    public String getStatus() {
        return status;
    }
    public int getValor() {
        return valor;
    }
    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }
    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setValor(int valor) {
        this.valor = valor;
    }
}
