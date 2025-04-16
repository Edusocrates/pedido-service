package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.gateways;

import com.fiap.rm358568.edusocrates.pedido_service.dominio.entities.Pedido;
import com.fiap.rm358568.edusocrates.pedido_service.dominio.gateways.PedidoGateway;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.mappers.PedidoEntityMapper;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.entities.PedidoEntity;
import com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.persistence.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoRepositoryGateway implements PedidoGateway {

    private final PedidoRepository repository;
    private final PedidoEntityMapper pedidoMapper;



    @Override
    @Transactional
    public Pedido salvar(Pedido pedido) {
        log.info("Salvando pedido na base! pedido: {}", pedido);
        PedidoEntity entity = PedidoEntityMapper.toEntity(pedido);
        entity.setId(null);

        if (entity.getItens() != null) {
            entity.getItens().forEach(item -> {
                item.setPedido(entity);
                item.setId(null);
            });
        }
        PedidoEntity salvo = repository.save(entity);
        return pedidoMapper.toDomain(salvo);
    }

    @Override
    public Optional<Pedido> buscarPorId(UUID id) {
        log.info("Buscando pedido por id na base! id: {}", id);
        return repository.findById(id)
                .map(pedidoMapper::toDomain);
    }

    @Override
    public List<Pedido> buscarPorCliente(UUID clienteId) {
        log.info("Buscando pedidos por cliente na base! clienteId: {}", clienteId);
        return repository.findAllByClienteId(clienteId)
                .stream()
                .map(pedidoMapper::toDomain)
                .toList();
    }
}

