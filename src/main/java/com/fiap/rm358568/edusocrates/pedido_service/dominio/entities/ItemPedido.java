package com.fiap.rm358568.edusocrates.pedido_service.dominio.entities;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedido {
    private UUID id;
    private String sku;
    private int quantidade;
    private BigDecimal precoUnitario;

    public ItemPedido(String sku, int quantidade, BigDecimal precoUnitario) {
        this.id = UUID.randomUUID();
        this.sku = sku;
        setQuantidade(quantidade);
        setPrecoUnitario(precoUnitario);
    }

    public BigDecimal getSubtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

    public void setQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        if (precoUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço unitário deve ser maior que zero.");
        }
        this.precoUnitario = precoUnitario;
    }

    public void adicionarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }
}
