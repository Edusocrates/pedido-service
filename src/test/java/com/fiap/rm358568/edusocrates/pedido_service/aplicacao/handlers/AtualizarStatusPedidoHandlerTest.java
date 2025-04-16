package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.exceptions.PedidoNotFoundException;
import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways.PedidoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AtualizarStatusPedidoHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private AtualizarStatusPedidoHandler atualizarStatusPedidoHandler;

    @Test
    void atualizarStatus_deveRetornarPedidoResponse_quandoPedidoExistir() {
        UUID pedidoId = UUID.randomUUID();
        StatusPedido novoStatus = StatusPedido.ABERTO;
        Pedido pedidoMock = Pedido.builder()
                .id(pedidoId)
                .status(StatusPedido.ABERTO)
                .itens(List.of()) // Inicializa a lista de itens como vazia
                .dadosPagamento(new DadosPagamento())
                .build();
        Pedido pedidoAtualizadoMock = Pedido.builder()
                .id(pedidoId)
                .status(novoStatus)
                .itens(List.of()) // Inicializa a lista de itens como vazia
                .dadosPagamento(new DadosPagamento())
                .build();
        PedidoResponse responseMock = PedidoResponse.fromEntity(pedidoAtualizadoMock);

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.of(pedidoMock));
        when(pedidoGateway.salvar(pedidoMock)).thenReturn(pedidoAtualizadoMock);

        PedidoResponse response = atualizarStatusPedidoHandler.atualizarStatus(pedidoId, novoStatus);

        assertNotNull( response);
        verify(pedidoGateway, times(1)).buscarPorId(pedidoId);
        verify(pedidoGateway, times(1)).salvar(pedidoMock);
    }

    @Test
    void atualizarStatus_deveLancarPedidoNotFoundException_quandoPedidoNaoExistir() {
        UUID pedidoId = UUID.randomUUID();
        StatusPedido novoStatus = StatusPedido.ABERTO;

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () ->
                atualizarStatusPedidoHandler.atualizarStatus(pedidoId, novoStatus)
        );

        verify(pedidoGateway, times(1)).buscarPorId(pedidoId);
        verify(pedidoGateway, never()).salvar(any());
    }
}