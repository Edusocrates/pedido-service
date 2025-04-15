CREATE TABLE pedidos (
    id UUID PRIMARY KEY,
    cliente_id UUID NOT NULL,
    numero_cartao VARCHAR(255),
    nome_titular VARCHAR(255),
    data_validade VARCHAR(10),
    cvv VARCHAR(10),
    status VARCHAR(50),
    valor_total NUMERIC(19, 2) NOT NULL,
    data_criacao TIMESTAMP NOT NULL
);

CREATE TABLE itens_pedido (
    id UUID PRIMARY KEY,
    pedido_id UUID NOT NULL,
    sku VARCHAR(255) NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario NUMERIC(19, 2) NOT NULL,
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE
);

-- Indexes para otimizar buscas
CREATE INDEX idx_pedido_cliente_id ON pedidos(cliente_id);
CREATE INDEX idx_itens_pedido_pedido_id ON itens_pedido(pedido_id);
