package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.producers;

import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging.event.PagamentoProcessarDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PagamentoSolicitadoProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private PagamentoSolicitadoProducer pagamentoSolicitadoProducer;

    @Test
    void send_deveEnviarMensagemComExchangeERoutingKey() {
        // Arrange
        String exchange = "test-exchange";
        String routingKey = "test-routing-key";
        PagamentoProcessarDto dto = new PagamentoProcessarDto(UUID.randomUUID(),"test-pedido-id", "TESTE", "test-transaction-id","123");

        // Simula valores de propriedades
        PagamentoSolicitadoProducer producer = new PagamentoSolicitadoProducer(rabbitTemplate, exchange, routingKey);

        // Act
        producer.send(dto);

        // Assert
        verify(rabbitTemplate, times(1)).convertAndSend(exchange, routingKey, dto);
    }
}
