package com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoGateway {
    Pedido salvar(Pedido pedido);
    Optional<Pedido> buscarPorId(UUID id);

    List<Pedido> buscarPorCliente(UUID clienteId);
}