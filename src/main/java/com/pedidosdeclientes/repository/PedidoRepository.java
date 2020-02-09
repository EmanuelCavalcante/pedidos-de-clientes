package com.pedidosdeclientes.repository;

import com.pedidosdeclientes.modelo.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Pedido findByNumeroDeControle(Integer numeroDeControle);

}
