package com.pedidosdeclientes.service;

import com.pedidosdeclientes.modelo.Pedido;
import com.pedidosdeclientes.modelo.PedidoCompletado;
import com.pedidosdeclientes.serviceimpl.util.Response;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface PedidoService {

    Response<PedidoCompletado> salvarPedido(List<Pedido> pedidos);

    Response<List<Pedido>> listar(Integer id,
                                  Integer numeroDeControle,
                                  String nome,
                                  BigDecimal valor,
                                  Integer quantidade,
                                  String codigoDoCliente,
                                  Timestamp dataDeCadastro);

}
