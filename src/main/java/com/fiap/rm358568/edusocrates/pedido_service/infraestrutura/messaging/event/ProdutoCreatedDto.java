package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event;

public record ProdutoCreatedDto(
        Long id,
        String nome,
        String descricao,
        Double preco
) {}
