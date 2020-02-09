package com.pedidosdeclientes.repository;

import com.pedidosdeclientes.modelo.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class QueryDinamicaPedidoRepository {


    @PersistenceContext
    @Autowired
    public EntityManager manager;

    public List<Pedido> listar(Integer id, Integer numeroDeControle, String nome, BigDecimal valor, Integer quantidade, String codigoDoCliente, Timestamp dataDeCadastro) {
        String sql = "SELECT  pedido FROM Pedido pedido";
        String sqlWhere = null;
        if (id != null) {
            sqlWhere += "pedido.id = :id";
        }
        if (numeroDeControle != null) {
            sqlWhere += "pedido.numeroDeControle = :numeroDeControle";
        }
        if (nome != null) {
            sqlWhere += "pedido.nome = :nome";
        }
        if (valor != null) {
            sqlWhere += "pedido.valor = :valor";
        }
        if (quantidade != null) {
            sqlWhere += "pedido.quantidade = :quantidade";
        }
        if (codigoDoCliente != null) {
            sqlWhere += "pedido.codigoDoCliente = :codigoDoCliente";
        }
        if (dataDeCadastro != null) {
            sqlWhere += "pedido.dataDeCadastro = :dataDeCadastro";
        }

        if (sqlWhere != null) {
            sql += " WHERE " + sqlWhere;
        }

        Query query = manager.createQuery(sql);


        if (id != null) {
            query.setParameter("id", id);
        }
        if (numeroDeControle != null) {
            query.setParameter("numeroDeControle", numeroDeControle);
        }
        if (nome != null) {
            query.setParameter("nome", nome);
        }
        if (valor != null) {
            query.setParameter("valor", valor);
        }
        if (quantidade != null) {
            query.setParameter("quantidade", quantidade);
        }
        if (codigoDoCliente != null) {
            query.setParameter("codigoDoCliente", codigoDoCliente);
        }
        if (dataDeCadastro != null) {
            query.setParameter("dataDeCadastro", dataDeCadastro);
        }


        return query.getResultList();


    }
}
