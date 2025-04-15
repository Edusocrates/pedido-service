package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.mappers;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.ItemPedido;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.entities.ItemPedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoEntityMapper {

    public static ItemPedidoEntity toEntity(ItemPedido item) {
        return ItemPedidoEntity.builder()
                .id(item.getId())
                .sku(item.getSku())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitario())
                .build();
    }

    public static ItemPedido toDomain(ItemPedidoEntity entity) {
        return ItemPedido.builder()
                .id(entity.getId())
                .sku(entity.getSku())
                .quantidade(entity.getQuantidade())
                .precoUnitario(entity.getPrecoUnitario())
                .build();
    }
}
