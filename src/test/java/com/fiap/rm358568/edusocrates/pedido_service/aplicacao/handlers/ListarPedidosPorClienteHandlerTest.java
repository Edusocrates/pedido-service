package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.request.DadosPagamentoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.request.ItemPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.ItemPedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListarPedidosPorClienteHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private ListarPedidosPorClienteHandler listarPedidosPorClienteHandler;

    @Test
    void listar_deveRetornarListaDePedidoResponse_quandoPedidosExistirem() {
        UUID clienteId = UUID.randomUUID();
        Pedido pedidoMock = Pedido.builder()
                .id(UUID.randomUUID())
                .clienteId(clienteId)
                .status(StatusPedido.ABERTO)
                .valorTotal(BigDecimal.valueOf(100.0))
                .itens(List.of(
                        ItemPedido.builder()
                                .sku("123")
                                .quantidade(2)
                                .precoUnitario(BigDecimal.valueOf(50.0))
                                .build()
                ))
                .dadosPagamento(DadosPagamento.builder()
                        .numeroCartao("1234567890123456")
                        .nomeTitular("Cliente Teste")
                        .dataValidade("12/25")
                        .cvv("123")
                        .build())
                .build();
        List<Pedido> pedidosMock = List.of(pedidoMock);

        when(pedidoGateway.buscarPorCliente(clienteId)).thenReturn(pedidosMock);

        List<PedidoResponse> responses = listarPedidosPorClienteHandler.listar(clienteId);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(pedidoMock.getId(), responses.get(0).getId());
        verify(pedidoGateway, times(1)).buscarPorCliente(clienteId);
    }

    @Test
    void listar_deveRetornarListaVazia_quandoNenhumPedidoExistir() {
        UUID clienteId = UUID.randomUUID();

        when(pedidoGateway.buscarPorCliente(clienteId)).thenReturn(List.of());

        List<PedidoResponse> responses = listarPedidosPorClienteHandler.listar(clienteId);

        assertNotNull(responses);
        assertTrue(responses.isEmpty());
        verify(pedidoGateway, times(1)).buscarPorCliente(clienteId);
    }
}