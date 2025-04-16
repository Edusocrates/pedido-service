package com.fiap.rm358568.edusocrates.pedido_service.API.response;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class PedidoResponse {
    private UUID id;
    private UUID clienteId;
    private List<ItemPedidoResponse> itens;
    private DadosPagamentoResponse dadosPagamento;
    private String status;
    private BigDecimal valorTotal;
    private LocalDateTime criadoEm;

    public static PedidoResponse fromEntity(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());
        response.setClienteId(pedido.getClienteId());
        response.setItens(pedido.getItens().stream().map(ItemPedidoResponse::fromEntity).collect(Collectors.toList()));
        response.setDadosPagamento(DadosPagamentoResponse.fromEntity(pedido.getDadosPagamento()));
        response.setStatus(pedido.getStatus().name());
        response.setValorTotal(pedido.getValorTotal());
        response.setCriadoEm(pedido.getDataCriacao());
        return response;
    }
}
