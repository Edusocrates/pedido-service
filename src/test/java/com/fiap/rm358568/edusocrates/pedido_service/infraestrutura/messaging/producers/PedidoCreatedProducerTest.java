package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.producers;

import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.EstoqueProcessarPedidoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoCreatedProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PedidoCreatedProducer pedidoCreatedProducer;

    @Test
    void send_deveEnviarMensagemComExchangeERoutingKey() {
        // Arrange
        String exchange = "test-exchange";
        String routingKey = "test-routing-key";
        EstoqueProcessarPedidoDto dto = new EstoqueProcessarPedidoDto(UUID.randomUUID(), List.of());

        // Simula valores de propriedades
        PedidoCreatedProducer producer = new PedidoCreatedProducer(rabbitTemplate, exchange, routingKey);

        // Act
        producer.send(dto);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(exchange, routingKey, dto);
    }
}
