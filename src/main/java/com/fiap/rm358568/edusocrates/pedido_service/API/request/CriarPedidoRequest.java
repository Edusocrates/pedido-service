package com.fiap.rm358568.edusocrates.pedido_service.API.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriarPedidoRequest {

    @NonNull
    private UUID clienteId;

    @NonNull
    private List<ItemPedidoRequest> itens;

    @NonNull
    private DadosPagamentoRequest dadosPagamento;

    @NonNull
    private BigDecimal valorTotal;

}