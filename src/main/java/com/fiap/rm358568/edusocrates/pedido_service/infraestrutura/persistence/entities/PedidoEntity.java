package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.entities;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "cliente_id", nullable = false)
    private UUID clienteId;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemPedidoEntity> itens = new ArrayList<>();

    @Embedded
    private DadosPagamentoEntity dadosPagamento;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    public void adicionarItem(ItemPedidoEntity item) {
        item.setPedido(this);
        this.itens.add(item);
    }
}
