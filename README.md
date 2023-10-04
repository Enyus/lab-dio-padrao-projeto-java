# Lab DIO: Padrões de Projeto com Java
Lab da DIO para o bootcamp Santander Fullstack Java + Angular que explora os padrões de projeto com a linguagem Java.

Como não há um desafio específico bem definido, estou usando este lab mais como uma abordagem teórica sobre o que são os Padrões de Projeto (Design Patterns).

* [Sobre o Desafio](#sobre-o-desafio)
    * [Singleton](#singleton)
    * [Strategy](#strategy)
    * [Facade](#facade)
* [Spring Framework](#spring-framework)
* [Minha versão do desafio](#minha-versão-do-desafio)
    * [Java no VSCode](#java-no-vscode)
    * [Instalando o Maven](#instalando-o-maven)
    * [Iniciando um projeto com o Spring Boot](#iniciando-um-projeto-com-o-springboot)
        * [Problema do Swagger UI](#problema-do-swagger-ui)
    * [Rodando o projeto web](#rodando-o-projeto-web)
    * [Estrutura de Pastas](#estrutura-de-pastas)
        * [Controller](#controller)
        * [Model](#model)
        * [Repository](#repository)
        * [Service](#service)
    * [Passo a passo - WIP](#passo-a-passo)
        * [Feign](#feign)


## Sobre o Desafio
Padrões de Projeto são "soluções consolidadas para problemas recorrentes".

* **Padrões Criacionais:** Relacionados a instanciação de um ou múltiplos objetos. Principal objetivo é criar objetos. Ex.: Singleton.
* **Padrões Comportamentais:** Conseguir definir ou até obrigar determinados comportamentos da estrutura de código; implementar soluções para inferir comportamentos numa determinada estrutura. Ex.: Strategy.
* **Padrões Estruturais:** Transformações de informação, orquestrar eventuais integrações com sistemas externos. Ex.: Facade.

* [Voltar ao Topo](#)

### Singleton
Instância única de uma determinada classe.
- Permitir a criação de uma única instância de uma classe e fornecer um modo para recuperá-la.
- Instâncias usam o mesmo endereço de memória.
- Pool de banco de dados.
- Classe da camada de serviços que incorpora as regras de negócio.
- Não alocar novos endereços de memória para alocar múltiplas instâncias de certo objeto.
    - Variação - Preguiçoso (Lazy): Num primeiro momento não disponibiliza a instância para o usuário. Construtor privado. Na chamada da instância, confere se ela já existe ou não.
    - Variação - Apressado (Eager): Atribui a instância quando a variável é definida.
    - Variação - LazyHolder: Uso de memória mais otimizado. Classe estática interna que cria a variável da instância. Thread-safe. [StackOverflow](https://stackoverflow.com/questions/15019306/regarding-static-holder-singleton-pattern).

* [Voltar ao Topo](#)

### Strategy
Definir um contrato a ser seguido por múltiplas implementações.
- Simplificar a variação de algoritmos para a resolução de um mesmo problema.
- Interface que encapsula ou provê contrato de um determinado algoritmo.
- Uma, ou mais, implementações que disponibilizam variações da estratégia de implementação.
- Ex.: Estratégias de movimentação de um robô.
    - Interface ```Comportamento``` exigindo método de movimentação do robô.
    - Classes de ```ComportamentoNormal```, ```ComportamentoAgressivo``` e ```ComportamentoDefensivo``` que implementam a interface ```Comportamento``` que exige que cada uma delas tenha um método de movimentação definido.
    - Classe ```Robo``` é o contexto, com variável comportamento a ser setado pelas classes ```Comportamento``` (que são as ***estratégias***) que possui também um método ```mover()``` que é delegado à ao ```Comportamento/Estratégia``` atual.

* [Voltar ao Topo](#)

### Facade
Abstrai a complexidade de integrações com múltiplos subsistemas. Interface simplicada e abstraída.
- Prover uma 'interface' que reduza a complexidade nas integrações com subsistemas.
- "Anti-pattern"
- Obtenção de dados de subsistemas.
- Interface que realiza operações ou expor uma interface mais simples.
- Ex: API de consultar CEP.
    - ```migrarCliente``` que tem como parâmetro só nome e CEP.
    - faz ligação com uma ```CrmService``` que para gravar cliente precisa de mais parâmetros.
    - Existe uma classe ```CepApi``` que recupera cidade e estado pelo cep.
    - A intenção é que a classe ```Facade``` com o método ```migrarCliente```, este que consulta os outros subsistemas (```CrmService``` e ```CepAPI```) para realizar a criação da entrada do cliente.

[Projeto do Professor Java Puro](https://github.com/digitalinnovationone/lab-padroes-projeto-java)

* [Voltar ao Topo](#)

## Spring Framework
O Spring já adota alguns dos padrões de projeto acima referidos em seu código, como, por exemplo:
- Singleton: ```@Bean``` e ```@Autowired```;
    - ***(Atenção: Nem todo @Bean é um singleton)***
- Strategy: ```@Service``` e ```@Repository```;
    - CrudRepository
    - ClienteService
    - ***(@Service e @Repository podem ser considerados padrões por si só)***
- Facade: API REST para abstrair a complexidade das integrações: ```Spring Data JPA``` e ```ViaCEP``` (com ```Feign```).
- Módulos do SpringBoot utilizados: Spring Web, Spring Data JPA, H2 Database (banco de dados em memória), Open Feign (Client Rest Declarativo), Swagger (documentação).

[Projeto do Professor Spring](https://github.com/digitalinnovationone/lab-padroes-projeto-spring).

* [Voltar ao Topo](#)

# Minha versão do desafio
Na parte do Java puro eu simplesmente repliquei as orientações do professor simplesmente, mas para usar o Spring framework eu gostaria de fazer algo diferente, especialmente por ser basicamente a primeira vez que eu usava o Spring e o Spring boot do zero. Assim, minha ideia inicial é resolver um problema que tive no lab de [Primeiras Páginas Interativas ocm Javascript](https://github.com/Enyus/dio-pokedex), no qual a chamada para PokeApi para uma cadeia de evolução de um determinado pokemon não partia de um endpoint único.

Mas vamos por partes. Já que esta foi o meu primeiro contato direto com o Maven, Spring Framework e Spring Boot, quero deixar aqui um passo a passo para caso eu precisar começar novamente do zero as instalações.

* [Voltar ao Topo](#)

## Java no VSCode
Eu já tinha instalado o VSCode no meu computador, então o que eu fiz foi simplesmente instalar a extensão ```Extension Pack for Java``` direto no programa. Logo na instalação, foi-me pedido para instalar também a JDK, o que imagino seja necessário para o uso da extensão.

No entanto, conforme dito em aula, é possível baixar o VS Code já com as configurações para o Java [aqui](https://code.visualstudio.com/docs/languages/java).

Ao rodar o comando ```java -version``` num prompt de comandos, será apresentada sua versão instalada do java.

* [Voltar ao Topo](#)

## Instalando o Maven
O Maven é um gerenciador de pacotes e empacotamento que "está para o Java como o npm está para o Node/Javascript". Sua instalação, porém, não é tão simples quanto à do Node, devendo ser seguidos os seguintes passos:

(Uso o sistema operacionl Windows 10)

1. Baixar o Maven [neste site](https://maven.apache.org/download.cgi) escolhendo a opção "Binary zip archive"
![arquivo a baixar para o maven](./public/maven-download.png)
[Aqui tem um guia rápido, em inglês, do que fazer em seguida](https://maven.apache.org/install.html).
2. Descompactar o arquivo baixado em algum diretório do computador. Colocar a pasta resultante em algum diretório que lhe convenha, de preferência junto com seus demais arquivos do Java.
3. Dentro da pasta descompactada (que provavelmente se chamará apache-maven-{versão}), haverá uma pasta chamada ```bin```. Será necessário adicionar o endereço desta pasta à variável de ambiente ```PATH```.
4. Entre na pasta ```bin``` e copie seu endereço:
5. No campo de pesquisa da barra de tarefas, digite ```Editar as variáveis de ambiente do sistema```:
![Editar as variáveis de ambiente do sistema](./public/editar-variaveis-sistema.jpg)
6. Na janela que aparecerá (Propriedades do Sistema), clique no botão ```Variáveis de ambiente...```
![Propriedades do Sistema](./public/propriedades-sistema.jpg)
7. Na tela seguinte, no campo "Variáveis do sistema", selecione o item ```Path``` e clique em ```Editar...```, clique em ```Novo``` e cole o caminho da pasta bin do arquivo baixado anteriormente e clique em ```OK```;
8. Caso a instalação tenha sido feita corretamente, num prompt de comando, o comando ```mvn -v``` mostrará no console a versão do maven que foi instalada.
![Maven instalado corretamente](./public/maven-instalado.jpg)

* [Voltar ao Topo](#)

## Iniciando um projeto com o SpringBoot
A criação de um projeto com o SpringBoot é razoavelmente simples, especialmente se usarmos o [Spring Initializr](https://start.spring.io/):
![Spring Initializr](./public/spring-initializr.jpg)
1. No lado esquerdo, deve-se selecionar o gerenciador de projetos (Maven, no caso), a linguagem de programação (Java, no caso), qual a versão do Spring Boot (usei 3.1.4) e alguns outros dados do projeto e a versão do Java desejada (usei 17).
2. No lado direito, devem ser escolhidas quais as dependências que deverão ser adicionadas ao projeto. Usei basicamente as mesmas que o professor: Spring Web, Spring Data JPA, H2 Database (apesar que, provavelmente, para o meu intuito não seria necessário um banco de dados), Open Feign e Swagger (veja minha dificuldade com o Swagger abaixo).
3. Ao clicar em "Generate", um arquivo zip será criado e poderá ser baixado do site.
4. Ao descompactar o arquivo baixado, um projeto já estará pré-criado dentro da pasta gerada, que poderá ser aberta em uma IDE.
5. Com um prompt de comando dentro da pasta do projeto, as dependências poderão ser instaladas com o comando ```mvn install```.

* [Voltar ao Topo](#)

### Problema do Swagger UI
Talvez por algum problema com versões, ou por outra coisa que não consegui deduzir, o Swagger não apareceu nas dependências disponíveis no Spring Initializr. Assim, adicionei tal dependência manualmente, cujo passo a passo vai a seguir:

1. No site [Maven Repository](https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui/3.0.0) é apresentada a estrutura a ser incluída no arquivo pom.xml:
```xml
    <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>3.0.0</version>
    </dependency>
```
2. Adicionando o código acima no arquivo ```pom.xml```, basta entrar na pasta do projeto maven e rodar o código ```mvn install``` para que todas as dependências sejam baixadas para o repositório local.

* [Voltar ao Topo](#)

## Rodando o projeto web
Uma vez que as dependências forem corretamente instaladas, é possível executar a aplicação com o comando ```mvn spring-boot:run``` no prompt, e a aplicação será carregada na porta ```http://localhost:8080/```. Caso não haja nenhuma rota mapeada, a seguinte mensagem confirmará que a aplicação está rodando:

![Aplicação funcionando, mas sem rotas](./public/aplicacao-funcionando-sem-rotas.jpg)

Para para a aplicação, basta usar ```ctrl + c``` no prompt de comando e escolher Sim (S) quando for questionado se "Deseja finalziar o arquivo em lotes".

* [Voltar ao Topo](#)

## Estrutura de Pastas
Faz parte das boas práticas usar uma estrutura padrão para os Pacotes/Pastas do projeto. Como se trata de um projeto Web para uma API, é seguido um padrão semelhante ao modelo MVC. No caso em específico, usarei as três principais camadas: Controller, Model e Service.

```
    +- \src\main\java\spring\designpatterns
       +- Application.java
       |
       +- controller
       |
       +- model
       |
       +- service
       |
```

### Controller
Esta camada servirá principalmente para determinar os endpoints da API, tratando os ```requests``` e ```responses``` HTML.

```ChatGPT
Os controladores lidam com as solicitações HTTP, mapeando-as para métodos e retornando respostas apropriadas. Eles são responsáveis pela interação com o cliente.
```

### Model
Esta camada determinará como são formados os objetos de dados da aplicação, geralmente usada para a determinação de como o banco de dados é estruturado (nome da tabela, nome das colunas, restrições dos dados, etc.)

```ChatGPT
Nesta pasta, você coloca as classes que representam os modelos de dados da sua aplicação. Isso inclui entidades, DTOs (Data Transfer Objects) e outras classes relacionadas à lógica de negócios da aplicação.
```

### Repository
Pelo que vi, as classes guardadas na camada Repository (que geralmente guarda classes denomiadas ```DadoRespository.java```) descrevem especificamente as buscas (queries) que estarão disponíveis ao projeto.

```ChatGPT
Esta pasta contém classes que encapsulam o acesso ao banco de dados, geralmente usando o Spring Data JPA ou outro mecanismo de persistência.
```

### Service
Nesta camada, pelo que entendi, estão as classes que realmente vão fazer o "trabalho" na API, usando os modelos e respositories para implementar as regras de negócio e devolver uma resposta ao controller.

```ChatGPT
Aqui, você coloca classes que contêm a lógica de negócios da sua aplicação. Os serviços podem fazer uso dos repositórios para buscar ou salvar dados.
```

* [Voltar ao Topo](#)

## Passo a passo
[WIP]
Abaixo vou descrever algumas informações que podem ser úteis para a utilização de cada módulo do projeto.

### Feign
O Feign é um módulo que permite fazer, de maneira declarativa, chamadas a APIs externas. Para que ele funcione da maneira correta, a primeira coisa a ser feita é adicionar a anotação ```@EnableFeignClients``` no arquivo principal do projeto (```Application.java```):

```java
    // Outras importações
    import org.springframework.cloud.openfeign.EnableFeignClients; //Importação da anotação @EnableFeignClients

    @EnableFeignClients // Permite o uso do Feign no projeto
    @SpringBootApplication
    public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }

    }
```

O Feign me permitirá acessar a [PokeAPI](https://pokeapi.co/). A Intenção é tratar os dados para que a API retorne um JSON formatado apenas com as informações que serão relevantes para o Front.

Inclui na pasta ```service``` a ***interface*** (é importante lembrar que a declaração do Feign é através de um interface, não de uma classe) de utilização do Feign, que chamei de ```PokeApiService.java```, ela ficou mais ou menos assim:

```java
// Informações sobre o pacote e importações

@FeignClient(name = "pokeapi", url = "https://pokeapi.co/api/v2/pokemon") // Anotação que declara o uso do Feign e a endpoint básica, que será ampliada com o {id} do pokemon abaixo
public interface PokeApiService {
    @GetMapping("/{id}") // Esta anotação demonstra que é uma chamada GET com uma parâmetro {id}, este que é adicionado à endpoint básica para completar a URL completa da chamada
    Object getPokemonBase(@PathVariable("id") int pokemonNumber);
}
```

Até o momento, como é possível ver no código, não criei um model para os dados recebidos e preciso pesquisar um modo de fazê-lo apenas com as informações úteis.

Criei, também na pasta ```service```, a classe ```PokemonService.java``` que será responsável por invocar o Feign e tratar os dados, que ficou mais ou menos assim:

```java
// Informações sobre o pacote e importações

@Service // Anotação do Spring que marca a classe como um Serviço
public class PokemonService {
    
    @Autowired // Anotação para instanciar automaticamente a interface do Feign
    private PokeApiService pokeApiService;

    // Novamente retornando um Object porque ainda não fiz o modelo de dados
    public Object getPokemonByNumber(int number){
        return pokeApiService.getPokemonBase(number);
    }
}
```

Por sua vez, esta classe de serviço será invocada no ```controller```, então criei sua pasta e uma classe dentro dela com o nome ```PokemonController.java``` que ficou assim:

```java
// Informações sobre o pacote e importações

@RestController // Anotação do Spring que declara a classe como um controller
@RequestMapping("pokemon") // Anotação para indicar que a classe vai envolver a url com caminho localhost:8080/pokemon
public class PokemonController {

    @Autowired // Anotação para instanciar automaticamente o PokemonService
    private PokemonService pokemonService;

    @GetMapping("/{number}") // Indica que o métodos abaixo será acionado no endpoint localhost:8080/pokemon/{número do pokemon}
    public ResponseEntity<Object> getPokemonByNumber(@PathVariable int number){
        return ResponseEntity.ok(pokemonService.getPokemonByNumber(number));
    }
    // A Anotação @PathVariable "traduz" a informação que será pega da rota (em @GetMapping)
    // o método "ok" informa que a resposta mostrará um status 200 
```

Por enquanto, a chamada ```localhost:8080/pokemon/1``` retorna o mesmo JSON que a PokeAPI:

![Primeiro teste da api](./public/primeiro-teste-api.jpg)


* [Voltar ao Topo](#)