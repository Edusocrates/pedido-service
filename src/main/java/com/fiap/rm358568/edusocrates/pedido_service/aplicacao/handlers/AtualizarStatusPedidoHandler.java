package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class AtualizarStatusPedidoHandler implements AtualizarStatusPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    public PedidoResponse atualizarStatus(UUID pedidoId, StatusPedido novoStatus) {
        log.info("Atualizando status do pedido com ID: {}", pedidoId);
        Pedido pedido = pedidoGateway.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));

        pedido.setStatus(novoStatus);
        Pedido atualizado = pedidoGateway.salvar(pedido);

        log.info("Status do pedido com ID: {} atualizado para: {}", pedidoId, novoStatus);
        return PedidoResponse.fromEntity(atualizado);
    }
}