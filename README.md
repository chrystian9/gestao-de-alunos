# Gestão de Alunos

Projeto de um sistema de gerenciamento de alunos por seus professores, realizando cadastro de alunos, professores e plotagem de graficos estatísticos com base nas notas dos alunos.

# Tecnologias usadas

- Java 11
- Node 14.17.1
- Spring Boot 2.5.2
- Angular 12
- Postgres 12.5

# Configurando Ambiente

Faça o clone do [repositorio](https://github.com/chrystian9/gestao-de-alunos)

## Backend

### Banco Postgres com Docker

* Instalar Postgres 12.5 com Docker: Nesse [link](https://elanderson.net/2018/02/setup-postgresql-on-windows-with-docker/) existe uma boa explicação, no entanto vamos mudar algumas coisas. 

  - Baixe e instale o docker pelo [link](https://hub.docker.com/editions/community/docker-ce-desktop-windows)
  - Rode no power shell o seguinte comando
  ```
  docker create -v /var/lib/postgresql/data --name PostgresData alpine
  ```
  - Para iniciar o banco vamos usar o comando 
  ```
  docker run -p 5435:5432 --name gestao_alunos -e POSTGRES_PASSWORD=gestaoalunos -d --volumes-from PostgresData postgres
  ```
  - Para criar o banco "gestao_alunos", seguia os seguintes passos: 
    
    -> Primeiro acesse o bash interno do container do postgres
    ```
    docker exec -it gestao_alunos bash
    ```
    -> Depois realize o acesso ao servidor do postgres
     ```
    psql -U postgres
    ```
    -> E por fim rode a query de criação do banco
    ```
    postgres-# CREATE DATABASE gestao_alunos;
    ```
    -> Pode fechar o terminal
  
  - Sempre que reiniciar a maquina, sera necessario iniciar o container para utiliza-lo, o que pode ser feito pela interface do docker.
  
### Java 11

*  [Link](https://www.oracle.com/java/technologies/downloads/#jdk17-windows) (Versão "x64 Installer") para download. É um instalador simples.

### Maven

* Nesse [link](https://dicasdejava.com.br/como-instalar-o-maven-no-windows/) existe um bom tutorial para baixar e configurar o Maven.

### Importar o projeto via IntelliJ IDEA

* Importe o projeto selecionando o arquivo "pom.xml" no seu diretorio.

  ```
  - File -> Open
  - Select file pom.xml
  - Select option "Open as Project"
  - Wait process...
  ```
  
* Vá em Run/Debug Configuration e adicione uma nova configuração de "Application"
 - Aqui adicionamos a classe principal do projeto (GestaoDeAlunosApplication) como main class
 - Selecionamos o Java 11
 - 
* Assim que carregar as dependencias, o backend já podera ser iníciado.

## Frontend

* Configure o Node a partir do modulo [n](https://www.npmjs.com/package/n) do NPM

```
sudo n 14.17.1
```

* Instalar [Angular CLI](https://www.devmedia.com.br/angular-cli-instalacao/38247)

* Abra o diretorio do frontend no Visual Code

* Rode os seguintes comandos

```
npm install
```

* Para rodar o modo de desenvolvimento do angular, basta rodar o seguinte comando

```
ng serve
```
