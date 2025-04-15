package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event;

import java.util.UUID;

public record PagamentoProcessarDto(
        UUID pedidoId,
        String numeroCartao,
        String nomeTitular,
        String dataValidade,
        String cvv
) {}
