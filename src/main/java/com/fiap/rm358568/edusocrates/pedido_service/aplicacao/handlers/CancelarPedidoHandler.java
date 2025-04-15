package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.CancelarPedidoUseCase;
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
public class CancelarPedidoHandler implements CancelarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    public PedidoResponse cancelar(UUID pedidoId) {
        log.info("Cancelando pedido com ID: {}", pedidoId);
        Pedido pedido = pedidoGateway.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado!"));

        if (!pedido.getStatus().equals(StatusPedido.ABERTO)) {
            throw new IllegalStateException("Somente pedidos com status ABERTO podem ser cancelados!");
        }

        pedido.setStatus(StatusPedido.CANCELADO);
        Pedido atualizado = pedidoGateway.salvar(pedido);

        log.info("Pedido com ID: {} cancelado com sucesso!", pedidoId);
        return PedidoResponse.fromEntity(atualizado);
    }
}