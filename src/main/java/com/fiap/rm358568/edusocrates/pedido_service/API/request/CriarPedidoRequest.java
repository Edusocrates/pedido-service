package com.fiap.rm358568.edusocrates.pedido_service.API.request;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class CriarPedidoRequest {

    @NonNull
    private UUID clienteId;

    @NonNull
    private List<ItemPedidoRequest> itens;

    @NonNull
    private DadosPagamentoRequest dadosPagamento;

    @Builder
    public CriarPedidoRequest(UUID clienteId, List<ItemPedidoRequest> itens, DadosPagamentoRequest dadosPagamento) {
        if (clienteId == null) {
            throw new IllegalArgumentException("O clienteId não pode ser nulo.");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("A lista de itens não pode ser nula ou vazia.");
        }
        if (dadosPagamento == null) {
            throw new IllegalArgumentException("Os dados de pagamento não podem ser nulos.");
        }
        this.clienteId = clienteId;
        this.itens = itens;
        this.dadosPagamento = dadosPagamento;
    }
}