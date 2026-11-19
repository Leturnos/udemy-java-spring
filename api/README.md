# API Principal (`api/`)

Aplicação RESTful principal construída ao longo do curso com **Spring Boot 3.5.6** e **Java 21**, reunindo CRUD completo, paginação HAL, HATEOAS, content negotiation, upload/download de arquivos e importação de planilhas CSV/XLSX (seção 22 em andamento).

---

## Funcionalidades e Endpoints

### People (`/api/person/v1`)
- `GET /api/person/v1` — Lista paginada (`page`, `size`, `direction`) com links HATEOAS/HAL
- `GET /api/person/v1/findPeopleByName/{firstName}` — Busca paginada por nome
- `GET /api/person/v1/{id}` — Busca por ID
- `POST /api/person/v1` — Criação de registro
- `PUT /api/person/v1/{id}` — Atualização completa
- `PATCH /api/person/v1/{id}` — Desabilitação lógica (`enabled = false`)
- `DELETE /api/person/v1/{id}` — Remoção

### Books (`/api/book/v1`)
- `GET /api/book/v1` — Lista paginada (`page`, `size`, `direction`) com links HATEOAS/HAL
- `GET /api/book/v1/{id}` — Busca por ID
- `POST /api/book/v1` — Criação de registro
- `PUT /api/book/v1/{id}` — Atualização completa
- `DELETE /api/book/v1/{id}` — Remoção

### Files (`/api/files/v1`)
- `POST /api/files/v1` — Upload de arquivo único (`multipart/form-data`, campo `file`)
- `POST /api/files/v1/multiple` — Upload de múltiplos arquivos (campo `files`)
- `GET /api/files/v1/{fileName}` — Download de arquivo armazenado em `UPLOAD_DIR`

### Diagnóstico
- `GET /api/test/v1` — Endpoint simples de demonstração de níveis de log (`TestLogController`)

---

## Content Negotiation e CORS

- **Formatos suportados** (via header `Accept` / `Content-Type`):
  - `application/json` (padrão)
  - `application/xml`
  - `application/x-yaml` (customizado por `YamlJackson2HttpMessageConverter`)
- **CORS**: configurado via `cors.originPatterns` no `application.yml`.

---

## Configuração e Variáveis de Ambiente

Todas as configurações possuem valores default para desenvolvimento local em `src/main/resources/application.yml` e podem ser sobrescritas via variáveis de ambiente:

| Variável | Default | Descrição |
| :--- | :--- | :--- |
| `DB_HOST` | `localhost` | Host do MySQL |
| `DB_PORT` | `3308` | Porta mapeada pelo `docker-compose.yml` da raiz |
| `DB_NAME` | `spring_boot` | Nome do schema |
| `DB_USERNAME` | `root` | Usuário do banco |
| `DB_PASSWORD` | `root` | Senha do banco |
| `UPLOAD_DIR` | `../upload-dir` | Diretório de armazenamento de uploads |

### Datasets e Migrations
- Migrations Flyway (`V1` a `V6`) em `src/main/resources/db/migration/`.
- Planilhas de exemplo (`people-import.csv` e `people-import.xlsx`) em `src/main/resources/dataset/`.

---

## Execução, Migrations e Testes

```bash
# Subir o MySQL na raiz do repositório
docker compose up -d

# Rodar a API (a partir de api/)
mvn spring-boot:run

# Rodar apenas migrations via Flyway plugin (opcional)
mvn flyway:migrate

# Rodar testes unitários (sem Docker)
mvn test -Dtest="ObjectMapperTests,BookServicesTest,PersonServicesTest"

# Rodar todos os testes (unitários + integração com Testcontainers)
mvn test
```

### Postman & Swagger
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Importação dinâmica no Postman**: cole `http://localhost:8080/v3/api-docs` no campo **Import** com a aplicação em execução, ou utilize os arquivos versionados em `../collections/`.
