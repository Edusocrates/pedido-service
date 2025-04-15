package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.listeners;

import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.config.RabbitMqConfig;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.PagamentoStatusDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoStatusListener {

    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    public PagamentoStatusListener(AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase) {
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    @RabbitListener(queues = RabbitMqConfig.PAGAMENTO_STATUS_QUEUE)
    public void listen(PagamentoStatusDTO pagamentoStatusDTO) {
        // Passamos diretamente o status do pagamento
        var novoStatus = pagamentoStatusDTO.status();

        // Atualizamos o status do pedido com o novo status vindo do pagamento
        atualizarStatusPedidoUseCase.atualizarStatus(pagamentoStatusDTO.pedidoId(), novoStatus);
    }
}