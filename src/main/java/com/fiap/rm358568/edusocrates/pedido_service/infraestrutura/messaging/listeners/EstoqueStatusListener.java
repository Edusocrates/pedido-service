package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.listeners;


import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.config.RabbitMqConfig;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.EstoqueStatusDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueStatusListener {

    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    public EstoqueStatusListener(AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase) {
        this.atualizarStatusPedidoUseCase = atualizarStatusPedidoUseCase;
    }

    @RabbitListener(queues = RabbitMqConfig.ESTOQUE_STATUS_QUEUE)
    public void listen(EstoqueStatusDTO estoqueStatusDTO) {
        // Passamos diretamente o status do estoque
        var novoStatus = estoqueStatusDTO.status();

        // Atualizamos o status do pedido com o novo status vindo do estoque
        atualizarStatusPedidoUseCase.atualizarStatus(estoqueStatusDTO.pedidoId(), novoStatus);
    }
}
