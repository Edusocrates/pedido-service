package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.listeners;

import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.EstoqueStatusDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstoqueStatusListenerTest {

    @Mock
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @InjectMocks
    private EstoqueStatusListener estoqueStatusListener;

    @Test
    void listen_deveAtualizarStatusPedidoComNovoStatus() {
        // Arrange
        UUID pedidoId = UUID.randomUUID();
        StatusPedido novoStatus = StatusPedido.FECHADO_SEM_ESTOQUE;
        EstoqueStatusDTO estoqueStatusDTO = new EstoqueStatusDTO(pedidoId, novoStatus);

        // Act
        estoqueStatusListener.listen(estoqueStatusDTO);

        // Assert
        verify(atualizarStatusPedidoUseCase, times(1)).atualizarStatus(pedidoId, novoStatus);
    }
}
