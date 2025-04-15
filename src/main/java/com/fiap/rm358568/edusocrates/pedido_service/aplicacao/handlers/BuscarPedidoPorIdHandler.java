package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.BuscarPedidoPorIdUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class BuscarPedidoPorIdHandler implements BuscarPedidoPorIdUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    public PedidoResponse buscar(UUID pedidoId) {
        log.info("Buscando pedido com ID: {}", pedidoId);
        Pedido pedido = pedidoGateway.buscarPorId(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado!"));
        log.info("Pedido encontrado: {}", pedido);
        return PedidoResponse.fromEntity(pedido);
    }
}

