package com.fiap.rm358568.edusocrates.pedido_service.dominio.entities;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private UUID id;
    private UUID clienteId;
    private List<ItemPedido> itens;
    private DadosPagamento dadosPagamento;
    private StatusPedido status;
    private BigDecimal valorTotal;
    private LocalDateTime dataCriacao;

    public Pedido(UUID clienteId, List<ItemPedido> itens, DadosPagamento dadosPagamento) {
        this.id = UUID.randomUUID();
        this.clienteId = clienteId;
        this.itens = itens;
        this.dadosPagamento = dadosPagamento;
        this.status = StatusPedido.ABERTO;
        this.dataCriacao = LocalDateTime.now();
        this.calcularValorTotal();
    }

    private void calcularValorTotal() {
        this.valorTotal = itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void fecharComSucesso() {
        this.status = StatusPedido.FECHADO_COM_SUCESSO;
    }

    public void fecharSemEstoque() {
        this.status = StatusPedido.FECHADO_SEM_ESTOQUE;
    }

    public void fecharSemCredito() {
        this.status = StatusPedido.FECHADO_SEM_CREDITO;
    }

    //comportamentos adicionais omitidos para foco
    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        this.calcularValorTotal();
    }

    public void removerItem(ItemPedido item) {
        this.itens.remove(item);
        this.calcularValorTotal();
    }


}
