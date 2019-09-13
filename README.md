# Agenda Pets

Projeto de avaliação do GraphQL em conjunto com SpringBoot(Java).

### Pré-requisitos

1. Java 8
2. Apache Maven 3.6.0
3. PostgreSQL 11.5

### Configuração do Banco de dados

1. Para o projeto rodar, cire uma Base de Dados no PostgreSQL com o nome que desejar

2. Crie também um Schema com o nome 'api'

3. Feito isso será necessário definir algumas variáveis de ambiente com as informações para a conexão com o banco:

   - PETS_DB_HOST: Caso o banco não esteja local, deve se informar o endereço do host do banco de dados. Ex. 189.10.9.88
   - PETS_DB_PORT: Caso o banco não esteja rodando na porta padrão, devera passar a porta em que o banco está rodando.
   - PETS_DB_NAME: Deverá conter o nome da base de dados que foi criada no passo 1
   - PETS_DB_SCHEMA: Caso tenha criado um Schema diferente de 'api', deverá definir nesta variável
   - PETS_DB_USER: Devera ser informado o usuário  para conexão com a base de dados
   - PETS_DB_PASS: Deverar ser informado a senha para conexão com a base de dados

   Para definir a variaveis siga o exemplo abaixo substituindo NOME_VAR pelo nome da variável que quer definir, e VALOR pelo valor que será atribuido a variável de ambiente:

   ```bash
   export NOME_VAR=VALOR
   ```

### Executando os testes

Para executar os testes basta executar o comando abaixo:

```bash
mvn test
```

### Build da aplicação

Para realizar o build da aplicação, execute o comando abaixo:

```bash
mvn clean package
```

Após o comando finalizar será gerado o arquivo 'target/agenda-pets-0.0.1-SNAPSHOT.jar'

### Iniciando a aplicação

Após ter realizado o build basta executar o comando abaixo, e terá sua aplicação rodando!

```bash
java -jar target/agenda-pets-0.0.1-SNAPSHOT.jar
```

Por padrão a aplicação vai iniciar na porta 5000, portanto para acessar localmente acesse o  endereço:

- [localhost:5000/playground](http://localhost:5000/playground) - Para acessar o playground
- [localhost:5000/graphql](http://localhost:5000/graphql) - Endpoint que recebe as requisições