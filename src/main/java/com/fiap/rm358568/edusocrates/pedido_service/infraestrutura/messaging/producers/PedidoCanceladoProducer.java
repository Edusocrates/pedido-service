package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.producers;

import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoCanceladoProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public PedidoCanceladoProducer(
            RabbitTemplate rabbitTemplate,
            @Value("${rabbitmq.pedido.cancelado.exchange}") String exchange,
            @Value("${rabbitmq.pedido.cancelado.routing-key}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void send(UUID pedidoId) {
        rabbitTemplate.convertAndSend(exchange, routingKey, pedidoId);
    }
}
