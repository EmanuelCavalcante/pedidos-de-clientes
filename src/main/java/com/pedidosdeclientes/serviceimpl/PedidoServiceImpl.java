package com.pedidosdeclientes.serviceimpl;

import com.pedidosdeclientes.modelo.Pedido;
import com.pedidosdeclientes.modelo.PedidoCompletado;
import com.pedidosdeclientes.repository.PedidoCompletoRepository;
import com.pedidosdeclientes.repository.PedidoRepository;
import com.pedidosdeclientes.repository.QueryDinamicaPedidoRepository;
import com.pedidosdeclientes.service.PedidoService;
import com.pedidosdeclientes.serviceimpl.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {


    @Autowired
    private QueryDinamicaPedidoRepository queryDinamicaPedidoRepository;

    @Autowired
    private PedidoCompletoRepository pedidoCompletoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    private final Double DESCONTO_5_PORCENTO = 5d;
    private final Double DESCONTO_10_PORCENTO = 10d;
    private final int NUMERO_MAXIMO_DE_PEDIDOS = 10;

    @Override
    public Response<PedidoCompletado> salvarPedido(List<Pedido> pedidos) {
        Response<PedidoCompletado> response = new Response<>();
        verificarQuantidadeDePedidos(pedidos, response);
        if (!response.getErros().isEmpty()) {
            return response;
        }
        verificarNumeroDeControler(pedidos, response);
        if (!response.getErros().isEmpty()) {
            return response;
        }
        Integer quantidade = calcularAQuantidade(pedidos);
        BigDecimal valorTotal = calcularValorTotal(pedidos);


        if (quantidade > DESCONTO_5_PORCENTO && quantidade < DESCONTO_10_PORCENTO) {
            valorTotal = aplicarDesconto(valorTotal, DESCONTO_5_PORCENTO);
        }
        if (quantidade >= DESCONTO_10_PORCENTO) {
            valorTotal = aplicarDesconto(valorTotal, DESCONTO_10_PORCENTO);
        }
        for (int i = 0; i < pedidos.size(); i++) {
            pedidoRepository.save(pedidos.get(i));
        }

        PedidoCompletado data = new PedidoCompletado();
        data.setPedidos(pedidos);
        data.setValorTotal(valorTotal);
        pedidoCompletoRepository.save(data);
        response.setData(data);
        return response;
    }

    @Override
    public Response<List<Pedido>> listar(Integer id, Integer numeroDeControle, String nome, BigDecimal valor, Integer quantidade, String codigoDoCliente, Timestamp dataDeCadastro) {
        Response<List<Pedido>> response = new Response<>();
        List<Pedido> pedidos = queryDinamicaPedidoRepository.listar(id, numeroDeControle, nome, valor, quantidade, codigoDoCliente, dataDeCadastro);
        response.setData(pedidos);
        return response;
    }

    public Integer calcularAQuantidade(List<Pedido> pedidos) {
        if (pedidos != null) {
            return pedidos.size();
        } else {
            return 0;
        }
    }

    public void verificarNumeroDeControler(List<Pedido> pedidos, Response<PedidoCompletado> response) {
        for (Pedido pedido : pedidos) {
            Pedido pedidoDoBanco = pedidoRepository.findByNumeroDeControle(pedido.getNumeroDeControle());
            if (pedidoDoBanco != null) {
                response.getErros().add("Numero de controle:" + pedidoDoBanco.getNumeroDeControle() + " repetido");
                break;
            }
        }


    }

    public void verificarQuantidadeDePedidos(List<Pedido> pedidos, Response<PedidoCompletado> response) {
        if (pedidos != null && !pedidos.isEmpty()) {
            if (pedidos.size() > NUMERO_MAXIMO_DE_PEDIDOS) {
                response.getErros().add("Não é permitido mais de " + NUMERO_MAXIMO_DE_PEDIDOS + " pedidos");
            }

        } else {
            response.getErros().add("Insira pelo menos 1 pedido");
        }

    }

    public BigDecimal aplicarDesconto(BigDecimal valorTotal, Double desconto) {
        BigDecimal percentualDeDesconto = new BigDecimal(desconto / 100);
        BigDecimal valorASerDescontado = percentualDeDesconto.multiply(valorTotal);
        valorTotal = valorTotal.subtract(valorASerDescontado);
        return valorTotal;
    }

    public BigDecimal calcularValorTotal(List<Pedido> pedidos) {
        BigDecimal valorTotal = new BigDecimal("0");
        for (Pedido pedido : pedidos) {
            valorTotal = valorTotal.add(pedido.getValor());
        }
        return valorTotal;
    }
}
