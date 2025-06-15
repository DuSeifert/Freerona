# Freerona - Sistema de Viagens (Terminal + SQLite)

## Descrição
Sistema de gerenciamento de viagens, com cadastro e login de usuários, criação, listagem, edição, deleção e aceitação de viagens. Interface via terminal e persistência em banco de dados SQLite.

## Funcionalidades
- Criar Conta
- Fazer Login
- Criar Viagem
- Listar Viagens
- Editar Viagem
- Deletar Viagem
- Aceitar Viagem

## Como Executar
1. **Pré-requisitos:**
   - Java 11+
   - [SQLite JDBC Driver](https://github.com/xerial/sqlite-jdbc)

2. **Compilar:**
   ```sh
   javac -cp .;sqlite-jdbc-3.43.0.0.jar src/**/*.java
   ```

3. **Executar:**
   ```sh
   java -cp "src;sqlite-jdbc-3.45.1.0.jar;slf4j-api-2.0.9.jar" Main
   ```

4. **Banco de Dados:**
   - O banco será criado automaticamente como `freerona.db` na primeira execução.

## Estrutura de Pastas
```
/src
  /model
    Usuario.java
    Viagem.java
  /dao
    Database.java
    UsuarioDAO.java
    ViagemDAO.java
  Main.java
```

## Observações
- O sistema é todo via terminal.
- Não há criptografia de senha (exemplo didático).
- O banco de dados é local (arquivo SQLite). 
