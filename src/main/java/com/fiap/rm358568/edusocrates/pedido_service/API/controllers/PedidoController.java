package com.fiap.rm358568.edusocrates.pedido_service.API.controllers;

import com.fiap.rm358568.edusocrates.pedido_service.API.request.CriarPedidoRequest;
import com.fiap.rm358568.edusocrates.pedido_service.API.response.PedidoResponse;
import com.fiap.rm358568.edusocrates.pedido_service.aplicacao.usecases.*;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Configurações Pedidos", description = "Operações relacionadas aos pedidos!")
public class PedidoController {

    private final CriarPedidoUseCase criarPedidoUseCase;
    private final CancelarPedidoUseCase cancelarPedidoUseCase;
    private final AtualizarStatusPedidoUseCase atualizarStatusPedidoUseCase;
    private final BuscarPedidoPorIdUseCase buscarPedidoPorIdUseCase;
    private final ListarPedidosPorClienteUseCase listarPedidosPorClienteUseCase;

    @GetMapping("/{id}")
    @Tag(name = "Consultar pedido", description = "consultar um pedido por ID")
    @Operation(summary = "Consultar pedido", description = "Consultar um pedido por ID")
    public ResponseEntity<PedidoResponse> buscarPedidoPorId(@PathVariable UUID id) {
        log.info("Consulta Recebida! Buscando pedido com ID: {}", id);
        PedidoResponse response = buscarPedidoPorIdUseCase.buscar(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/cliente")
    @Tag(name = "Consultar pedidos por cliente", description = "consultar pedidos por cliente")
    @Operation(summary = "Consultar pedidos por cliente", description = "Consultar pedidos por cliente")
    public ResponseEntity<List<PedidoResponse>> listarPedidosPorCliente(@RequestParam("clienteId") UUID clienteId) {
        log.info("Listagem Recebida! Listando pedidos do cliente com ID: {}", clienteId);
        List<PedidoResponse> response = listarPedidosPorClienteUseCase.listar(clienteId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Tag(name = "Criar pedido", description = "Criar um novo pedido")
    @Operation(summary = "Criar pedido", description = "Criar um novo pedido")
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody CriarPedidoRequest request) {
        log.info("Criação Recebida! Criando pedido com os dados: {}", request);
        PedidoResponse response = criarPedidoUseCase.criar(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/cancelar")
    @Tag(name = "Cancelar pedido", description = "Cancelar um pedido")
    @Operation(summary = "Cancelar pedido", description = "Cancelar um pedido")
    public ResponseEntity<PedidoResponse> cancelarPedido(@PathVariable UUID id) {
        log.info("Cancelamento Recebido! Cancelando pedido com ID: {}", id);
        PedidoResponse response = cancelarPedidoUseCase.cancelar(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/status")
    @Tag(name = "Atualizar status do pedido", description = "Atualizar o status de um pedido")
    @Operation(summary = "Atualizar status do pedido", description = "Atualizar o status de um pedido")
    public ResponseEntity<PedidoResponse> atualizarStatus(
            @PathVariable UUID id,
            @RequestParam("status") String novoStatus
    ) {
        log.info("Atualização Recebida! Atualizando status do pedido com ID: {} para {}", id, novoStatus);
        PedidoResponse response = atualizarStatusPedidoUseCase.atualizarStatus(id, Enum.valueOf(StatusPedido.class, novoStatus));
        return ResponseEntity.ok(response);
    }
}
