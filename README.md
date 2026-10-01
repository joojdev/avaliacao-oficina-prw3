# Oficina Mecânica - API REST (PRW3 - Avaliação 3)

API REST para registro de consertos de carros em uma oficina mecânica, desenvolvida na disciplina PRW3 do IFSP São Carlos.

## Integrantes

- João Vitor Piovezan
- Marcos Vinicius Narçay Stanquini

## Tecnologias

- Java 21
- Spring Boot 4 (Web MVC, Data JPA, Validation)
- H2 Database (em arquivo)
- Flyway (migrations)
- Lombok

## Como rodar

```bash
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`. O banco é criado na pasta `DATA/` e as migrations do Flyway (`src/main/resources/db/migration`) criam a tabela e inserem alguns consertos de exemplo.

### H2 Console

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./DATA/oficina`
- Usuário: `sa`
- Senha: (vazia)

## Endpoints

| Método | Rota | Descrição | Status |
|---|---|---|---|
| POST | `/consertos` | Cadastra um conserto | 201 Created (com header `Location`), 400 se inválido |
| GET | `/consertos` | Lista todos os dados, com paginação (`?page=0&size=10&sort=id`) | 200 OK |
| GET | `/consertos/algunsdados` | Lista id, datas, nome do mecânico, marca e modelo dos consertos ativos, sem paginação | 200 OK |
| GET | `/consertos/{id}` | Detalha um conserto | 200 OK, 404 Not Found |
| PUT | `/consertos/{id}` | Altera data de saída, nome e anos de experiência do mecânico | 200 OK, 400, 404 Not Found |
| DELETE | `/consertos/{id}` | Exclusão lógica (`ativo = false`) | 204 No Content, 404 Not Found |

### Validações

- Datas no formato `dd/mm/aaaa`.
- Obrigatórios: nome do mecânico, marca, modelo e ano do veículo (formato `aaaa`).
- A cor do veículo é opcional.

### Exemplo de POST `/consertos`

```json
{
  "dataEntrada": "10/09/2026",
  "dataSaida": "15/09/2026",
  "mecanico": { "nome": "Carlos Souza", "anosExperiencia": 12 },
  "veiculo": { "marca": "Volkswagen", "modelo": "Gol", "ano": "2015", "cor": "Prata" }
}
```

### Exemplo de PUT `/consertos/{id}`

```json
{
  "dataSaida": "20/09/2026",
  "mecanico": { "nome": "Ana Ribeiro", "anosExperiencia": 8 }
}
```
