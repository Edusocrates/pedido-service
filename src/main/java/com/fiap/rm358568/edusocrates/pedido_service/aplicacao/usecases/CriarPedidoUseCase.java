package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases;


import com.fiap.rm358568.edusocrates.pedido_service.API.request.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;


public interface CriarPedidoUseCase {
    PedidoResponse criar(CriarPedidoRequest request);
}