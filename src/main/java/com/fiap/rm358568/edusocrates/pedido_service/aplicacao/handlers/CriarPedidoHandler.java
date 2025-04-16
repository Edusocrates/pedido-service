package com.fiap.rm358568.edusocrates.pedido_service.aplicacao.handlers;

import com.fiap.rm358568.edusocrates.pedido_service.API.request.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.CriarPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.ItemPedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways.PedidoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class CriarPedidoHandler implements CriarPedidoUseCase {

    private final PedidoGateway pedidoGateway;

    @Override
    @Transactional
    public PedidoResponse criar(CriarPedidoRequest request) {
        log.info("Criando pedido para o cliente com ID: {}", request.getClienteId());

        Pedido pedido = new Pedido();
        pedido.setClienteId(request.getClienteId());
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setValorTotal(request.getValorTotal());
        pedido.setDataCriacao(LocalDateTime.now());

        List<ItemPedido> itens = request.getItens().stream().map(itemRequest -> {
            return new ItemPedido(
                    itemRequest.getSku(),
                    itemRequest.getQuantidade(),
                    itemRequest.getPrecoUnitario()
            );
        }).collect(Collectors.toList());

        pedido.setItens(itens);

        DadosPagamento dadosPagamento = new DadosPagamento(
                request.getDadosPagamento().getNumeroCartao(),
                request.getDadosPagamento().getNomeTitular(),
                request.getDadosPagamento().getDataValidade(),
                request.getDadosPagamento().getCvv()
        );
        pedido.setDadosPagamento(dadosPagamento);

        Pedido salvo = pedidoGateway.salvar(pedido);
        return PedidoResponse.fromEntity(salvo);
    }
}