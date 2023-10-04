# Lab DIO: Padrões de Projeto com Java

Este laboratório da DIO faz parte do bootcamp Santander Fullstack Java + Angular e explora os padrões de projeto em Java.

## Sobre o Desafio

Os Padrões de Projeto são soluções consolidadas para problemas recorrentes no desenvolvimento de software. Eles podem ser categorizados em três grupos principais:

* **Padrões Criacionais:** São relacionados à instanciação de objetos. Um exemplo é o Singleton, que garante que uma classe tenha apenas uma instância.

* **Padrões Comportamentais:** Tratam do comportamento das classes e objetos. O Strategy é um exemplo, pois permite definir diferentes algoritmos para resolver o mesmo problema.

* **Padrões Estruturais:** Lidam com a composição de classes e objetos. O Facade é um exemplo, pois fornece uma interface simplificada para um conjunto de subsistemas.

### Singleton

O Singleton é um padrão de projeto que garante a existência de apenas uma instância de uma classe e oferece um ponto de acesso global a essa instância. Existem variações de Singleton, como o Lazy Singleton, que cria a instância apenas quando necessário, e o Eager Singleton, que a cria imediatamente.

Exemplo de código Java para um Singleton:

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### Strategy
O Strategy é um padrão que define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis. Isso permite que o cliente escolha o algoritmo desejado em tempo de execução.

Exemplo de código Java para o padrão Strategy:

```java
// Interface que define o contrato para os algoritmos
public interface Comportamento {
    void executar();
}

// Implementações dos algoritmos
public class ComportamentoNormal implements Comportamento {
    public void executar() {
        System.out.println("Comportamento Normal");
    }
}

public class ComportamentoAgressivo implements Comportamento {
    public void executar() {
        System.out.println("Comportamento Agressivo");
    }
}

public class ComportamentoDefensivo implements Comportamento {
    public void executar() {
        System.out.println("Comportamento Defensivo");
    }
}

// Classe que utiliza o Strategy
public class Robo {
    private Comportamento comportamento;

    public Robo(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public void setComportamento(Comportamento comportamento) {
        this.comportamento = comportamento;
    }

    public void mover() {
        comportamento.executar();
    }
}
```

### Facade
O Facade é um padrão que fornece uma interface simplificada para um conjunto de subsistemas. Ele oculta a complexidade da interação com esses subsistemas e facilita o uso para o cliente.

Exemplo de uso do Facade:
```java
// Classe que representa um subsistema complicado
public class CrmService {
    public void gravarCliente(String nome, String email) {
        // Implementação detalhada para gravar um cliente no CRM
    }
}

// Classe que representa outro subsistema
public class CepApi {
    public String consultarCep(String cep) {
        // Implementação detalhada para consultar um CEP
    }
}

// Facade que simplifica a integração com os subsistemas
public class Facade {
    private CrmService crmService;
    private CepApi cepApi;

    public Facade() {
        this.crmService = new CrmService();
        this.cepApi = new CepApi();
    }

    public void migrarCliente(String nome, String cep) {
        String email = cepApi.consultarCep(cep);
        crmService.gravarCliente(nome, email);
    }
}
```