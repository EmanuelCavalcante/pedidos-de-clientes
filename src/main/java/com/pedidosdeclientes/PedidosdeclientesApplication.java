package com.pedidosdeclientes;

import com.pedidosdeclientes.modelo.Pedido;
import com.pedidosdeclientes.modelo.PedidoCompletado;
import com.pedidosdeclientes.service.PedidoService;
import com.pedidosdeclientes.serviceimpl.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RestController
public class PedidosdeclientesApplication {

    @Autowired
    private PedidoService pedidoService;

    public static void main(String[] args) {
        SpringApplication.run(PedidosdeclientesApplication.class, args);

    }


    @GetMapping("/")
    public Response<PedidoCompletado> hello() {
        List<Pedido> pedidos = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Pedido pedido = new Pedido();
            pedido.setCodigoDoCliente(i);
            pedido.setNome("Produto " + i);
            pedido.setNumeroDeControle(i);
            pedido.setValor(new BigDecimal(i + 1));
            pedidos.add(pedido);
        }
        return pedidoService.salvarPedido(pedidos);
    }
}


