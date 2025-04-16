package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.request.CriarPedidoRequest;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarPedidoHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private CriarPedidoHandler criarPedidoHandler;

    @Test
    void criar_deveRetornarPedidoResponse_quandoRequestValida() {
        CriarPedidoRequest request = CriarPedidoRequest.builder()
                .clienteId(UUID.randomUUID())
                .valorTotal(BigDecimal.valueOf(100.0))
                .itens(List.of(
                        ItemPedidoRequest.builder()
                                .sku("123")
                                .quantidade(2)
                                .precoUnitario(BigDecimal.valueOf(50.0))
                                .build()
                ))
                .dadosPagamento(DadosPagamentoRequest.builder()
                        .numeroCartao("1234567890123456")
                        .nomeTitular("Cliente Teste")
                        .dataValidade("12/25")
                        .cvv("123")
                        .build())
                .build();

        Pedido pedidoMock = Pedido.builder()
                .id(UUID.randomUUID())
                .clienteId(request.getClienteId())
                .status(StatusPedido.ABERTO)
                .valorTotal(request.getValorTotal())
                .dataCriacao(LocalDateTime.now())
                .itens(List.of(new ItemPedido("123", 2, BigDecimal.valueOf(200.0))))
                .dadosPagamento(new DadosPagamento("1234567890123456", "Cliente Teste", "12/25", "123"))
                .build();

        when(pedidoGateway.salvar(any(Pedido.class))).thenReturn(pedidoMock);

        PedidoResponse response = criarPedidoHandler.criar(request);

        assertNotNull(response);
        assertEquals(pedidoMock.getId(), response.getId());
        assertEquals(pedidoMock.getStatus().name(), response.getStatus());
        verify(pedidoGateway, times(1)).salvar(any(Pedido.class));
    }



}