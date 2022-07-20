# Book-api

Sistema bibliotecário em que é possível fazer um CRUD de Autores e de Livros. No relacionamento entre eles, um livro pode ter um ou mais autores. Cada livro possui informações como nome, edição, ano de lançamento e uma lista do Autor ou Autores presentes nele.

Cada entidade possui as demais funcionalidades:
* Busca paginada de recursos
* Busca de recurso por id
* Inserir novo recurso
* Atualizar recurso
* Deletar recurso

# Modelo conceitual

<div align="center">
<img src="https://github.com/CarlosDaniel396/book-api/blob/main/assets/library-uml.png"/>
</div>

# Tecnologias utilizadas
* Java
* Spring Boot
* JPA / Hibernate
* Maven
* JUnit
* Postgresql

# Implantação em produção
* Back end: Heroku

# Como rodar o projeto

Pré-requisitos: Java 11
```
# Clonar repositório
git clone https://github.com/CarlosDaniel396/book-api.git

# executar o projeto
./mvnw spring-boot:run

```
# Autor
Carlos Daniel Oliveira Nunes

www.linkedin.com/in/carlos-daniel27
