# Arquivo de Estudos (`studies-archive/`)

Este diretório preserva os **códigos didáticos únicos** desenvolvidos ao longo do curso que foram substituídos ou descontinuados na evolução da aplicação principal (`api/`), além dos **patches** com as diferenças dos desafios intermediários.

---

## Módulos Preservados

| Pasta | Seção(ões) | Conteúdo Único Preservado | Como Executar |
| :--- | :--- | :--- | :--- |
| [fundamentals-greeting-math](fundamentals-greeting-math) | Seções 04–05 | `GreetingController`, `MathController`, `MathFormat` e `UnsupportedMathOperationException` | `mvn -pl studies-archive/fundamentals-greeting-math spring-boot:run` |
| [api-versioning-v1-v2](api-versioning-v1-v2) | Seção 10 | Versionamento de API (`/v1` e `/v2`), `PersonDTOV1`, `PersonDTOV2` e `PersonMapper` | `mvn -pl studies-archive/api-versioning-v1-v2 spring-boot:run` |
| [custom-json-serializer](custom-json-serializer) | Seção 12 | Serialização customizada com `ObjectMapperConfig` e `GenderSerializer` | `mvn -pl studies-archive/custom-json-serializer spring-boot:run` |
| [hello-docker](hello-docker) | Seção 16 | Exercício de containerização com `DockerController`, `Dockerfile` e guia de comandos Docker | Ver [hello-docker/README.md](hello-docker/README.md) |

> Os módulos `api-versioning-v1-v2` e `custom-json-serializer` utilizam o MySQL do `docker-compose.yml` da raiz (`localhost:3308/spring_boot`).

---

## Patches dos Desafios

A pasta [challenges-patches](challenges-patches/README.md) contém os **14 arquivos `.patch`** gerados antes da consolidação das seções `12`, `16` e `17`, preservando o código exato das suas implementações em `challenge-*` em comparação com `base` e `solution-challenge-*`.
