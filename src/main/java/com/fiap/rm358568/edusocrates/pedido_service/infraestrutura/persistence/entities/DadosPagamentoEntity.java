package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosPagamentoEntity {

    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;
    private String cvv;
}
