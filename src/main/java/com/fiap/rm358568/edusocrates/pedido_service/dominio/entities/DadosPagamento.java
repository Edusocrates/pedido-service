package com.fiap.rm358568.edusocrates.pedido_service.dominio.entities;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DadosPagamento {

    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;
    private String cvv;

}
