package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event;

import java.util.List;
import java.util.UUID;

public record PedidoCreatedDto(
        UUID pedidoId,
        List<ItemPedidoDto> itens,
        DadosPagamentoDto dadosPagamento
) {
    public record ItemPedidoDto(
            Long produtoId,
            Integer quantidade
    ) {}

    public record DadosPagamentoDto(
            String numeroCartao,
            String nomeTitular,
            String dataValidade,
            String cvv
    ) {}
}
