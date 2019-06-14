package com.cursoapp.cadprodutos.model;

import java.io.Serializable;

public class Clientes implements Serializable {
    private Long id;
    private String nomeCliente;
    private String endereco;

    @Override
    public String toString() {
        return nomeCliente.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
