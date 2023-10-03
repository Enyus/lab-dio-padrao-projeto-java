# Lab DIO: Padrões de Projeto com Java
Lab da DIO para o bootcamp Santander Fullstack Java + Angular que explora os padrões de projeto com a linguagem Java.

Como não há um desafio específico bem definido, estou usando este lab mais como uma abordagem teórica sobre o que são os Padrões de Projeto (Design Patterns).

## Sobre o Desafio
Padrões de Projeto são "soluções consolidadas para problemas recorrentes".

* **Padrões Criacionais:** Relacionados a instanciação de um ou múltiplos objetos. Principal objetivo é criar objetos. Ex.: Singleton.
* **Padrões Comportamentais:** Conseguir definir ou até obrigar determinados comportamentos da estrutura de código; implementar soluções para inferir comportamentos numa determinada estrutura. Ex.: Strategy.
* **Padrões Estruturais:** Transformações de informação, orquestrar eventuais integrações com sistemas externos. Ex.: Facade.

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

### Strategy
Definir um contrato a ser seguido por múltiplas implementações.
- Simplificar a variação de algoritmos para a resolução de um mesmo problema.
- Interface que encapsula ou provê contrato de um determinado algoritmo.
- Uma, ou mais, implementações que disponibilizam variações da estratégia de implementação.
- Ex.: Estratégias de movimentação de um robô.
    - Interface ```Comportamento``` exigindo método de movimentação do robô.
    - Classes de ```ComportamentoNormal```, ```ComportamentoAgressivo``` e ```ComportamentoDefensivo``` que implementam a interface ```Comportamento``` que exige que cada uma delas tenha um método de movimentação definido.
    - Classe ```Robo``` é o contexto, com variável comportamento a ser setado pelas classes ```Comportamento``` (que são as ***estratégias***) que possui também um método ```mover()``` que é delegado à ao ```Comportamento/Estratégia``` atual.


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

## Spring Framework
O Spring já adota alguns dos padrões de projeto acima referidos em seu código, como, por exemplo:
- Singleton: ```@Bean``` e ```@Autowired```;
- Strategy: ```@Service``` e ```@Repository```;
    - CrudRepository
    - ClienteService
- Facade: API REST para abstrair a complexidade das integrações: ```Spring Data JPA``` e ```ViaCEP``` (com ```Feign```).
- Módulos do SpringBoot utilizados: Spring Web, Spring Data JPA, H2 Database (banco de dados em memória), Open Feign (Client Rest Declarativo), Swagger (documentação).

[Projeto do Professor Spring](https://github.com/digitalinnovationone/lab-padroes-projeto-spring).

# Minha versão do desafio
Na parte do Java puro eu simplesmente repliquei as orientações do professor simplesmente, mas para usar o Spring framework eu gostaria de fazer algo diferente, especialmente por ser basicamente a primeira vez que eu usava o Spring e o Spring boot do zero. Assim, minha ideia inicial é resolver um problema que tive no lab de [Primeiras Páginas Interativas ocm Javascript](https://github.com/Enyus/dio-pokedex), no qual a chamada para PokeApi para uma cadeia de evolução de um determinado pokemon não partia de um endpoint único.

Mas vamos por partes. Já que esta foi o meu primeiro contato direto com o Maven, Spring Framework e Spring Boot, quero deixar aqui um passo a passo para caso eu precisar começar novamente do zero as instalações.

## Java do VSCode
Eu já tinha instalado o VSCode no meu computador, então o que eu fiz foi simplesmente instalar a extensão ```Extension Pack for Java``` direto no programa. Logo na instalação, foi-me pedido para instalar também a JDK, o que imagino seja necessário para o uso da extensão.

No entanto, conforme dito em aula, é possível baixar o VS Code já com as configurações para o Java [aqui](https://code.visualstudio.com/docs/languages/java).

Ao rodar o comando ```java -version``` num prompt de comandos, será apresentada sua versão instalada do java.

## Instalando o Maven
O Maven é um gerenciador de pacotes e empacotamento que "está para o Java como o npm está para o Node/Javascript". Sua instalação, porém, não é tão simples quanto à do Node, devendo ser seguidos os seguintes passos:

(Uso o sistema operacionl Windows 10)

1. Baixar o Maven [neste site](https://maven.apache.org/download.cgi) escolhendo a opção "Binary zip archive"
![arquivo a baixar para o maven](public\maven-download.png)
[Aqui tem um guia rápido, em inglês, do que fazer em seguida](https://maven.apache.org/install.html).
2. Descompactar o arquivo baixado em algum diretório do computador. Colocar a pasta resultante em algum diretório que lhe convenha, de preferência junto com seus demais arquivos do Java.
3. Dentro da pasta descompactada (que provavelmente se chamará apache-maven-{versão}), haverá uma pasta chamada ```bin```. Será necessário adicionar o endereço desta pasta à variável de ambiente ```PATH```.
4. Entre na pasta ```bin``` e copie seu endereço:
5. No campo de pesquisa da barra de tarefas, digite ```Editar as variáveis de ambiente do sistema```:
![Editar as variáveis de ambiente do sistema](public\editar-variaveis-sistema.jpg)
6. Na janela que aparecerá (Propriedades do Sistema), clique no botão ```Variáveis de ambiente...```
![Propriedades do Sistema](public\propriedades-sistema.jpg)
7. Na tela seguinte, no campo "Variáveis do sistema", selecione o item ```Path``` e clique em ```Editar...```, clique em ```Novo``` e cole o caminho da pasta bin do arquivo baixado anteriormente e clique em ```OK```;
8. Caso a instalação tenha sido feita corretamente, num prompt de comando, o comando ```mvn -v``` mostrará no console a versão do maven que foi instalada.
![Maven instalado corretamente](public\maven-instalado.jpg)


## Iniciando um projeto com o SpringBoot
A criação de um projeto com o SpringBoot é razoavelmente simples, especialmente se usarmos o [Spring Initializr](https://start.spring.io/):
![Spring Initializr](public\spring-initializr.jpg)
1. No lado esquerdo, deve-se selecionar o gerenciador de projetos (Maven, no caso), a linguagem de programação (Java, no caso), qual a versão do Spring Boot (usei 3.1.4) e alguns outros dados do projeto e a versão do Java desejada (usei 17).
2. No lado direito, devem ser escolhidas quais as dependências que deverão ser adicionadas ao projeto. Usei basicamente as mesmas que o professor: Spring Web, Spring Data JPA, H2 Database (apesar que, provavelmente, para o meu intuito não seria necessário um banco de dados), Open Feign e Swagger (veja minha dificuldade com o Swagger abaixo).
3. Ao clicar em "Generate", um arquivo zip será criado e poderá ser baixado do site.
4. Ao descompactar o arquivo baixado, um projeto já estará pré-criado dentro da pasta gerada, que poderá ser aberta em uma IDE.
5. Com um prompt de comando dentro da pasta do projeto, as dependências poderão ser instaladas com o comando ```mvn install```.

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