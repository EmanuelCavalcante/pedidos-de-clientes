package com.pedidosdeclientes.repository;

import com.pedidosdeclientes.modelo.PedidoCompletado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoCompletoRepository extends JpaRepository<PedidoCompletado, Integer> {
}
