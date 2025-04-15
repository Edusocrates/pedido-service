package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;

import java.util.UUID;

public record PagamentoStatusDTO(
        UUID pedidoId,
        StatusPedido status
) { }
