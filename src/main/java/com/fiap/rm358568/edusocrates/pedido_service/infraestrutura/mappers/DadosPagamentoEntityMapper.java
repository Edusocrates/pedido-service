package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.mappers;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.DadosPagamento;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.entities.DadosPagamentoEntity;
import org.springframework.stereotype.Component;

@Component
public class DadosPagamentoEntityMapper {

    public static DadosPagamentoEntity toEntity(DadosPagamento dados) {
        return DadosPagamentoEntity.builder()
                .numeroCartao(dados.getNumeroCartao())
                .nomeTitular(dados.getNomeTitular())
                .dataValidade(dados.getDataValidade())
                .cvv(dados.getCvv())
                .build();
    }

    public static DadosPagamento toDomain(DadosPagamentoEntity entity) {
        return new DadosPagamento(
                entity.getNumeroCartao(),
                entity.getNomeTitular(),
                entity.getDataValidade(),
                entity.getCvv()
        );
    }
}