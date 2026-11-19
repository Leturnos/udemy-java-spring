# RESTful API with Spring Boot & Java — Udemy Course

Repositório dos estudos do curso **Spring Boot 2026 REST API's do 0 à AWS e GCP c/ Java e Docker** (Udemy), organizado como um **monorepo enxuto**: aplicação principal consolidada em `api/`, módulos didáticos históricos preservados em `studies-archive/` e histórico completo das seções versionado via **Git tags**.

---

## Organização do Repositório

```text
.
├── api/                         # Aplicação principal evoluída (Spring Boot 3.5.6 + Java 21)
├── collections/                 # Coleção e environment do Postman centralizados
├── studies-archive/             # Módulos didáticos únicos e patches dos desafios
│   ├── fundamentals-greeting-math/  # Seções 04–05: GreetingController e MathController
│   ├── api-versioning-v1-v2/        # Seção 10: Versionamento v1/v2 com PersonMapper
│   ├── custom-json-serializer/      # Seção 12: ObjectMapperConfig e GenderSerializer
│   ├── hello-docker/                # Seção 16: Exercício introdutório de Docker
│   └── challenges-patches/          # 14 arquivos .patch (base ↔ challenge ↔ solution)
├── upload-dir/                  # Diretório padrão para upload/download de arquivos
├── docker-compose.yml           # Infraestrutura MySQL centralizada
├── .env.example                 # Template de variáveis de ambiente
└── pom.xml                      # POM agregador Maven (reactor com 5 módulos)
```

---

## Pré-requisitos

- **Java 21+**
- **Maven 3.x** (ou Maven integrado da IDE)
- **Docker & Docker Compose** (necessário para o MySQL local e testes de integração com Testcontainers)

---

## Como Executar

1. **(Opcional)** Crie um `.env` na raiz caso queira customizar porta, banco ou diretório de uploads:
   ```bash
   cp .env.example .env
   ```

2. **Suba o MySQL** a partir da raiz do repositório:
   ```bash
   docker compose up -d
   ```

3. **Execute a API principal** (o Flyway aplicará as migrations automaticamente no startup):
   ```bash
   cd api
   mvn spring-boot:run
   ```

4. **Acesse a documentação e endpoints**:
   - **Swagger UI**: `http://localhost:8080/swagger-ui.html` (redireciona pela raiz `http://localhost:8080/`)
   - **OpenAPI Spec**: `http://localhost:8080/v3/api-docs`
   - **Postman**: importe os arquivos em `collections/` (`SpringBoot_Course.postman_collection.json` e `Dev_Environment.postman_environment.json`) ou importe diretamente via URL do `/v3/api-docs`.

---

## Comandos Úteis (Build e Testes)

```bash
# Compilar todos os módulos do monorepo a partir da raiz
mvn test-compile

# Executar testes unitários da API principal (não exige Docker)
mvn -pl api test -Dtest="ObjectMapperTests,BookServicesTest,PersonServicesTest"

# Executar todos os testes da API (inclui Testcontainers — exige Docker ativo)
mvn -pl api test

# Executar um módulo preservado em studies-archive
mvn -pl studies-archive/fundamentals-greeting-math spring-boot:run
```

Mais detalhes específicos da API (endpoints, content negotiation, variáveis de ambiente e migrations) estão em [api/README.md](api/README.md). Detalhes dos módulos arquivados e patches estão em [studies-archive/README.md](studies-archive/README.md).

---

## Stack Principal

- **Backend**: Java 21, Spring Boot 3.5.6 (`spring-boot-starter-web`, `data-jpa`, `hateoas`)
- **Banco e Migrations**: MySQL 9.4, Flyway (`V1` a `V6`)
- **Mapeamento e Serialização**: Dozer Mapper, Jackson (JSON, XML e YAML)
- **Arquivos e Planilhas**: Multipart Upload/Download, Apache Commons CSV, Apache POI (`.xlsx`)
- **Documentação**: SpringDoc OpenAPI (`swagger-ui`)
- **Qualidade e Testes**: JUnit 5, Mockito, Testcontainers (`mysql`), REST Assured

---

## Histórico das Seções (Git Tags)

Para manter o repositório limpo sem perder nenhum estágio intermediário do curso, cada seção foi marcada com uma **Git tag** imutável:

| Tag | Conteúdo / Seção |
| :--- | :--- |
| `study/sec-04` | First steps with Spring Boot |
| `study/sec-05` | Path params and exceptions |
| `study/sec-06` | HTTP verbs |
| `study/sec-07` | Spring Data JPA and MySQL |
| `study/sec-08` | Logs |
| `study/sec-09` | DTO and unit tests |
| `study/sec-10` | API versioning |
| `study/sec-11` | Flyway migrations |
| `study/sec-12` | Custom JSON serialization |
| `study/sec-13` | Content negotiation |
| `study/sec-14` | HATEOAS, JUnit and Mockito |
| `study/sec-15` | Swagger/OpenAPI and Books challenges |
| `study/sec-16` | Docker containerization |
| `study/sec-17` | Integration tests with Testcontainers |
| `study/sec-18` | CORS |
| `study/sec-19` | PATCH verb and XML/YAML tests |
| `study/sec-20` | Query params, pagination and HAL |
| `study/sec-21` | File upload and download |
| `study/sec-22-wip` | Excel and CSV processing (WIP) |
| `archive/pre-refactor-2026-11` | Snapshot completo anterior à consolidação do monorepo |

Para consultar uma seção antiga sem alterar a branch atual:
```bash
# Criar uma branch temporária de inspeção a partir da tag
git switch -c inspect-sec-15 study/sec-15

# Ou apenas navegar em detached HEAD
git checkout study/sec-15
```
