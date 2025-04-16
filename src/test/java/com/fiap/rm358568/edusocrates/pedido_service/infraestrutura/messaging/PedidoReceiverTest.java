package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.rm358568.edusocrates.pedido_service.API.request.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.request.DadosPagamentoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.AtualizarStatusPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.CriarPedidoUseCase;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoReceiverTest {

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @Mock
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private PedidoReceiver pedidoReceiver;

    @Test
    void receberNovoPedido_deveChamarCriarPedidoUseCase_quandoMensagemValida() throws Exception {
        String message = "{\"clienteId\":\"123e4567-e89b-12d3-a456-426614174000\",\"valorTotal\":100.0,\"itens\":[],\"dadosPagamento\":{}}";
        CriarPedidoRequest requestMock = CriarPedidoRequest.builder()
                .clienteId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"))
                .valorTotal(BigDecimal.valueOf(100.0))
                .itens(List.of())
                .dadosPagamento(new DadosPagamentoRequest())
                .build();

        when(objectMapper.readValue(message, CriarPedidoRequest.class)).thenReturn(requestMock);

        pedidoReceiver.receberNovoPedido(message);

        verify(objectMapper, times(1)).readValue(message, CriarPedidoRequest.class);
        verify(criarPedidoUseCase, times(1)).criar(requestMock);
    }

    @Test
    void atualizarStatusPedido_deveChamarAtualizarStatusPedidoUseCase_quandoMensagemValida() throws Exception {
        String message = "{\"pedidoId\":\"123e4567-e89b-12d3-a456-426614174000\",\"status\":\"ENVIADO\"}";
        PedidoReceiver.PedidoStatusMessage statusMessageMock = new PedidoReceiver.PedidoStatusMessage();
        statusMessageMock.setPedidoId("123e4567-e89b-12d3-a456-426614174000");
        statusMessageMock.setStatus("ABERTO");

        when(objectMapper.readValue(message, PedidoReceiver.PedidoStatusMessage.class)).thenReturn(statusMessageMock);

        pedidoReceiver.atualizarStatusPedido(message);

        verify(objectMapper, times(1)).readValue(message, PedidoReceiver.PedidoStatusMessage.class);
        verify(atualizarStatusPedidoUseCase, times(1)).atualizarStatus(
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                StatusPedido.ABERTO
        );
    }

    @Test
    void receberNovoPedido_deveLancarIOException_quandoMensagemInvalida() throws Exception {
        String invalidMessage = "invalid json";

        doAnswer(invocation -> {
            throw new IOException("Erro de parsing");
        }).when(objectMapper).readValue(invalidMessage, CriarPedidoRequest.class);

        assertThrows(IOException.class, () -> pedidoReceiver.receberNovoPedido(invalidMessage));

        verify(objectMapper, times(1)).readValue(invalidMessage, CriarPedidoRequest.class);
        verifyNoInteractions(criarPedidoUseCase);
    }

    @Test
    void atualizarStatusPedido_deveLancarIOException_quandoMensagemInvalida() throws Exception {
        String invalidMessage = "invalid json";

        doAnswer(invocation -> {
            throw new IOException("Erro de parsing");
        }).when(objectMapper).readValue(invalidMessage, PedidoReceiver.PedidoStatusMessage.class);

        assertThrows(IOException.class, () -> pedidoReceiver.atualizarStatusPedido(invalidMessage));

        verify(objectMapper, times(1)).readValue(invalidMessage, PedidoReceiver.PedidoStatusMessage.class);
        verifyNoInteractions(atualizarStatusPedidoUseCase);
    }
}