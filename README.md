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

Temos algumas configurações no arquivo .propeties que podem ser modificadas.

Como as configurações do token JWT:
~~~
# token config
config.token.secret=${TOKEN_SECRET:secret}
config.token.expiration-hours=${TOKEN_EXPIRATION:8}
~~~

A configuração do Swagger, h2, Cors e a abertura de endpoints é opcional e pode se feita seguindo o exemplo:
Os endpoints do Swaggere do h2 são fechados por padrão

~~~
#End-Points Swagger and h2
config.request.enable-swagger=${ENABLE_SWAGGER:true}
config.request.enable-h2=${ENABLE_H2:true}

# Cors
config.origin.allowed-origins[0]=*
config.origin.allowed-origins[1]=http://my-front-url.com

#Public Requests Config

config.request.allowed-all[0]=/public/**
config.request.allowed-all[1]=/home

config.request.allowed-get[0]=/public-get/**

config.request.allowed-post[0]=/public-post/**

config.request.allowed-put[0]=/public-put/**

config.request.allowed-patch[0]=/public-patch/**

config.request.allowed-delete[0]=/public-delete/**
~~~

Devemos também ativar o h2 o swagger nas sua configurações padrões
 ~~~
# h2
spring.h2.console.enabled=${ENABLE_H2:true}
spring.h2.console.path=/h2-console

# Swagger-ui
springdoc.swagger-ui.enabled=${ENABLE_SWAGGER:true}
~~~

### :bookmark_tabs: Swagger Authentication

Para que a authenticação funcione no Swagger é necessario utilizar a anotação @SecurityRequirement(name = "bearerToken") no controller ou nos metodos seguindo o exemplo:

~~~java
@SecurityRequirement(name = "bearerToken")
public class MySecurityController{

    public String myMethod(){
        return "Hi Swagger Security";
    }
}

public class MyUnsecuredController{

    @SecurityRequirement(name = "bearerToken")
    public String mySecurityMethod(){
        return "Hi Swagger Security";
    }

    public String myUnsecuredMethod(){
        return "Unsecured";
    }
}
~~~

### :memo: PrincipalUtil

É possivel recuperar os dados do usuario atual como roles e permissions caso ele tenha feito authenticação atravez da classe PrincipalUtil, Caso não exista authenticação sera lançado um erro: NOT_AUTHENTICATED.


### :lock: Permissões de Acesso

O token gera uma autenticação com authorities que pode ser usada para filtar acesso seguindo o exemplo:

~~~java
@RestController
@RequestMapping("/test")
@SecurityRequirement(name = "bearerToken")
public class TestController {

    @GetMapping("/open")
    public ResponseEntity<String> openMethod() {
        return ResponseEntity.ok("open");
    }

    @GetMapping("/filtered-by-syngle-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> hiAdmin() {
        return ResponseEntity.ok("Hi, Admin");
    }


    @GetMapping("/filtered-by-multiple-roles")
    @PreAuthorize("hasAnyRole({'CLIENT','ADMIN'})")
    public ResponseEntity<String> hiRoles() {
        return ResponseEntity.ok("Hi, Admin and Client");
    }

    @GetMapping("/filtered-by-syngle-authority")
    @PreAuthorize("hasAuthority('EXAMPLE_1')")
    public ResponseEntity<String> hiPermission() {
        return ResponseEntity.ok("filtered by authority 'EXAMPLE_1'");
    }

    @GetMapping("/filtered-by-multiple-authorities")
    @PreAuthorize("hasAnyAuthority({'EXAMPLE_1','EXAMPLE_2'})")
    public ResponseEntity<String> hiPermissions() {
        return ResponseEntity.ok("filtered by authorities 'EXAMPLE_1','EXAMPLE_2'");
    }

    @GetMapping("/filtered-by-roles-and-authorities")
    @PreAuthorize("hasAnyRole({'CLIENT','ADMIN'}) or hasAnyAuthority({'EXAMPLE_1','EXAMPLE_2'})")
    public ResponseEntity<String> filteredByRolesAndPermission() {
        return ResponseEntity.ok("filtered by roles 'CLIENT','ADMIN' and authorities 'EXAMPLE_1','EXAMPLE_2'");
    }
}
~~~
Lembrando que a ausencia da anotação '@PreAuthorize' não deixa o endpoint publico,assim o usuario ainda precisar ser authenticado.
Mas a presença da anotação em um metodo publico exige que o usuario seja authenticado ou lançara erro.

É recomendado se utilizar a filtragem por authoridade para methodos utilizados por mais de um Perfil de Acesso(Role).
Caso o usuario não possua a role ou a authority sera lançado o erro: AccessDeniedException do spring.
As Permissões(Authorities) e Roles podem ser manipuladas pelo endpoint de /permissions.

### :bomb: Error Handling

A Dependencia trata seus proprios erros e tem suporte para tratamento do erro padrão **StandardException** que pode ser instanciado seguindo o exempo:
~~~java
 throw new StandardException("name", "message", HttpStatus.BAD_REQUEST, extra);
// ou
 throw new StandardException("name", "message", HttpStatus.BAD_REQUEST);
~~~
a classe possui o contrutor **StandardException(String name, String message, HttpStatus status, Object extra)** e faz o tratamento com o handler seguindo os dados informados retornando o objeto **StandardError**:
~~~json
{
    "timestamp": 1660328779.686435117,
    "status": 400,
    "error": "name",
    "message": "message",
    "path": "/my-path",
    "extraInfo": null
}
~~~


### :dart: Tecnologias

As seguintes ferramentas foram usadas na construção do projeto:

- [Git](https://git-scm.com)
- [Java](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Spring](https://spring.io/)
- Jpa Specification
- Swagger
- Lombok