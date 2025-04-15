package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.producers;

import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.PagamentoProcessarDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PagamentoSolicitadoProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public PagamentoSolicitadoProducer(
            RabbitTemplate rabbitTemplate,
            @Value("${mensageria.exchange.pagamento}") String exchange,
            @Value("${mensageria.routing-key.pagamento}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void send(PagamentoProcessarDto dto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, dto);
    }
}
