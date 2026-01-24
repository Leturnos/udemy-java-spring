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

docker tag leturnos/hello-docker:0.0.1-SNAPSHOT leturnos/hello-docker:latest

docker login Docker.io

docker push leturnos/hello-docker:latest
```

2. Rodar (baixa do registry e roda):

```Bash
docker run -p 80:80 leturnos/hello-docker
```

subir com restart:

```Bash
docker run -p 80:80 -d --restart=always leturnos/hello-docker
```
Nota: -d "libera" o terminal, padrão é restart=no

3. Comandos básicos:

listar:

```Bash
docker container ls
docker container ls -a
```
Nota: mesma coisa do docker ps(comando mais antigo), -a mostra parados também

```Bash
docker images
```

parar:

```Bash
docker stop {id}
docker stop {name}
docker container stop {id}
docker container stop {name}
```

ver logs:

```Bash
docker logs 0
docker logs -f {id}
```
Nota: -f permite acompanhar os logs ao vivo(console)

baixar (sem rodar):

```Bash
docker pull {name}
```

procurar no registry:

```Bash
docker search {name}
docker search {name} --filter is-official=true
```

4. Gerenciando imagens:

ver histórico de uma imagem:

```Bash
docker image history {id}
docker image history {name}
```

inspecionar uma imagem:

```Bash
docker image inspect {id}
docker image inspect {name}
```

deletar uma imagem:

```Bash
docker image remove {id} 
docker rmi {id}
docker image remove {name} 
docker rmi {name}
```
Nota: -f após os comandos força a remoção

5. Gerenciando um container:

obs: comandos do image também funcionam aqui

pausar:

```Bash
docker container pause {id}
docker container pause {name}
```

despausar:

```Bash
docker container unpause {id}
docker container unpause {name}
```

deletar containers parados:

```Bash
docker container prune
```

logs do container:

```Bash
docker container logs -f {id}
```

matar o processo:

```Bash
docker container kill {id}
```

6. Events, Stats, Top e System

```Bash
docker events
```
Nota: tipo um debug

```Bash
docker top {id}
```

```Bash
docker system df
```

```Bash
docker stats
```
Nota: estatísticas da máquina

subir limitado:

```Bash
docker run -p 80:80 -m 512m --cpu-quota 50000 -d IMAGE_NAME:TAG
```
Nota: 100000 é 100%




