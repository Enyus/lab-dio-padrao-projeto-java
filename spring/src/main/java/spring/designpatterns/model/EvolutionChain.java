package spring.designpatterns.model;

public class EvolutionChain {
    private String name;
    private String evolvesTo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvolvesTo() {
        return evolvesTo;
    }
    
    public void setEvolvesTo(String evolvesTo) {
        this.evolvesTo = evolvesTo;
    }

}
