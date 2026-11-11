# Oficina Mecânica - API REST (PRW3 - Avaliação 3)

API REST para registro de consertos de carros em uma oficina mecânica, desenvolvida na disciplina PRW3 do IFSP São Carlos.

## Integrantes

- João Vitor Piovezan
- Marcos Vinicius Narçay Stanquini

## Tecnologias

- Java 21
- Spring Boot 4 (Web MVC, Data JPA, Validation, Security)
- JWT (biblioteca `com.auth0:java-jwt`)
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

## Autenticação (JWT)

Todas as rotas de `/consertos` exigem um token JWT. Para obter o token, faça login:

```http
POST /login
Content-Type: application/json

{ "login": "admin@oficina.com", "senha": "123456" }
```

Resposta (200 OK):

```json
{ "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." }
```

Envie o token no cabeçalho `Authorization` das demais requisições:

```
Authorization: Bearer <token>
```

- O token vale por 2 horas.
- Sem token, com token inválido/expirado ou com login/senha errados, a API responde 403 Forbidden.
- O usuário padrão é criado pela migration V5, com a senha guardada com hash BCrypt.
- A chave que assina os tokens é lida da variável de ambiente `JWT_SECRET` (se não existir, usa um valor padrão para rodar localmente):

```bash
JWT_SECRET=minha-chave-secreta ./mvnw spring-boot:run
```

## Endpoints

| Método | Rota | Descrição | Status |
|---|---|---|---|
| POST | `/login` | Autentica o usuário e devolve o token JWT (sem token) | 200 OK, 400 se inválido, 403 se login/senha errados |
| POST | `/consertos` | Cadastra um conserto | 201 Created (com header `Location`), 400 se inválido |
| GET | `/consertos` | Lista todos os dados, com paginação (`?page=0&size=10&sort=id`) | 200 OK |
| GET | `/consertos/algunsdados` | Lista id, datas, nome do mecânico, marca e modelo dos consertos ativos, sem paginação | 200 OK |
| GET | `/consertos/{id}` | Detalha um conserto | 200 OK, 404 Not Found |
| PUT | `/consertos/{id}` | Altera data de saída, nome e anos de experiência do mecânico | 200 OK, 400, 404 Not Found |
| DELETE | `/consertos/{id}` | Exclusão lógica (`ativo = false`) | 204 No Content, 404 Not Found |

As rotas de `/consertos` respondem 403 Forbidden quando o token não é enviado ou é inválido.

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
