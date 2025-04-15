package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.mappers;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.entities.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoEntityMapper {

    public static PedidoEntity toEntity(Pedido pedido) {
        return PedidoEntity.builder()
                .id(pedido.getId())
                .clienteId(pedido.getClienteId())
                .status(pedido.getStatus())
                .valorTotal(pedido.getValorTotal())
                .dataCriacao(pedido.getDataCriacao())
                .dadosPagamento(DadosPagamentoEntityMapper.toEntity(pedido.getDadosPagamento()))
                .itens(pedido.getItens().stream().map(ItemPedidoEntityMapper::toEntity).collect(Collectors.toList()))
                .build();
    }

    public Pedido toDomain(PedidoEntity entity) {
        return Pedido.builder()
                .id(entity.getId())
                .clienteId(entity.getClienteId())
                .status(entity.getStatus())
                .valorTotal(entity.getValorTotal())
                .dataCriacao(entity.getDataCriacao())
                .dadosPagamento(DadosPagamentoEntityMapper.toDomain(entity.getDadosPagamento()))
                .itens(entity.getItens().stream().map(ItemPedidoEntityMapper::toDomain).collect(Collectors.toList()))
                .build();
    }
}
