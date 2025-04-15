package com.fiap.rm358568.edusocrates.pedido_service.API.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ItemPedidoRequest {

    @NonNull
    private String sku;

    private int quantidade;

    @NonNull
    private BigDecimal precoUnitario;

    @Builder
    public ItemPedidoRequest(String sku, int quantidade, BigDecimal precoUnitario) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("O SKU não pode ser nulo ou vazio.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        if (precoUnitario == null || precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }
        this.sku = sku;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }
}