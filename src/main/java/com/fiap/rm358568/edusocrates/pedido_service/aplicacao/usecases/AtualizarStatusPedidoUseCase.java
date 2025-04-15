package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases;

import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;

import java.util.UUID;

public interface AtualizarStatusPedidoUseCase {
    PedidoResponse atualizarStatus(UUID pedidoId, StatusPedido novoStatus);
}
