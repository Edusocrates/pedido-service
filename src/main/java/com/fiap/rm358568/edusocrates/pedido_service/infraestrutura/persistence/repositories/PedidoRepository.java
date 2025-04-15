package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.repositories;

import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface de repositório para a entidade Pedido.
 * Extende JpaRepository para fornecer operações CRUD básicas.
 */
@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, UUID> {
    Optional<PedidoEntity> findAllByClienteId(UUID clienteId);
}
