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
class BuscarPedidoPorIdHandlerTest {

    @Mock
    private PedidoGateway pedidoGateway;

    @InjectMocks
    private BuscarPedidoPorIdHandler buscarPedidoPorIdHandler;

    @Test
    void buscar_deveRetornarPedidoResponse_quandoPedidoExistir() {
        UUID pedidoId = UUID.randomUUID();
        Pedido pedidoMock = Pedido.builder()
                .id(pedidoId)
                .clienteId(UUID.randomUUID())
                .itens(List.of())
                .dadosPagamento(new DadosPagamento())
                .status(StatusPedido.ABERTO)
                .valorTotal(null)
                .dataCriacao(null)
                .build();
        PedidoResponse responseMock = PedidoResponse.fromEntity(pedidoMock);

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.of(pedidoMock));

        PedidoResponse response = buscarPedidoPorIdHandler.buscar(pedidoId);

        assertNotNull(response);
        verify(pedidoGateway, times(1)).buscarPorId(pedidoId);
    }

    @Test
    void buscar_deveLancarPedidoNotFoundException_quandoPedidoNaoExistir() {
        UUID pedidoId = UUID.randomUUID();

        when(pedidoGateway.buscarPorId(pedidoId)).thenReturn(Optional.empty());

        assertThrows(PedidoNotFoundException.class, () ->
                buscarPedidoPorIdHandler.buscar(pedidoId)
        );

        verify(pedidoGateway, times(1)).buscarPorId(pedidoId);
    }
}
