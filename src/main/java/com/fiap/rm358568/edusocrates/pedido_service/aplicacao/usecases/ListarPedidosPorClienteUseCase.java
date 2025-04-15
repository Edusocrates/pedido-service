package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;

import java.util.List;
import java.util.UUID;

public interface ListarPedidosPorClienteUseCase {
    List<PedidoResponse> listar(UUID clienteId);
}
