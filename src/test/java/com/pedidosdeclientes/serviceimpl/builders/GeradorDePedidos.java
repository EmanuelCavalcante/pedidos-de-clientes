package com.pedidosdeclientes.serviceimpl.builders;

import com.pedidosdeclientes.modelo.Pedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GeradorDePedidos {

    public static List<Pedido> gerarPedidosAleatorios(Integer quantidade) {
        List<Pedido> pedidos = new ArrayList<>();
        for (int i = 0; i < quantidade; i++) {
            Pedido pedido = new Pedido();
            pedido.setCodigoDoCliente(i);
            pedido.setNome("Produto " + i);
            pedido.setNumeroDeControle(i);
            pedido.setValor(new BigDecimal(i + 1));
            pedidos.add(pedido);
        }
        return pedidos;
    }


}
