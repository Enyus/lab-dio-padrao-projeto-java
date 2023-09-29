# Lab DIO: Padrões de Projeto com Java
Lab da DIO para o bootcamp Santander Fullstack Java + Angular que explora os padrões de projeto com a linguagem Java.

Como não há um desafio específico bem definido, estou usando este lab mais como uma abordagem teórica sobre o que são os Padrões de Projeto (Design Patterns).

### Java do VSCode
Eu já tinha instalado o VSCode no meu computador, então o que eu fiz foi simplesmente instalar a extensão ```Extension Pack for Java``` direto no programa. Logo na instalação, foi-me pedido para instalar também a JDK, o que imagino seja necessário para o uso da extensão.

No entanto, conforme dito em aula, é possível baixar o VS Code já com as configurações para o Java [aqui](https://code.visualstudio.com/docs/languages/java).

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