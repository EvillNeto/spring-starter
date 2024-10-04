<h1 align="center">Spring Starter</h1>

Projeto tem a gestão de permissões dos usuarios em mente, tendo varios metodos de controle e acesso nos endpoints

### :zap: Atalhos de uso:

- [Swagger Ui](http://localhost:8080/swagger-ui/index.html)

- [Banco H2](http://localhost:8080/h2-console)

### :rocket: Setup
- é recomendado possuir uma versão do Java igual ou superior a 17, pois a api utiliza o Java 17
- A api vem com todas as configurações prontas para a porta 8080
- Rodar o projeto pode ser feito pela sua IDE de preferencia ou atraves do terminal caso sua maquina tenha maven instalado lançando o comando a seguir no root do projeto

~~~
mvn spring-boot:run
~~~

### :bookmark_tabs: Funcionalidades
- Security configurado com roles e permissões usando token Jwt
- A api possui documentação feita através do [Swagger](http://localhost:8080/swagger-ui/index.html) com descrições para cada metodo
- O banco de dados [H2](http://localhost:8080/h2-console) pode ser acessado por um atalho na tela do swagger e possui as configurações de acesso:
~~~
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:db
spring.datasource.username=sa
spring.datasource.password=
~~~
![Configuração h2](https://i.imgur.com/0UnsaT3.png)


### :dart: Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Git](https://git-scm.com)
- [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring](https://spring.io/)
- Jpa Specification
- Swagger
- Lombok