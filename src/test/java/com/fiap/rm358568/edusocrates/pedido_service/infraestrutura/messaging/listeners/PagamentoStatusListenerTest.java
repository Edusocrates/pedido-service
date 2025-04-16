package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.listeners;

import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.PagamentoStatusDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoStatusListenerTest {

    @Mock
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @InjectMocks
    private PagamentoStatusListener pagamentoStatusListener;

    @Test
    void listen_deveAtualizarStatusPedidoComNovoStatus() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        StatusPedido novoStatus = StatusPedido.FECHADO_COM_SUCESSO;
        PagamentoStatusDTO pagamentoStatusDTO = new PagamentoStatusDTO(pedidoId, novoStatus);

        // Act
        pagamentoStatusListener.listen(pagamentoStatusDTO);

        // Assert
        verify(atualizarStatusPedidoUseCase, times(1)).atualizarStatus(pedidoId, novoStatus);
    }
}