package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.ListarPedidosPorClienteUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways.PedidoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class ListarPedidosPorClienteHandler implements ListarPedidosPorClienteUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    public List<PedidoResponse> listar(UUID clienteId) {
        return pedidoGateway.buscarPorCliente(clienteId)
                .stream()
                .map(PedidoResponse::fromEntity)
                .collect(Collectors.toList());
    }
}