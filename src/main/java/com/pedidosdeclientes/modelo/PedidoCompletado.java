package com.pedidosdeclientes.modelo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table
public class PedidoCompletado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @OneToMany
    private Set<Pedido> pedidos;

    @Column
    private BigDecimal valorTotal;


    public PedidoCompletado(Integer id, List<Pedido> pedidos, BigDecimal valorTotal) {
        this.id = id;
        this.pedidos = new HashSet<>(pedidos);
        this.valorTotal = valorTotal;
    }

    public PedidoCompletado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Pedido> getPedidos() {
        return this.pedidos.stream().collect(Collectors.toList());
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = new HashSet<>(pedidos);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
