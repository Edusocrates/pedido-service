package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.rm358568.edusocrates.pedido_service.API.request.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.CriarPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PedidoReceiver {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "pedido.created")
    public void receberNovoPedido(String message) throws IOException {
        CriarPedidoRequest request = objectMapper.readValue(message, CriarPedidoRequest.class);
        criarPedidoUseCase.criar(request);
    }

    @RabbitListener(queues = "pedido.atualizar.status")
    public void atualizarStatusPedido(String message) throws IOException {
        // Exemplo de mensagem recebida: {"pedidoId":"uuid","status":"ENVIADO"}
        PedidoStatusMessage statusMessage = objectMapper.readValue(message, PedidoStatusMessage.class);
        atualizarStatusPedidoUseCase.atualizarStatus(
                UUID.fromString(statusMessage.getPedidoId()),
                StatusPedido.valueOf(statusMessage.getStatus())
        );
    }

    // Classe auxiliar interna ou externa para deserializar mensagens de status
    public static class PedidoStatusMessage {
        private String pedidoId;
        private String status;

        // Getters e Setters
        public String getPedidoId() { return pedidoId; }
        public void setPedidoId(String pedidoId) { this.pedidoId = pedidoId; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
