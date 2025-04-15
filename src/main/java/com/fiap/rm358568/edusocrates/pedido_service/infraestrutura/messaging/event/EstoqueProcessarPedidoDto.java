package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event;

import java.util.List;
import java.util.UUID;

public record EstoqueProcessarPedidoDto(
        UUID pedidoId,
        List<ItemEstoqueDto> itens
) {
    public record ItemEstoqueDto(
            Long produtoId,
            Integer quantidade
    ) {}
}
