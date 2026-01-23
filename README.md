# RESTful API with Spring Boot & Java - Udemy Course
Este repositório contém os códigos desenvolvidos durante o curso Spring Boot 2026 REST API's do 0 à AWS e GCP c Java e Docker na Udemy. 
O projeto foca na construção de APIs robustas, seguindo as melhores práticas de desenvolvimento, padrões de projeto e níveis de maturidade REST.

## 📌 Organização do Projeto
O repositório está organizado por pastas que representam as seções do curso. Cada seção funciona como um projeto independente ou uma evolução da anterior:

- Fundamentos: Controllers, roteamento e injeção de dependência.

- Persistência e Migrations: Uso de Spring Data JPA com MySQL e versionamento de banco de dados com Flyway.

- Padrão DTO: Implementação de Data Transfer Objects e Mappers (Dozer) para isolar as entidades da API.

- HATEOAS: Implementação de hipermídia para navegabilidade da API (Maturidade Nível 3).

- Documentação e Testes: Swagger/OpenAPI para documentação interativa e testes unitários com Mockito.

## 🚀 Como Executar o Projeto
Cada seção pode possuir um ambiente containerizado para o banco de dados.

Pré-requisitos:
- Java 21+

- Maven 3.x (instalado localmente)

- Docker & Docker Compose

Execução:
1. Navegue até a pasta da seção que deseja testar (ex: 12-swagger-openapi/challenge-1-api-books).

2. Suba a infraestrutura necessária (MySQL):

```Bash
docker-compose up -d
```
  Nota: As configurações de porta e credenciais encontram-se no arquivo docker-compose.yml de cada pasta.

Execute a aplicação:

```Bash
mvn spring-boot:run
```
  documentação Swagger em: http://localhost:8080/swagger-ui.html (Se tiver)
  
  Acesse a API em http://localhost:8080 (conferir endpoints no controller da seção).

## 🛠 Tecnologias Utilizadas
- Framework: Spring Boot 3

- Banco de Dados: MySQL

- Migração de Dados: Flyway

- Mapeamento: Dozer Mapper

- Documentação: Swagger (SpringDoc OpenAPI)

- Testes: JUnit 5, Mockito e Mockito Extension

- Containerização: Docker
