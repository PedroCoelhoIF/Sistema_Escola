# Projeto Escola (Etapa - 1) - CRUD com Spring Boot

Este projeto é uma aplicação web extremamente simples com foco no backend, desenvolvida como atividade para a disciplina de Web II. O sistema consiste em um CRUD (Create, Read, Update, Delete) completo para gerenciar Professores e Categorias, construído com o framework Spring Boot.

![Demonstração do Sistema de Cadastro](https://raw.githubusercontent.com/PedroCoelhoIF/Sistema_Cadastro_Professores/refs/heads/master/Trabalho%201/escola/assets/demo-crud-professor.gif)

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3**
* **Spring Data JPA:** Para persistência de dados.
* **Hibernate:** Como implementação do JPA.
* **MySQL:** Como banco de dados relacional.
* **Thymeleaf:** Como template engine para renderizar as páginas web.
* **Maven:** Para gerenciamento de dependências e build do projeto.
* **Git & GitHub:** Para versionamento de código.

## Funcionalidades
O sistema permite o gerenciamento completo de duas entidades principais:

#### Professor
* **Cadastrar** um novo professor, incluindo nome, email e uma imagem de perfil.
* **Listar** todos os professores cadastrados em uma tabela.
* **Buscar** professores por nome.
* **Editar** as informações de um professor, incluindo a troca da imagem.
* **Excluir** um professor do sistema.

#### Categoria
* **Cadastrar** uma nova categoria com um nome.
* **Listar** todas as categorias.
* **Buscar** categorias por nome.
* **Editar** o nome de uma categoria.
* **Excluir** uma categoria.

## Como Executar o Projeto

#### Pré-requisitos
* Java (JDK 21 ou superior)
* Maven 3.x
* MySQL Server

#### Passos para Configuração
1.  **Clone o Repositório**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    ```

2.  **Configure o Banco de Dados**
    * Abra seu cliente MySQL e crie um novo banco de dados chamado `escola`.
        ```sql
        CREATE DATABASE escola;
        ```
    * Abra o arquivo `src/main/resources/application.properties`.
    * Altere as propriedades `spring.datasource.username` e `spring.datasource.password` para corresponder às suas credenciais do MySQL.

3.  **Execute a Aplicação**
    * Abra o projeto na sua IDE (IntelliJ).
    * Encontre a classe `EscolaApplication.java` e execute o método `main`.
    * A aplicação iniciará na porta `8080`.

4.  **Acesse a Aplicação**
    * Para ver a lista de professores: `http://localhost:8080/professor/listar`
    * Para ver a lista de categorias: `http://localhost:8080/categoria/listar`

## Estrutura do Projeto e Responsabilidades

O projeto segue a arquitetura Model-View-Controller (MVC) e está organizado da seguinte forma:

#### `src/main/java/com/escola`
* **`EscolaApplication.java`**: A classe principal que inicializa a aplicação Spring Boot.

* **`/config`**:
    * `MvcConfig.java`: Classe de configuração responsável por mapear as URLs das imagens salvas em um diretório externo para que possam ser exibidas no navegador.

* **`/controller`**:
    * Classes responsáveis por receber as requisições web (HTTP), processar os dados de entrada, chamar a camada de repositório e retornar uma View (página HTML) para o usuário.

* **`/dto` (Data Transfer Object)**:
    * Records que representam uma versão simplificada dos modelos, usados para transferir dados de forma segura e validada entre as Views e os Controllers.

* **`/model`**:
    * As classes de entidade (`Professor`, `Categoria`, `Usuario`) que são marcadas com `@Entity`. Elas representam as tabelas do banco de dados.

* **`/repository`**:
    * Interfaces que estendem `JpaRepository`. Elas são responsáveis pela comunicação com o banco de dados, provendo métodos CRUD (salvar, buscar, excluir, etc.) sem a necessidade de escrever código SQL.

#### `src/main/resources`
* **`/static`**: Para arquivos estáticos como CSS, JavaScript e imagens (embora as imagens de upload sejam salvas externamente).
* **`/templates`**: Contém os arquivos HTML do Thymeleaf, que formam a User Interface (UI) da aplicação.
* **`application.properties`**: Arquivo central de configurações da aplicação, incluindo a conexão com o banco de dados.
