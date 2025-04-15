package com.fiap.rm358568.edusocrates.pedido_service.API.response;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.DadosPagamento;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosPagamentoResponse {
    private String numeroCartao;
    private String nomeTitular;
    private String dataValidade;

    public static DadosPagamentoResponse fromEntity(DadosPagamento dados) {
        DadosPagamentoResponse response = new DadosPagamentoResponse();
        response.setNumeroCartao(dados.getNumeroCartao());
        response.setNomeTitular(dados.getNomeTitular());
        response.setDataValidade(dados.getDataValidade());
        return response;
    }
}
