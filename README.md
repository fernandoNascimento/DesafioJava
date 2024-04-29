# Desafio Java

## Tecnologias utilizadas

- ``Java 21``
- ``Spring boot``


## Serviços

- `pagamento` : Administrar os Pagamentos e fornecer mecanismo (API) para INCLUIR,
CONSULTAR, ATUALIZAR e DELETAR os pagamentos.

- `comprovante` : Gera os comprovantes utilizando os dados do pagamento de acordo com as
operações feitas.

## Como utilizar

- `Requisitos para realizar o teste`: Docker e Docker Compose,
- **1** Clone o projeto: git clone git@github.com:fernandoNascimento/DesafioJava.git
- **2** Abra o terminal no diretório DesafioJava
- **3** Execute o comando no terminal: docker-compose up -d
- **4** Importe o Postman Collection disponível em DesafioJava/collection para Postman e realizar os testes.

## Serviço pagamento

#### Incluir pagamento
- **URL:** `/pagamento`
- **Método HTTP:** POST
- **Descrição:** Cria um novo registro de pagamento.
- **campos_obrigatorios**: [dataInclusao, dataPagamento, valorPagamento, destinoPagamento]
- **campos_opcionais**: [descricaoPagamento, dadosRecorrencia]

Exemplo de corpo da solicitação:
```json
{
    "dataInclusao": "2024-04-28",
    "dataPagamento": "2024-04-28",
    "valorPagamento": 51,
    "descricaoPagamento": "desc",
    "dadosRecorrencia": {
        "dataFinal": "2024-06-27",
        "frequencia": "SEMANAL"
    },
    "destinoPagamento": {
        "chavePix": "fb2aeafc-1078-44c0-941d-5b218efab584"
    }
}
```

#### Consultar pagamento
- **URL:** `/pagamento`
- **Método HTTP:** GET
- **Descrição:** Consulta pagamentos registrados.

#### Parâmetros de Consulta
| Parâmetro | Valor |
| ------ | ------ |
| status | AGENDADO, EFETUADO ou TODOS |

Exemplo de resposta:
```json
{
    "content": [
        {
            "id": "662d69067b254c3f11a98a05",
            "statusPagamento": "EFETUADO",
            "dataInclusao": "2024-04-28",
            "dataPagamento": "2024-04-28",
            "valorPagamento": 51,
            "descricaoPagamento": "desc",
            "dadosRecorrencia": {
                "dataFinal": "2024-06-27",
                "frequencia": "SEMANAL"
            },
            "destinoPagamento": {
                "chavePix": "fb2aeafc-1078-44c0-941d-5b218efab584",
                "tipoChave": "ALEATORIA"
            }
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 7,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 7,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 1,
    "first": true,
    "empty": false
}
```

#### Atualizar pagamento
- **URL:** `/pagamento/{id}`
- **Método HTTP:** PUT
- **Descrição:** Atualiza um pagamento registrado.
- **campos_obrigatorios**: [id]

Exemplo de corpo da solicitação:
```json
{
    "descricaoPagamento": "descricao update"
}
```
Exemplo de resposta:

```json
{
    "id": "662d69067b254c3f11a98a05",
    "statusPagamento": "EFETUADO",
    "dataInclusao": "2024-04-28",
    "dataPagamento": "2024-04-28",
    "valorPagamento": 51,
    "descricaoPagamento": "desc update",
    "dadosRecorrencia": {
        "dataFinal": "2024-06-27",
        "frequencia": "SEMANAL"
    },
    "destinoPagamento": {
        "chavePix": "fb2aeafc-1078-44c0-941d-5b218efab584",
        "tipoChave": "ALEATORIA"
    }
}
```

#### Deletar pagamento
- **URL:** `/pagamento/{id}`
- **Método HTTP:** DELETE
- **Descrição:** Exclui um registro de pagamento.
- **campos_obrigatorios**: [id]

## Serviço comprovante

#### Consultar comprovantes
- **URL:** `/comprovante/{idPagamento}`
- **Método HTTP:** GET
- **Descrição:** Consulta comprovante de pagamentos registrados.
- **campos_obrigatorios**: [idPagamento]

Exemplo de resposta:
```json
{
    "content": [
        {
            "id": "662d6b671c3ada2974d05013",
            "idPagamento": "662d69067b254c3f11a98a05",
            "dataInclusao": "2024-04-28",
            "dataPagamento": "2024-04-28",
            "valorPagamento": 51,
            "descricaoPagamento": "desc",
            "dadosRecorrencia": {
                "dataFinal": "2024-06-27",
                "frequencia": "SEMANAL"
            },
            "destinoPagamento": {
                "chavePix": "fb2aeafc-1078-44c0-941d-5b218efab584",
                "tipoChave": "ALEATORIA"
            }
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 7,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "unpaged": false,
        "paged": true
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 7,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
```