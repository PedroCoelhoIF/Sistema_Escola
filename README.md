# Projeto Escola - CRUD Completo com Spring Boot

Este projeto é uma aplicação web full-stack desenvolvida como atividade para a disciplina de Web II. O sistema consiste em um CRUD (Create, Read, Update, Delete) completo para gerenciar Cursos, Professores e Categorias, incluindo relacionamentos entre eles e com uma implementação de uma interface navegável.

## Evolução do Projeto por Etapas

O sistema foi construído em três etapas incrementais, cada uma adicionando novas funcionalidades e complexidade.

### Etapa 1: Base do CRUD e Estrutura do Projeto
Nesta fase inicial, a estrutura fundamental da aplicação foi criada.
* **Backend:** Configuração do projeto Spring Boot com Maven, Spring Data JPA e conexão com banco de dados MySQL.
* **Entidades:** Criação das entidades `Professor` e `Categoria`.
* **CRUD Básico:** Implementação das funcionalidades de Cadastrar, Listar, Editar e Excluir para Professores e Categorias.
* **Frontend:** Criação de páginas de administração simples com Thymeleaf para exibir os dados em tabelas e interagir com o sistema.
* **Upload de Imagem:** Implementação da funcionalidade de upload de imagem para a entidade `Professor`.

![Demonstração do Sistema de Cadastro](https://raw.githubusercontent.com/PedroCoelhoIF/Sistema_Cadastro_Professores/refs/heads/master/Trabalho%201/escola/assets/demo-crud-professor.gif) 

### Etapa 2: Adição de Nova Entidade e Relacionamentos
A segunda etapa focou em expandir o modelo de dados e introduzir relacionamentos, tornando o sistema mais complexo e realista.
* **Nova Entidade:** Adição da entidade `Curso`, com campos como nome, descrição, datas de início/fim e imagem.
* **CRUD para Cursos:** Implementação de todas as operações CRUD para a nova entidade.
* **Relacionamentos JPA/Hibernate:**
    * **Professor e Curso (One-to-Many):** Um professor foi vinculado a múltiplos cursos.
    * **Categoria e Curso (One-to-Many):** Uma categoria foi vinculada a múltiplos cursos.
* **Formulários com Relacionamentos:** Os formulários de criação e edição de cursos foram implementados com menus de seleção (dropdowns) para associar um curso a um professor e a uma categoria existentes.

### Etapa 3: Construção de uma interface de usuário (UI)
A etapa final foi focada na experiência do usuário, criando uma interface pública para navegar e consumir os dados.
* **Layout Consistente:** Criação de um layout padrão com um menu de navegação (`navbar`) reutilizável em todas as páginas.
* **Menu Dinâmico:** O menu de navegação agora exibe dinamicamente a lista de categorias de cursos extraídas do banco de dados.
* **Páginas Públicas:**
    * Criação de uma **Página Inicial**.
    * Criação de uma **página de Cursos por Categoria**, que exibe os cursos em formato de galeria.
    * Criação de uma **página de Detalhes do Curso**, mostrando todas as informações de um curso selecionado.
* **Funcionalidade de Busca:** Implementação de uma barra de pesquisa no menu para buscar cursos por nome.
* **Estilização com CSS:** Criação de uma folha de estilos (`style.css`) para dar ao site uma identidade visual coesa e profissional.

### Demonstração do Sistema Final:

# Cadastro, Edição e Exclusão de Professores
![Demonstração do Sistema de Cadastro de Professores](https://raw.githubusercontent.com/PedroCoelhoIF/Sistema_Escola/refs/heads/master/Trabalho%201/escola/assets/inserir-editar-excluir-professor.gif)

# Cadastro, Edição e Exclusão de Categorias
![Demonstração do Sistema de Cadastro de Categorias](https://raw.githubusercontent.com/PedroCoelhoIF/Sistema_Escola/refs/heads/master/Trabalho%201/escola/assets/inserir-editar-excluir-categoria.gif) 

# Cadastro, Edição e Exclusão de Cursos
![Demonstração do Sistema de Cadastro de Cursos](https://raw.githubusercontent.com/PedroCoelhoIF/Sistema_Escola/refs/heads/master/Trabalho%201/escola/assets/inserir-editar-excluir-curso.gif) 

# Filtrar cursos por categoria
![Demonstração do Sistema de Cadastro de Cursos](https://raw.githubusercontent.com/PedroCoelhoIF/Sistema_Escola/refs/heads/master/Trabalho%201/escola/assets/demo-cursos-por-categoria.gif) 

## Tecnologias Utilizadas
* **Java 21** e **Spring Boot 3**
* **Spring Data JPA** e **Hibernate** para persistência de dados.
* **MySQL** como banco de dados.
* **Thymeleaf** para a renderização do frontend.
* **Maven** para gerenciamento de dependências.
* **Git & GitHub** para versionamento de código.

#### Pré-requisitos
* Java (JDK 21 ou superior)
* Maven 3.x
* MySQL Server



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
