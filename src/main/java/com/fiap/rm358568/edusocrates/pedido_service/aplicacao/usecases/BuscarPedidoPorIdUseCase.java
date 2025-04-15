package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;

import java.util.UUID;

public interface BuscarPedidoPorIdUseCase {
    PedidoResponse buscar(UUID pedidoId);
}
