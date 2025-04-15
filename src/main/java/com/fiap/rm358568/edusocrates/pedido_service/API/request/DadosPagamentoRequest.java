package com.fiap.rm358568.edusocrates.pedido_service.API.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class DadosPagamentoRequest {

    @NonNull
    private String numeroCartao;

    @NonNull
    private String nomeTitular;

    @NonNull
    private String dataValidade;

    @NonNull
    private String cvv;

    @Builder
    public DadosPagamentoRequest(String numeroCartao, String nomeTitular, String dataValidade, String cvv) {
        if (numeroCartao == null || numeroCartao.isBlank()) {
            throw new IllegalArgumentException("O número do cartão não pode ser nulo ou vazio.");
        }
        if (nomeTitular == null || nomeTitular.isBlank()) {
            throw new IllegalArgumentException("O nome do titular não pode ser nulo ou vazio.");
        }
        if (dataValidade == null || dataValidade.isBlank()) {
            throw new IllegalArgumentException("A data de validade não pode ser nula ou vazia.");
        }
        if (cvv == null || cvv.isBlank()) {
            throw new IllegalArgumentException("O CVV não pode ser nulo ou vazio.");
        }
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }
}
