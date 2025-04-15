-- Inserindo dados na tabela pedidos
INSERT INTO pedidos (id, cliente_id, numero_cartao, nome_titular, data_validade, cvv, status, valor_total, data_criacao)
VALUES
    ('11111111-1111-1111-1111-111111111111', '22222222-2222-2222-2222-222222222222', '1234567890123456', 'João Silva', '12/25', '123', 'ABERTO', 150.00, NOW()),
    ('33333333-3333-3333-3333-333333333333', '44444444-4444-4444-4444-444444444444', '6543210987654321', 'Maria Oliveira', '11/24', '456', 'ABERTO', 300.00, NOW());

-- Inserindo dados na tabela itens_pedido
INSERT INTO itens_pedido (id, pedido_id, sku, quantidade, preco_unitario)
VALUES
    ('55555555-5555-5555-5555-555555555555', '11111111-1111-1111-1111-111111111111', 'SKU123', 2, 50.00),
    ('66666666-6666-6666-6666-666666666666', '11111111-1111-1111-1111-111111111111', 'SKU456', 1, 50.00),
    ('77777777-7777-7777-7777-777777777777', '33333333-3333-3333-3333-333333333333', 'SKU789', 3, 100.00);