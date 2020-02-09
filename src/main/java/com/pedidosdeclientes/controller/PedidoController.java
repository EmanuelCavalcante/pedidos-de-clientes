package com.pedidosdeclientes.controller;

import com.pedidosdeclientes.modelo.Pedido;
import com.pedidosdeclientes.modelo.PedidoCompletado;
import com.pedidosdeclientes.modelo.Pedidos;
import com.pedidosdeclientes.service.PedidoService;
import com.pedidosdeclientes.serviceimpl.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api-pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping("/pedido")
    public ResponseEntity<Response<PedidoCompletado>> salvarPedido(@RequestBody List<Pedido> pedidos) {
        Response<PedidoCompletado> body = pedidoService.salvarPedido(pedidos);
        if (body.getErros().isEmpty()) {
            return ResponseEntity.ok(body);
        } else {
            return ResponseEntity.badRequest().body(body);
        }
    }


    @GetMapping("/pedidos")
    public ResponseEntity<Response<List<Pedido>>> listar(@RequestParam(required = false, value = "id")
                                                                 Integer id,
                                                         @RequestParam(required = false, value = "numeroDeControle")
                                                                 Integer numeroDeControle,
                                                         @RequestParam(required = false, value = "nome")
                                                                 String nome,
                                                         @RequestParam(required = false, value = "valor")
                                                                 BigDecimal valor,
                                                         @RequestParam(required = false, value = "quantidade")
                                                                 Integer quantidade,
                                                         @RequestParam(required = false, value = "codigoDoCliente")
                                                                 String codigoDoCliente,
                                                         @RequestParam(required = false, value = "dataDeCadastro")
                                                                 Timestamp dataDeCadastro) {
        Response<List<Pedido>> body = new Response<>();
        try {
            body = pedidoService.listar(id, numeroDeControle, nome, valor, quantidade, codigoDoCliente, dataDeCadastro);
            return ResponseEntity.ok(body);
        } catch (Exception e) {
            body.getErros().add(e.getMessage());
            return ResponseEntity.status(500).body(body);
        }
    }

    @GetMapping(value = "/pedidos-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public Pedidos listarXML(@RequestParam(required = false, value = "id")
                                     Integer id,
                             @RequestParam(required = false, value = "numeroDeControle")
                                     Integer numeroDeControle,
                             @RequestParam(required = false, value = "nome")
                                     String nome,
                             @RequestParam(required = false, value = "valor")
                                     BigDecimal valor,
                             @RequestParam(required = false, value = "quantidade")
                                     Integer quantidade,
                             @RequestParam(required = false, value = "codigoDoCliente")
                                     String codigoDoCliente,
                             @RequestParam(required = false, value = "dataDeCadastro")
                                     Timestamp dataDeCadastro) {
        try {
            Response<List<Pedido>> body = pedidoService.listar(id, numeroDeControle, nome, valor, quantidade, codigoDoCliente, dataDeCadastro);
            Pedidos pedidos = new Pedidos();
            pedidos.setPedidos(body.getData());
            return pedidos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
