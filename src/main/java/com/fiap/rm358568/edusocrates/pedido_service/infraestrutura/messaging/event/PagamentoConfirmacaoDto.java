package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event;

import java.util.UUID;

public record PagamentoConfirmacaoDto(
        UUID pedidoId,
        boolean sucesso,
        String mensagem
) {}