package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.producers;

import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.EstoqueProcessarPedidoDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoCreatedProducer {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange;
    private final String routingKey;

    public PedidoCreatedProducer(
            RabbitTemplate rabbitTemplate,
            @Value("${rabbitmq.pedido.created.exchange}") String exchange,
            @Value("${rabbitmq.pedido.created.routing-key}") String routingKey
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void send(EstoqueProcessarPedidoDto dto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, dto);
    }
}