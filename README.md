# 📦 Microsserviço de Pedidos

Este microsserviço é responsável pelo gerenciamento de **pedidos** dentro da arquitetura de e-commerce. Ele permite criar, consultar, cancelar e acompanhar o status de pedidos realizados por clientes. É um serviço independente, construído com foco em escalabilidade, separação de responsabilidades e integração assíncrona com outros serviços via mensageria.

---

## 🧱 Arquitetura

Este serviço segue os princípios da **Clean Architecture**, separando claramente as camadas de:
- **Domínio** (regras de negócio e entidades)
- **Casos de uso (UseCases)**
- **Interfaces de entrada (Handlers/Controllers)**
- **Interfaces de saída (Gateways, Repositories, Mensageria)**
- **Infraestrutura (JPA, RabbitMQ, Configs)**

Além disso, é um dos microsserviços que compõem a arquitetura distribuída baseada em **eventos assíncronos**, utilizando **RabbitMQ** para comunicação com os demais serviços.

---

## 🔗 Interações com outros microsserviços

| Microsserviço    | Tipo de comunicação | Objetivo                                                                 |
|------------------|---------------------|--------------------------------------------------------------------------|
| `Produto`        | Assíncrona (RabbitMQ) | Validação e sincronização de SKUs                                        |
| `Estoque`        | Assíncrona (RabbitMQ) | Notificação de novos pedidos para reserva de estoque                     |
| `Pagamento`      | Assíncrona (RabbitMQ) | Envio de dados de pagamento e atualização de status                      |
| `PedidoReceiver` | Assíncrona (RabbitMQ) | Escuta de eventos de outros serviços para atualizar estado do pedido    |

---

## 🛠️ Tecnologias utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring AMQP (RabbitMQ)**
- **PostgreSQL**
- **Lombok**
- **MapStruct** (para mapeamento entre entidades e objetos de domínio/DTOs)
- **Clean Architecture**
- **Docker / Docker Compose**

---

## 🧾 Funcionalidades disponíveis

### ✅ Pedido
- Criar novo pedido
- Consultar pedido por ID
- Cancelar pedido (somente se ainda não estiver em processamento/finalizado)
- Calcular automaticamente o valor total com base nos itens
- Persistência do status do pedido
- Armazenamento dos dados de pagamento com segurança

---

## 📂 Estrutura do projeto
```bash
src
├── main
│   ├── java
│   │   └── com
│   │       └── fiap
│   │           └── rm358568
│   │               └── edusocrates
│   │                   ├── pedido_service
│   │                   │   ├── aplicacao
│   │                   │   │   ├── usecases
│   │                   │   │   ├── handlers
│   │                   │   ├── dominio
│   │                   │   │   ├── entities
│   │                   │   │   │  ├── enums
│   │                   │   │   ├── gateways
│   │                   │   ├── API
│   │                   │   │   ├── controllers
│   │                   │   │   ├── requests
│   │                   │   │   ├── responses
│   │                   │   │   ├── exceptions
│   │                   │   ├── infraestrutura
│   │                   │   │   ├── persistence
│   │                   │   │   ├── config
│   │                   │   │   ├── messaging

```

## 🧪 Testes

- Os testes de unidade são aplicados aos UseCases e aos Mappers.
- Os testes de integração utilizam H2 ou testcontainers (caso configurado) para simular a base de dados.
- A comunicação via mensageria pode ser testada com ferramentas como **Testcontainers + RabbitMQ** ou usando mocks locais.



## 🔄 Comunicação via RabbitMQ

Este microsserviço envia e consome eventos através de tópicos definidos para integração com Estoque, Pagamento e Produto.

**Exemplos de tópicos usados:**

| Evento                         | Tipo        | Fila/Routing Key               |
|-------------------------------|-------------|-------------------------------|
| Pedido criado → Estoque       | `Producer`  | `pedido.criado`               |
| Atualização status (Pagamento)| `Consumer`  | `pedido.atualizar-status`     |
| Sincronização de Produto      | `Consumer`  | `produto.sincronizar`         |

---

## 🐳 Docker
- O projeto pode ser executado em um container Docker, utilizando o Dockerfile e o docker-compose.yml fornecidos.
- Certifique-se de ter o Docker instalado e em execução.
- Para iniciar o serviço, execute:
```bash
docker-compose up --build
```
- O serviço estará disponível em `http://localhost:8083/pedido-service`.

## 🚀 Endpoints REST disponíveis
| Método | Endpoint                 | Descrição                           |
|--------|--------------------------|-------------------------------------|
| POST   | `/pedidos`               | Criação de novo pedido              |
| GET    | `/pedidos/{id}`          | Consulta pedido por ID              |
| PATCH  | `/pedidos/{id}/status`   | Atualiza status do pedido           |
| GET    | `/pedidos/{clienteId}`   | Lista todos os pedidos por cliente  |
| PATCH  | `/pedidos/{id}/cancelar` | Realiza o cancelamento de um pedido |


---
## 🧠 Regras de negócio
Pedido deve conter ao menos 1 item com quantidade maior que 0.

Valor total é calculado automaticamente com base nos preços dos itens.

Dados de pagamento devem ser obrigatoriamente informados e válidos.

Pedido não pode ser cancelado se já estiver finalizado ou em processamento.

SKUs devem estar previamente sincronizados com o serviço de Produto.

## Acesso ao banco de dados (PostgreSQL)
- Host: `localhost`
- Porta: `5432`
- Usuário: `postgres`
- Senha: `postgres`
- Banco: `pedidos_db`
- URL: `jdbc:postgresql://localhost:5432/pedidos_db`
- Driver: `PostgreSQL`
- JDBC URL: `jdbc:postgresql://localhost:5432/pedidos_db`
- Ferramenta: `DBeaver` ou `pgAdmin`

## Acesso ao RabbitMQ
- Host: `localhost`
- Porta: `15672`
- Usuário: `guest`
- Senha: `guest`
- Vá para `http://localhost:15672` para acessar a interface do RabbitMQ.
- Crie uma fila chamada `pedido` para receber mensagens.
- Crie uma exchange chamada `pedido` para enviar mensagens.
- Crie uma binding entre a fila e a exchange.
- Crie uma rota chamada `pedido.created` para receber mensagens da exchange.
- Crie uma rota chamada `pedido.atualizar-status` para receber mensagens da exchange.
- Crie uma rota chamada `produto.sincronizar` para receber mensagens da exchange.
- Crie uma rota chamada `estoque.reservar` para receber mensagens da exchange.


## ✍️ Autor
- Eduardo Sócrates Caria
- GitHub: https://github.com/Edusocrates
- RM: 358568
- Turma: 6ADJT
- Grupo 15
