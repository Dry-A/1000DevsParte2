# Gerenciamento de Dados com Java e MySQL

Este projeto é uma aplicação Java para realizar operações de CRUD (Create, Read, Update, Delete) em um banco de dados MySQL. Ele utiliza JDBC (Java Database Connectivity) para gerenciar a conexão e interações com o banco.

---

## 🛠️ Funcionalidades

1. **Conectar ao banco de dados**  
   Conexão estabelecida com um banco MySQL local usando as credenciais fornecidas.

2. **Consultar dados**
    - Listar todas as pessoas da tabela `pessoa`.
    - Consultar uma pessoa específica pelo `id`.

3. **Inserir dados**  
   Adicionar uma nova pessoa ao banco de dados.

4. **Atualizar dados**  
   Atualizar informações de uma pessoa existente pelo `id`.

5. **Deletar dados**  
   Excluir uma pessoa da tabela `pessoa` pelo `id`.

---

## 📋 Pré-requisitos

- Java JDK 8 ou superior instalado.
- MySQL Server instalado e em execução.
- Conector MySQL JDBC (arquivo `.jar`) adicionado ao classpath do projeto. Você pode baixá-lo [aqui](https://dev.mysql.com/downloads/connector/j/).

---

## 🏗️ Estrutura do Banco de Dados

Crie a tabela `pessoa` no banco de dados `teste` antes de executar o programa. Use o seguinte script SQL:

```
CREATE DATABASE IF NOT EXISTS teste;

USE teste;

CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(15),
    idade INT,
    profissao VARCHAR(50),
    renda FLOAT
);
```




## ⚙️ Configuração do Projeto

1. **Adicione o conector MySQL ao projeto:**

   - **No IntelliJ IDEA**  
     - Vá em `File > Project Structure > Libraries`.  
     - Clique em `+` e adicione o arquivo `.jar` do MySQL Connector que você baixou.  

   - **No VS Code**  
     - Baixe o arquivo `mysql-connector-java-8.0.xx.jar` [aqui](https://dev.mysql.com/downloads/connector/j/).  
     - Ao compilar e executar o programa, inclua o `.jar` no classpath com o seguinte comando:
       
     - ```
       javac -cp mysql-connector-java-8.0.xx.jar App.java
       java -cp mysql-connector-java-8.0.xx.jar:. App
       ```

     > **Nota:** Substitua `mysql-connector-java-8.0.xx.jar` pelo nome real do arquivo que você baixou.

2. **Configure os parâmetros de conexão no código:**

   No método `criarConexao()`:
   
```
   String url = "jdbc:mysql://localhost:3306/teste";
   String usuario = "root";
   String senha = "root";
   
```
Substitua usuario e senha pelas credenciais do seu servidor MySQL.
Certifique-se de que o banco de dados teste está criado e acessível na porta 3306.

      
      
3. **Crie o banco de dados e a tabela:**

Certifique-se de executar o script SQL abaixo no seu servidor MySQL antes de rodar o programa:

```
      
CREATE DATABASE IF NOT EXISTS teste;

USE teste;

CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(15),
    idade INT,
    profissao VARCHAR(50),
    renda FLOAT
);
      
```


4. **Compile e execute o programa:**

No IntelliJ IDEA

Execute o programa diretamente pelo botão Run.
No VS Code
Compile e execute o programa manualmente:

```
javac -cp mysql-connector-java-8.0.xx.jar App.java
java -cp mysql-connector-java-8.0.xx.jar:. App
```

## 📂 Estrutura do Código

O código está organizado em métodos estáticos para realizar as operações CRUD no banco de dados.

### Métodos Principais

- **`criarConexao()`**  
  Estabelece a conexão com o banco de dados utilizando os parâmetros fornecidos.

- **`consultar(int id)`**  
  Realiza a consulta de todos os registros ou de um registro específico pelo ID.
    - Caso `id <= 0`, retorna todos os registros.

- **`inserir(String nome, String telefone, int idade, String profissao)`**  
  Insere uma nova pessoa no banco de dados com as informações fornecidas.

- **`atualizar(int id, String novoNome, String novoTelefone, String novaProfissao)`**  
  Atualiza as informações de um registro existente baseado no ID.

- **`deletar(int id)`**  
  Remove um registro do banco de dados pelo ID.

---

## 🚀 Como Usar

1. Certifique-se de que a configuração do projeto foi concluída com sucesso.
2. No método `main()` do código, chame os métodos conforme necessário.


```
// Consultar todos os registros
consultar(-1);

// Consultar registro específico
consultar(1);

// Inserir um novo registro
inserir("João Silva", "987654321", 30, "Engenheiro");

// Atualizar um registro
atualizar(1, "Maria Oliveira", "123456789", "Professora");

// Deletar um registro
deletar(1);

```

## 🛠 Compile e Execute o Programa

Certifique-se de seguir os passos abaixo para compilar e executar o programa.

---

## 📚 Dependências

- **Java 8+**
- **MySQL Connector/J**
- **MySQL Server**

---

## ❓ Dúvidas

Caso tenha dúvidas ou encontre algum problema, entre em contato ou consulte a documentação do [MySQL Connector/J](https://dev.mysql.com/doc/connector-j/en/).
