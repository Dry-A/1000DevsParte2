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

```sql
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


---

## ⚙️ Configuração do Projeto

1. **Adicione o conector MySQL ao projeto:**

   - **No IntelliJ IDEA**  
     - Vá em `File > Project Structure > Libraries`.  
     - Clique em `+` e adicione o arquivo `.jar` do MySQL Connector que você baixou.  

   - **No VS Code**  
     - Baixe o arquivo `mysql-connector-java-8.0.xx.jar` [aqui](https://dev.mysql.com/downloads/connector/j/).  
     - Ao compilar e executar o programa, inclua o `.jar` no classpath com o seguinte comando:
       ```bash
       javac -cp mysql-connector-java-8.0.xx.jar App.java
       java -cp mysql-connector-java-8.0.xx.jar:. App
       ```

     > **Nota:** Substitua `mysql-connector-java-8.0.xx.jar` pelo nome real do arquivo que você baixou.

2. **Configure os parâmetros de conexão no código:**

   No método `criarConexao()`:
   ```java
   String url = "jdbc:mysql://localhost:3306/teste";
   String usuario = "root";
   String senha = "root";
