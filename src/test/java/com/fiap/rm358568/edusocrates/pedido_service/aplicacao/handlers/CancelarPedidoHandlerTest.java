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
class CancelarPedidoHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private CancelarPedidoHandler cancelarPedidoHandler;

    @Test
    void cancelar_deveRetornarPedidoResponse_quandoPedidoAbertoExistir() {
        UUID pedidoId = UUID.randomUUID();
        Pedido pedidoMock = Pedido.builder()
                .id(pedidoId)
                .status(StatusPedido.ABERTO)
                .itens(List.of())
                .dadosPagamento(new DadosPagamento())
                .build();
        Pedido pedidoAtualizadoMock = Pedido.builder()
                .id(pedidoId)
                .status(StatusPedido.CANCELADO)
                .itens(List.of())
                .dadosPagamento(new DadosPagamento())
                .build();
        PedidoResponse responseMock = PedidoResponse.fromEntity(pedidoAtualizadoMock);

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.of(pedidoMock));
        when(pedidoGateway.salvar(pedidoMock)).thenReturn(pedidoAtualizadoMock);

        PedidoResponse response = cancelarPedidoHandler.cancelar(pedidoId);

        assertNotNull(response);
        verify(pedidoGateway, times(1)).buscarPorId(pedidoId);
        verify(pedidoGateway, times(1)).salvar(pedidoMock);
    }

    @Test
    void cancelar_deveLancarPedidoNotFoundException_quandoPedidoNaoExistir() {
        UUID pedidoId = UUID.randomUUID();

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () ->
                cancelarPedidoHandler.cancelar(pedidoId)
        );

        verify(pedidoGateway, times(1)).buscarPorId(pedidoId);
        verify(pedidoGateway, never()).salvar(any());
    }

    @Test
    void cancelar_deveLancarIllegalStateException_quandoPedidoNaoEstiverAberto() {
        UUID pedidoId = UUID.randomUUID();
        Pedido pedidoMock = Pedido.builder()
                .id(pedidoId)
                .status(StatusPedido.FECHADO_COM_SUCESSO)
                .itens(List.of())
                .dadosPagamento(new DadosPagamento())
                .build();

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.of(pedidoMock));

        assertThrows(IllegalStateException.class, () ->
                cancelarPedidoHandler.cancelar(pedidoId)
        );

        verify(pedidoGateway, times(1)).buscarPorId(pedidoId);
        verify(pedidoGateway, never()).salvar(any());
    }
}