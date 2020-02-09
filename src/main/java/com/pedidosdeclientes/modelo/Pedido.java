package com.pedidosdeclientes.modelo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.pedidosdeclientes.serviceimpl.util.GeradorDeNumeros;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table
@JacksonXmlRootElement(localName = "Pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JacksonXmlProperty(isAttribute = true)
    private Integer id;

    @Column
    @JacksonXmlProperty
    private Integer numeroDeControle;

    @Column
    @NotNull
    @JacksonXmlProperty
    private String nome;

    @Column
    @NotNull
    @JacksonXmlProperty
    private BigDecimal valor;

    @Column
    @JacksonXmlProperty
    private Integer quantidade = 1;

    @Column
    @NotNull
    @JacksonXmlProperty
    private Integer codigoDoCliente = GeradorDeNumeros.gerarNumeroAleatorio();

    @Column
    @JacksonXmlProperty
    private Timestamp dataDeCadastro = new Timestamp(new Date().getTime());

    public Pedido() {
    }

    public Pedido(Integer id, Integer numeroDeControle, String nome, BigDecimal valor, Integer quantidade, Integer codigoDoCliente, Timestamp dataDeCadastro) {
        this.id = id;
        this.numeroDeControle = numeroDeControle;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.codigoDoCliente = codigoDoCliente;
        this.dataDeCadastro = dataDeCadastro;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroDeControle() {
        return numeroDeControle;
    }

    public void setNumeroDeControle(Integer numeroDeControle) {
        this.numeroDeControle = numeroDeControle;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getCodigoDoCliente() {
        return codigoDoCliente;
    }

    public void setCodigoDoCliente(Integer codigoDoCliente) {
        this.codigoDoCliente = codigoDoCliente;
    }

    public Timestamp getDataDeCadastro() {
        return dataDeCadastro;
    }

    public void setDataDeCadastro(Timestamp dataDeCadastro) {
        this.dataDeCadastro = dataDeCadastro;
    }
}
