package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.producers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoCanceladoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PedidoCanceladoProducer pedidoCanceladoProducer;

    @Test
    void send_deveEnviarMensagemComExchangeERoutingKey() {
        // Arrange
        String exchange = "test-exchange";
        String routingKey = "test-routing-key";
        UUID pedidoId = UUID.randomUUID();

        // Simula valores de propriedades
        PedidoCanceladoProducer producer = new PedidoCanceladoProducer(rabbitTemplate, exchange, routingKey);

        // Act
        producer.send(pedidoId);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(exchange, routingKey, pedidoId);
    }
}
