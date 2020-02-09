package com.pedidosdeclientes.serviceimpl;

import com.pedidosdeclientes.modelo.Pedido;
import com.pedidosdeclientes.modelo.PedidoCompletado;
import com.pedidosdeclientes.repository.PedidoCompletoRepository;
import com.pedidosdeclientes.repository.PedidoRepository;
import com.pedidosdeclientes.repository.QueryDinamicaPedidoRepository;
import com.pedidosdeclientes.service.PedidoService;
import com.pedidosdeclientes.serviceimpl.builders.GeradorDePedidos;
import com.pedidosdeclientes.serviceimpl.util.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private QueryDinamicaPedidoRepository queryDinamicaPedidoRepository;
    @Mock
    private PedidoCompletoRepository pedidoCompletoRepository;
    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService = new PedidoServiceImpl();


    @Test
    @DisplayName("Testando quantidade de pedidos")
    void testeQuantidadeDePedidos() {
        Response response = pedidoService.salvarPedido(GeradorDePedidos.gerarPedidosAleatorios(19));
        assertEquals("Não é permitido mais de " + 10 + " pedidos", response.getErros().get(0));
        Response response2 = pedidoService.salvarPedido(GeradorDePedidos.gerarPedidosAleatorios(0));
        assertEquals("Insira pelo menos 1 pedido", response2.getErros().get(0));
        Response response3 = pedidoService.salvarPedido(GeradorDePedidos.gerarPedidosAleatorios(10));
        assertTrue(response3.getErros().isEmpty());
    }


    @Test
    @DisplayName("Testando numero de controle repetido")
    void testeNumeroDeControleRepetido() {
        Pedido pedido = new Pedido();
        pedido.setNumeroDeControle(0);
        Mockito.lenient().when(pedidoRepository.findByNumeroDeControle(0)).thenReturn(pedido);
        Response<PedidoCompletado> response = pedidoService.salvarPedido(GeradorDePedidos.gerarPedidosAleatorios(10));
        assertEquals("Numero de controle:0 repetido", response.getErros().get(0));
    }

    @Test
    @DisplayName("Testando o desconto")
    void testeValidarDesconto() {
        Mockito.lenient().when(pedidoRepository.findByNumeroDeControle(null)).thenReturn(null);

        Response<PedidoCompletado> response = pedidoService.salvarPedido(GeradorDePedidos.gerarPedidosAleatorios(10));
        BigDecimal valorTotal = new BigDecimal("49.4999999999999996946886682280819513835012912750244140625");
        assertEquals(valorTotal, response.getData().getValorTotal());

        valorTotal = new BigDecimal("19.94999999999999994171329120717928162775933742523193359375");
        Response<PedidoCompletado> response2 = pedidoService.salvarPedido(GeradorDePedidos.gerarPedidosAleatorios(6));
        assertEquals(valorTotal, response2.getData().getValorTotal());
    }


}
