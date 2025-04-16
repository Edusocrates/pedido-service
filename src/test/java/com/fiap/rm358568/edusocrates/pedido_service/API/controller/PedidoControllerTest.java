package com.fiap.rm358568.edusocrates.pedido_service.API.controller;

import com.fiap.rm358568.edusocrates.pedido_service.API.controllers.PedidoController;
import com.fiap.rm358568.edusocrates.pedido_service.API.request.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.request.DadosPagamentoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.response.DadosPagamentoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.*;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private CriarPedidoUseCase criarPedidoUseCase;

    @Mock
    private CancelarPedidoUseCase cancelarPedidoUseCase;

    @Mock
    private AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;

    @Mock
    private BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;

    @Mock
    private ListarPedidosPorClienteUseCase listarPedidosPorClienteUseCase;

    @InjectMocks
    private PedidoController pedidoController;

    @Test
    void criarPedido_deveRetornarPedidoResponse_quandoPedidoForCriado() {
        CriarPedidoRequest request = new CriarPedidoRequest(UUID.randomUUID(), List.of(), new DadosPagamentoRequest(), BigDecimal.valueOf(123.45));
        PedidoResponse responseMock = new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),List.of(), new DadosPagamentoResponse(), "CRIADO",  BigDecimal.valueOf(123.45), LocalDateTime.now());

        when(criarPedidoUseCase.criar(request)).thenReturn(responseMock);

        ResponseEntity<PedidoResponse> response = pedidoController.criarPedido(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void buscarPedidoPorId_deveRetornarPedidoResponse_quandoPedidoExistir() {
        UUID id = UUID.randomUUID();
        PedidoResponse responseMock = new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),List.of(), new DadosPagamentoResponse(), "CRIADO",  BigDecimal.valueOf(123.45), LocalDateTime.now());

        when(buscarPedidoPorIdUseCase.buscar(id)).thenReturn(responseMock);

        ResponseEntity<PedidoResponse> response = pedidoController.buscarPedidoPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void listarPedidosPorCliente_deveRetornarListaDePedidos_quandoPedidosExistirem() {
        UUID clienteId = UUID.randomUUID();
        PedidoResponse responseMock = new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),List.of(), new DadosPagamentoResponse(), "CRIADO",  BigDecimal.valueOf(123.45), LocalDateTime.now());

        when(listarPedidosPorClienteUseCase.listar(clienteId)).thenReturn(List.of(responseMock));

        ResponseEntity<List<PedidoResponse>> response = pedidoController.listarPedidosPorCliente(clienteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(responseMock, response.getBody().get(0));
    }

    @Test
    void cancelarPedido_deveRetornarPedidoResponse_quandoPedidoForCancelado() {
        UUID id = UUID.randomUUID();
        PedidoResponse responseMock = new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),List.of(), new DadosPagamentoResponse(), "CRIADO",  BigDecimal.valueOf(123.45), LocalDateTime.now());

        when(cancelarPedidoUseCase.cancelar(id)).thenReturn(responseMock);

        ResponseEntity<PedidoResponse> response = pedidoController.cancelarPedido(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }

    @Test
    void atualizarStatus_deveRetornarPedidoResponse_quandoStatusForAtualizado() {
        UUID id = UUID.randomUUID();
        String novoStatus = "ABERTO";
        PedidoResponse responseMock = new PedidoResponse(UUID.randomUUID(), UUID.randomUUID(),List.of(), new DadosPagamentoResponse(), "CRIADO",  BigDecimal.valueOf(123.45), LocalDateTime.now());

        when(atualizarStatusPedidoUseCase.atualizarStatus(id, StatusPedido.valueOf(novoStatus))).thenReturn(responseMock);

        ResponseEntity<PedidoResponse> response = pedidoController.atualizarStatus(id, novoStatus);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMock, response.getBody());
    }
}
