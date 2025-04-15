package com.fiap.rm358568.edusocrates.pedido_service.API.response;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.ItemPedido;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemPedidoResponse {
    private String sku;
    private int quantidade;
    private BigDecimal precoUnitario;

    public static ItemPedidoResponse fromEntity(ItemPedido item) {
        ItemPedidoResponse response = new ItemPedidoResponse();
        response.setSku(item.getSku());
        response.setQuantidade(item.getQuantidade());
        response.setPrecoUnitario(item.getPrecoUnitario());
        return response;
    }
}
