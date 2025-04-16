package com.fiap.rm358568.edusocrates.pedido_service.infraestrutura.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // Exchanges
    public static final String EXCHANGE_PEDIDO = "pedido.exchange";
    public static final String EXCHANGE_PRODUTO = "produto.exchange";
    public static final String EXCHANGE_ESTOQUE = "estoque.exchange";
    public static final String EXCHANGE_PAGAMENTO = "pagamento.exchange";

    // Queues
    public static final String PEDIDO_CREATED_QUEUE = "pedido.created.queue";
    public static final String PRODUTO_UPDATED_QUEUE = "produto.updated.queue";
    public static final String PRODUTO_CREATED_QUEUE = "produto.created.queue";
    public static final String ESTOQUE_PEDIDO_PROCESS_QUEUE = "estoque.pedido.process.queue";
    public static final String PAGAMENTO_PEDIDO_PROCESS_QUEUE = "pagamento.pedido.process.queue";
    public static final String PEDIDO_CONFIRMACAO_ESTOQUE_QUEUE = "pedido.confirmacao.estoque.queue";
    public static final String PEDIDO_CONFIRMACAO_PAGAMENTO_QUEUE = "pedido.confirmacao.pagamento.queue";

    // Routing Keys
    public static final String RK_PEDIDO_CREATED = "pedido.created";
    public static final String RK_PRODUTO_UPDATED = "produto.updated";
    public static final String RK_PRODUTO_CREATED = "produto.created";
    public static final String RK_ESTOQUE_PROCESS = "estoque.process";
    public static final String RK_PAGAMENTO_PROCESS = "pagamento.process";
    public static final String RK_ESTOQUE_CONFIRMACAO = "estoque.confirmacao";
    public static final String RK_PAGAMENTO_CONFIRMACAO = "pagamento.confirmacao";

    public static final String PAGAMENTO_STATUS_QUEUE = "pedido.pagamento.status";
    public static final String ESTOQUE_STATUS_QUEUE = "pedido.estoque.status";
    public static final String PEDIDO_ATUALIZAR_STATUS_QUEUE = "pedido.atualizar.status";

    // Exchanges
    @Bean
    public TopicExchange pedidoExchange() {
        return new TopicExchange(EXCHANGE_PEDIDO);
    }

    @Bean
    public TopicExchange produtoExchange() {
        return new TopicExchange(EXCHANGE_PRODUTO);
    }

    @Bean
    public TopicExchange estoqueExchange() {
        return new TopicExchange(EXCHANGE_ESTOQUE);
    }

    @Bean
    public TopicExchange pagamentoExchange() {
        return new TopicExchange(EXCHANGE_PAGAMENTO);
    }

    // Queues
    @Bean
    public Queue pedidoCreatedQueue() {
        return new Queue(PEDIDO_CREATED_QUEUE);
    }

    @Bean
    public Queue estoqueStatusQueue() {
        return new Queue(ESTOQUE_STATUS_QUEUE);
    }

    @Bean
    public Queue pagamentoStatusQueue() {
        return new Queue(PAGAMENTO_STATUS_QUEUE);
    }

    @Bean
    public Queue pedidoAtualizarStatusQueue() {
        return new Queue(PEDIDO_ATUALIZAR_STATUS_QUEUE);
    }

    @Bean
    public Queue produtoUpdatedQueue() {
        return new Queue(PRODUTO_UPDATED_QUEUE);
    }

    @Bean
    public Queue produtoCreatedQueue() {
        return new Queue(PRODUTO_CREATED_QUEUE);
    }

    @Bean
    public Queue estoqueProcessQueue() {
        return new Queue(ESTOQUE_PEDIDO_PROCESS_QUEUE);
    }

    @Bean
    public Queue pagamentoProcessQueue() {
        return new Queue(PAGAMENTO_PEDIDO_PROCESS_QUEUE);
    }

    @Bean
    public Queue pedidoConfirmacaoEstoqueQueue() {
        return new Queue(PEDIDO_CONFIRMACAO_ESTOQUE_QUEUE);
    }

    @Bean
    public Queue pedidoConfirmacaoPagamentoQueue() {
        return new Queue(PEDIDO_CONFIRMACAO_PAGAMENTO_QUEUE);
    }

    // Bindings
    @Bean
    public Binding bindPedidoCreated() {
        return BindingBuilder.bind(pedidoCreatedQueue()).to(pedidoExchange()).with(RK_PEDIDO_CREATED);
    }

    @Bean
    public Binding bindProdutoUpdated() {
        return BindingBuilder.bind(produtoUpdatedQueue()).to(produtoExchange()).with(RK_PRODUTO_UPDATED);
    }

    @Bean
    public Binding bindProdutoCreated() {
        return BindingBuilder.bind(produtoCreatedQueue()).to(produtoExchange()).with(RK_PRODUTO_CREATED);
    }

    @Bean
    public Binding bindEstoqueProcess() {
        return BindingBuilder.bind(estoqueProcessQueue()).to(estoqueExchange()).with(RK_ESTOQUE_PROCESS);
    }

    @Bean
    public Binding bindPagamentoProcess() {
        return BindingBuilder.bind(pagamentoProcessQueue()).to(pagamentoExchange()).with(RK_PAGAMENTO_PROCESS);
    }

    @Bean
    public Binding bindPedidoConfirmacaoEstoque() {
        return BindingBuilder.bind(pedidoConfirmacaoEstoqueQueue()).to(estoqueExchange()).with(RK_ESTOQUE_CONFIRMACAO);
    }

    @Bean
    public Binding bindPedidoConfirmacaoPagamento() {
        return BindingBuilder.bind(pedidoConfirmacaoPagamentoQueue()).to(pagamentoExchange()).with(RK_PAGAMENTO_CONFIRMACAO);
    }
}
