# Hello Docker Exercise

Para criar a imagem Docker da pasta hello-docker siga esse passo a passo:


1. Compilar o projeto:


```Bash

mvn clean package

```

Este comando gera o arquivo .jar na pasta target, que é o artefato que o Dockerfile precisa copiar.


2. Criar a imagem Docker:


```Bash

docker build -t hello-docker:0.0.1-SNAPSHOT .

```

3. Executar o container:


```Bash

docker run -p 80:80 hello-docker:0.0.1-SNAPSHOT

```

4. Acessar o endereço:

http://localhost/hello-docker no navegador

---

## Outros comandos que vi na seção:

1. Envio de uma imagem para o Docker hub:

```Bash
docker tag hello-docker:0.0.1-SNAPSHOT leturnos/hello-docker:0.0.1-SNAPSHOT

docker login Docker.io

docker push leturnos/hello-docker:0.0.1-SNAPSHOT
```

